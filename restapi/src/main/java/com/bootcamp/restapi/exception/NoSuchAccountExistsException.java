package com.bootcamp.restapi.exception;

public class NoSuchAccountExistsException extends RuntimeException {
    public NoSuchAccountExistsException() {
        super();
    }
    
    public NoSuchAccountExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoSuchAccountExistsException(final String message) {
        super(message);
    }

    public NoSuchAccountExistsException(final Throwable cause) {
        super(cause);
    }
}
