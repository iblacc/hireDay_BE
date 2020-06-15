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

    @ExceptionHandler(UnexpectedErrorException.class)
    protected ResponseEntity<Object> handleUnexpectedErrorException(UnexpectedErrorException ex) {
        UnexpectedErrorExceptionResponse response = new UnexpectedErrorExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployerNotFoundException.class)
    protected ResponseEntity<Object> handleEmployerNotFoundException(EmployerNotFoundException ex) {
        EmployerNotFoundExceptionResponse response = new EmployerNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserPasswordException.class)
    protected ResponseEntity<Object> handleDecadevPasswordException(UserPasswordException ex) {
        UserPasswordExceptionResponse response = new UserPasswordExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        UserAlreadyExistExceptionResponse response = new UserAlreadyExistExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    protected ResponseEntity<Object> handleAdminNotFoundException(AdminNotFoundException ex) {
        AdminNotFoundExceptionResponse response = new AdminNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
