package com.nexus.catalog.domain.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("The book with ISBN " + isbn + " Does Not Exist.");
    }
}
