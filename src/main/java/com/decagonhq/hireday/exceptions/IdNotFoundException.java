package com.decagonhq.hireday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }
}
