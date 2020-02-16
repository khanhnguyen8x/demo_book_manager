package com.khanhnv.bookservice.services;

import com.khanhnv.bookservice.client.CategoryDiscoveryClient;
import com.khanhnv.bookservice.client.CategoryFeignClient;
import com.khanhnv.bookservice.client.CategoryRestTemplateClient;
import com.khanhnv.bookservice.models.Book;
import com.khanhnv.bookservice.models.Category;
import com.khanhnv.bookservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Book> getBookByCategoryId(int categoryId) {
        return bookRepository.findByCategoryId(categoryId);
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
