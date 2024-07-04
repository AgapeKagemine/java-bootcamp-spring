package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);

    Book findById(long id);
}
