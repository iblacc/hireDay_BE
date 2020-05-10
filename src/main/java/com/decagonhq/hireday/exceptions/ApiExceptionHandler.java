package com.decagonhq.hireday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DecadevIdException.class)
    protected ResponseEntity<Object> handleDecadevIdException(DecadevIdException ex) {
        DecadevIdExceptionResponse response = new DecadevIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DecadevNotFoundException.class)
    protected ResponseEntity<Object> handleDecadevNotFoundException(DecadevNotFoundException ex) {
        DecadevNotFoundExceptionResponse response = new DecadevNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}