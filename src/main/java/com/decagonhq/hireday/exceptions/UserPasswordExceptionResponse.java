package com.decagonhq.hireday.exceptions;

public class UserPasswordExceptionResponse {

    private String password;

    public UserPasswordExceptionResponse(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
