package com.nexus.catalog.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Optional;
import java.util.Map;

import com.nexus.catalog.domain.Book;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return this.books.values();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return this.books.get(isbn) != null;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return this.existsByIsbn(isbn) ? Optional.of(this.books.get(isbn)) : Optional.empty();
    }

    @Override
    public Book save(Book book) {
        this.books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        this.books.remove(isbn);
    }
}
