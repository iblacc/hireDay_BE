package com.decagonhq.hireday.exceptions;

public class DecadevNotFoundExceptionResponse {

    private String DecadevNotFound;

    public DecadevNotFoundExceptionResponse(String decadevNotFound) {
        DecadevNotFound = decadevNotFound;
    }

    public String getDecadevNotFound() {
        return DecadevNotFound;
    }

    public void setDecadevNotFound(String decadevNotFound) {
        DecadevNotFound = decadevNotFound;
    }
}
