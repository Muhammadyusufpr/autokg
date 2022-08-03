package com.company.avtokg.exception;

public class RegionAlreadyExistsException extends RuntimeException {
    public RegionAlreadyExistsException(String message) {
        super(message);
    }
}
