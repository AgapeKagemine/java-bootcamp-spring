package com.bootcamp.reactive_bootcamp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.bootcamp.reactive_bootcamp.model.Book;
import com.bootcamp.reactive_bootcamp.service.BookService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class BookControllerMockitoTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBookByIdOk() {
        var book = Book.builder().id(1).title("test title 1").description("test description 1").build();
        log.info("book: {}", book);

        when(bookService.findById(anyInt())).thenReturn(Mono.just(book));

        Mono<ResponseEntity<Book>> response = bookController.getBookById(1);
        response.subscribe(
                value -> {
                    var existBook = value.getBody();
                    log.info("exist book={}", existBook);
                    assertTrue(book.getId() == existBook.getId());
                    assertEquals(book.getTitle(), existBook.getTitle());
                });
    }
}
