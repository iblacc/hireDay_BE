package com.decagonhq.hireday.exceptions;

public class UserAlreadyExistExceptionResponse {
    private String userAlreadyExist;

    public UserAlreadyExistExceptionResponse(String userAlreadyExist) {
        this.userAlreadyExist = userAlreadyExist;
    }

    public String getUserAlreadyExist() {
        return userAlreadyExist;
    }

    public void setUserAlreadyExist(String userAlreadyExist) {
        this.userAlreadyExist = userAlreadyExist;
    }
}
