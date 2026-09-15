package com.airtribe.library.exception;

public class BookStatusException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BookStatusException(String message) {
        super(message);
    }
}
