package com.decagonhq.hireday.exceptions;

public class IdNotFoundExceptionResponse {
    private String IdNotFound;

    public IdNotFoundExceptionResponse(String idNotFound) {
        IdNotFound = idNotFound;
    }

    public String getIdNotFound() {
        return IdNotFound;
    }

    public void setIdNotFound(String idNotFound) {
        IdNotFound = idNotFound;
    }
}
