package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(nullable = false, unique = true)
    public String title;

    @Column(nullable = false)
    public String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}