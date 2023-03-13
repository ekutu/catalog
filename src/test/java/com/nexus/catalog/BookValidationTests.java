package com.nexus.catalog;

import java.util.Set;
import java.math.BigDecimal;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexus.catalog.domain.Book;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

//    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Book book = Book.of("1234567890", "Title", "Author", new BigDecimal(9.90), "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

 //   @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book book = Book.of("a234567890", "Title", "Author", new BigDecimal(9.90), "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid ISBN format.");
    }
}
