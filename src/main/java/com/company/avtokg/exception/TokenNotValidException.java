package com.company.avtokg.exception;

public class TokenNotValidException extends RuntimeException{
    public TokenNotValidException(String message) {
        super(message);
    }
}
