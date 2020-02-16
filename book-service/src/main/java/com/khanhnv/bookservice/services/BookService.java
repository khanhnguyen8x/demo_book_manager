package com.khanhnv.bookservice.services;

import com.khanhnv.bookservice.client.CategoryDiscoveryClient;
import com.khanhnv.bookservice.client.CategoryFeignClient;
import com.khanhnv.bookservice.client.CategoryRestTemplateClient;
import com.khanhnv.bookservice.models.Book;
import com.khanhnv.bookservice.models.Category;
import com.khanhnv.bookservice.repositories.BookRepository;
import com.khanhnv.bookservice.utils.RandomUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    CategoryDiscoveryClient categoryDiscoveryClient;

    @Autowired
    CategoryRestTemplateClient categoryRestTemplateClient;

    @Autowired
    CategoryFeignClient categoryFeignClient;

    @Autowired
    private BookRepository bookRepository;

    public Book getBook(int categoryId, int bookId, String clientType) {
        Book book = bookRepository.findByCategoryIdAndBookId(categoryId, bookId);

        Category category = getCategory(categoryId, clientType);

        return Book.builder().bookId(book.getBookId())
                .name(book.getName())
                .price(book.getPrice())
                .categoryId(category.getCategoryId())
                .categoryName(category.getName())
                .categoryDescription(category.getDescription())
                .build();
    }

    @HystrixCommand(fallbackMethod = "buildFallbackBookList",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize", value = "30"),
                            @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
    )
    public List<Book> getBookByCategoryId(int categoryId) {

        RandomUtil.randomlyRunLong();

        return bookRepository.findByCategoryId(categoryId);
    }

    private List<Book> buildFallbackBookList(int categoryId) {
        List<Book> fallbackList = new ArrayList<>();
        Book book = Book.builder().bookId(10000)
                .name("Sorry no book information currently available")
                .categoryId(categoryId)
                .build();

        fallbackList.add(book);
        return fallbackList;
    }


    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.deleteById(book.getBookId());
    }

    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @HystrixCommand
    private Category getCategory(int categoryId, String clientType) {
        Category category = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                category = categoryFeignClient.getCategory(categoryId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                category = categoryRestTemplateClient.getCategory(categoryId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                category = categoryDiscoveryClient.getCategory(categoryId);
                break;
            default:
                category = categoryRestTemplateClient.getCategory(categoryId);
        }

        return category;
    }
}
