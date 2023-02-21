package com.nexus.catalog.repository;

import java.util.Optional;

import com.nexus.catalog.domain.Book;

public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
