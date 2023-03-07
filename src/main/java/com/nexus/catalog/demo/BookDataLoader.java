package com.nexus.catalog.demo;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import com.nexus.catalog.domain.Book;
import com.nexus.catalog.repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();

        Book book1 = Book.of("1234567891", "Northen Lights", "Lyra Silverstar", new BigDecimal(9.90));
        Book book2 = Book.of("1234567892", "Nexus Journey", "Iorek Nexusson", new BigDecimal(12.90));
        
        bookRepository.saveAll(List.of(book1, book2));
    }
}
