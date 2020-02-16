package com.khanhnv.bookservice.controllers;

import com.khanhnv.bookservice.models.Book;
import com.khanhnv.bookservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{bookId}/{clientType}")
    public Book getBook(@PathVariable("categoryId") int categoryId,
                        @PathVariable("bookId") int bookId,
                        @PathVariable("clientType") String clientType) {
        return bookService.getBook(categoryId, bookId, clientType);
    }

    @PostMapping(value = "/")
    public void createBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @PutMapping(value = "/{bookId}")
    public void editBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        book.setBookId(bookId);
        bookService.saveBook(book);
    }

    @DeleteMapping(value = "/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
    }
}
