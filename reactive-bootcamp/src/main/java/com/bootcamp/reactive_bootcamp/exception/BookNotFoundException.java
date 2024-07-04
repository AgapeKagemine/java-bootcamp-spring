package com.bootcamp.reactive_bootcamp.exception;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String message) {
        super(message);
    }
    
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BookNotFoundException(Throwable cause) {
        super(cause);
    }
    
}