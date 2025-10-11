package com.github.sync.myday.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("message", error.getDefaultMessage());
            errors.add(errorDetails);
        }

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", new Date());
        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }
}
