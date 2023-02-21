package com.nexus.catalog.web;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.HashMap;

import com.nexus.catalog.domain.Book;
import com.nexus.catalog.domain.exception.BookNotFoundException;
import com.nexus.catalog.domain.exception.BookAlreadyExistsException;

@RestControllerAdvice
public class BookControllerAdvice {
    
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String bookAlreadyExistsHandler(BookAlreadyExistsException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map errors = new HashMap<String, String>();
        
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> {
                     String fieldName = ((FieldError) error).getField();
                     String errorMessage = error.getDefaultMessage();
                     errors.put(fieldName, errorMessage);
                 });

        return errors;
    }
}
