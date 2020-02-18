package com.example.usermanager.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDetails> handelApiExceptions(BaseException ex, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationDetails validationDetails = new ValidationDetails();
        validationDetails.setUri(request.getDescription(false));

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        errors.forEach(fieldError ->
                validationDetails.addError(fieldError.getDefaultMessage())
        );

        return new ResponseEntity<>(validationDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return generateValidationDetails(
                request,
                "Cannot convert the value " + ex.getValue() + " to " + ex.getRequiredType(),
                HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return generateValidationDetails(
                request,
                "Method " + ex.getMethod() + " is not supported",
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> generateValidationDetails(WebRequest request, String err, HttpStatus httpStatus) {
        ValidationDetails validationDetails = new ValidationDetails();
        validationDetails.setUri(request.getDescription(false));
        validationDetails.addError(err);
        return new ResponseEntity<>(validationDetails, httpStatus);
    }
}
