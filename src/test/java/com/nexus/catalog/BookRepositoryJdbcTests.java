package com.nexus.catalog;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.math.BigDecimal;

import com.nexus.catalog.domain.Book;
import com.nexus.catalog.repository.BookRepository;
import com.nexus.catalog.config.DataConfig;

//@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

//    @Test
    public void findBookByIsbnWhenExisting() {

        String bookIsbn = "1234561237";
        Book book = Book.of(bookIsbn, "Title", "Author", new BigDecimal(12.90), "Polarshop");
        jdbcAggregateTemplate.insert(book);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
