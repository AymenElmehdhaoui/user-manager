package com.example.usermanager.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> dataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException e) {
        Throwable throwable = e.getRootCause();
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(throwable.getMessage());
        errorDetails.setUri(req.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
}
