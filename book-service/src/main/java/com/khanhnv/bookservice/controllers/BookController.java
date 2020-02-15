package com.khanhnv.bookservice.controllers;

import com.khanhnv.bookservice.model.Book;
import com.khanhnv.bookservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/categories/{categoryId}/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping(value = "/")
    public List<Book> getBooks(@PathVariable("categoryId") int categoryId) {
        return bookService.getBookByCategoryId(categoryId);
    }

    @GetMapping(value = "/{bookId}")
    public Book getBook(@PathVariable("categoryId") int categoryId,
                        @PathVariable("bookId") int bookId) {
        return bookService.getBook(categoryId, bookId);
    }

    @PostMapping(value = "/")
    public void getBook(Book book) {
        bookService.saveBook(book);
    }
}
