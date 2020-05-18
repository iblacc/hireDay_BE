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

    @ExceptionHandler(CompanyException.class)
    protected ResponseEntity<Object> handleOrganizationNameException(CompanyException ex) {
        CompanyExceptionResponse response = new CompanyExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployerNotFoundException.class)
    protected ResponseEntity<Object> handleEmployerNotFoundException(EmployerNotFoundException ex) {
        EmployerNotFoundExceptionResponse response = new EmployerNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DecadevPasswordException.class)
    protected ResponseEntity<Object> handleDecadevPasswordException(DecadevPasswordException ex) {
        DecadevPasswordExceptionResponse response = new DecadevPasswordExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex) {
        IdNotFoundExceptionResponse response = new IdNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
