package com.decagonhq.hireday.exceptions;

public class DecadevIdExceptionResponse {

    private String decadevId;

    public DecadevIdExceptionResponse(String decadevId) {
        this.decadevId = decadevId;
    }

    public String getDecadevId() {
        return decadevId;
    }

    public void setDecadevId(String decadevId) {
        this.decadevId = decadevId;
    }
}
