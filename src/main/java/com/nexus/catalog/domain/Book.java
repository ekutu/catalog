package com.nexus.catalog.domain;

import org.springframework.data.annotation.Version;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record Book (

        @Id
        Long id,

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Invalid ISBN format.")
        String isbn,

        @NotBlank(message = "The book title must be supplied.")
        String title,

        @NotBlank(message = "The book author must be supplied.")
        String author,

        @NotNull(message = "The book price must supplied")
        @Positive(message = "The book price must be greater than zero")
        BigDecimal price,

        @CreatedDate
        LocalDateTime createdDate,

        @LastModifiedDate
        LocalDateTime lastModifiedDate,

        String publisher,

        @Version
        int version) {

    public static Book of(String isbn, String title, String author, BigDecimal price, String publisher) {
        return new Book(null, isbn, title, author, price, null, null, publisher, 0);
    }
}
