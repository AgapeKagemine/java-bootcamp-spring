package com.bootcamp.restapi.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() {
        super();
    }
    
    public AccountAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistsException(final String message) {
        super(message);
    }

    public AccountAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
