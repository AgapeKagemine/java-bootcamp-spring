package com.example.bootcamp.exception;

public class NoSuchAccountExistsException extends RuntimeException {

    @SuppressWarnings("unused")
    private String message;
    
    public NoSuchAccountExistsException() {
        super();
    }
    
    public NoSuchAccountExistsException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public NoSuchAccountExistsException(final String message) {
        super(message);
        this.message = message;
    }

    public NoSuchAccountExistsException(final Throwable cause) {
        super(cause);
    }
}
