package com.khanhnv.bookservice.services;

import com.khanhnv.bookservice.model.Book;
import com.khanhnv.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBook(int categoryId, int bookId) {
        return bookRepository.findByCategoryIdAndBookId(categoryId, bookId);
    }

    public List<Book> getBookByCategoryId(int categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Book book){
        bookRepository.deleteById(book.getBookId());
    }
}
