package com.decagonhq.hireday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployerNotFoundException extends RuntimeException {

    public EmployerNotFoundException(String message) {
        super(message);
    }
}
