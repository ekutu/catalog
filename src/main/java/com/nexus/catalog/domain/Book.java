package com.nexus.catalog.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Pattern;

import java.math.BigDecimal;

public record Book (

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Invalid ISBN format.")
        String isbn,

        @NotBlank(message = "The book title must be supplied.")
        String title,

        @NotBlank(message = "The book author must be supplied.")
        String author,

        @NotNull(message = "The book price must supplied")
        @Positive(message = "The book price must be greater than zero")
        BigDecimal price) {
}
