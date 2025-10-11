package com.github.sync.myday.handle.exception;

public class EmailExistingException extends RuntimeException {
    public EmailExistingException(String message) {
        super(message);
    }
}
