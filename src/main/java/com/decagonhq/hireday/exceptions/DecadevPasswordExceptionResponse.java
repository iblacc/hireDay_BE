package com.decagonhq.hireday.exceptions;

public class DecadevPasswordExceptionResponse {

    private String password;

    public DecadevPasswordExceptionResponse(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
