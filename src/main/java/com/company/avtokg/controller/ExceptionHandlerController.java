package com.company.avtokg.controller;


import com.company.avtokg.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ ItemNotFoundException.class,
            AppBadRequestException.class, ItemAlreadyExistsException.class,
            CategoryAlreadyExsistException.class, PhoneAlreadyExistsException.class})
    public ResponseEntity<?> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({AppForbiddenException.class})
    public ResponseEntity<?> handleForbidden(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({TokenNotValidException.class})
    public ResponseEntity<?> mazgi(TokenNotValidException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
