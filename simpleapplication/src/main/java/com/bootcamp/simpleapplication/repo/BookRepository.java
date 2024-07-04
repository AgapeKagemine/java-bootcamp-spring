package com.bootcamp.simpleapplication.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.simpleapplication.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    java.util.List<Book> findByTitle(String title);

    Optional<Book> findAllById(Long id);
}
