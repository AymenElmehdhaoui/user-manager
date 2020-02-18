package com.example.usermanager.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
