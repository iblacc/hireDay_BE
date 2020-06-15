package com.decagonhq.hireday.exceptions;

public class UnexpectedErrorExceptionResponse {

    private String ExceptedErrorException;

    public UnexpectedErrorExceptionResponse(String ExceptedErrorException) {
        this.ExceptedErrorException = ExceptedErrorException;
    }

    public String getExceptedErrorException() {
        return ExceptedErrorException;
    }

    public void setExceptedErrorException(String exceptedErrorException) {
        this.ExceptedErrorException = exceptedErrorException;
    }
}
