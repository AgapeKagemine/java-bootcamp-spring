package com.bootcamp.reactive_bootcamp.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.reactive_bootcamp.model.Book;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Integer>{
    Flux<Book> findByTitleContaining(String title);

    Flux<Book> findByPublished(boolean isPublished);
}