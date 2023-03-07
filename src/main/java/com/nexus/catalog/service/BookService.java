package com.nexus.catalog.service;

import org.springframework.stereotype.Service;

import com.nexus.catalog.domain.Book;
import com.nexus.catalog.repository.BookRepository;
import com.nexus.catalog.domain.exception.BookNotFoundException;
import com.nexus.catalog.domain.exception.BookAlreadyExistsException;

@Service
public class BookService {
    
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return this.bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return this.bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (this.bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        } 

        return this.bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        this.bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return this.bookRepository.findByIsbn(isbn).map(existingBook -> {
            Book bookToUpdate = new Book(existingBook.id(),
                                         existingBook.isbn(), 
                                         book.title(),
                                         book.author(),
                                         book.price(),
                                         existingBook.createdDate(),
                                         existingBook.lastModifiedDate(),
                                         existingBook.version());
            return this.bookRepository.save(bookToUpdate);
        }).orElseGet(() -> this.addBookToCatalog(book));
    }
}
