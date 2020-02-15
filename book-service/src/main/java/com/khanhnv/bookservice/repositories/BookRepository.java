package com.khanhnv.bookservice.repositories;

import com.khanhnv.bookservice.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    public List<Book> findByCategoryId(int categoryId);

    public Book findByCategoryIdAndBookId(int category, int bookId);
}
