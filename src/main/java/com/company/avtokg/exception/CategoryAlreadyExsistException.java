package com.company.avtokg.exception;

public class CategoryAlreadyExsistException extends RuntimeException {
    public CategoryAlreadyExsistException(String message) {
        super(message);
    }
}
