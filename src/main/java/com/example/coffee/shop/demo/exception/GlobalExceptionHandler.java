package com.example.coffee.shop.demo.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)  // ✅ Handles all AppException subclasses
    public ResponseEntity<ErrorResponse> handleAppException(AppException e) {
        ErrorResponse error = ErrorResponse.builder()
                .status(e.getHttpStatus().value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(e.getHttpStatus()).body(error);
    }
}