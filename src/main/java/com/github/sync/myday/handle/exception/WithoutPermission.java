package com.github.sync.myday.handle.exception;

public class WithoutPermission extends RuntimeException {
    public WithoutPermission(String message) {
        super(message);
    }
}
