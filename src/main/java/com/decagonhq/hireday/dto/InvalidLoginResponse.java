package com.decagonhq.hireday.dto;

public class InvalidLoginResponse {
    private int status;
    private String error;
    private String message;

    public InvalidLoginResponse() {
        this.status = 401;
        this.error = "Unauthorized";
        this.message = "Invalid login details";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "success=" + status +
                ", token='" + message + '\'' +
                '}';
    }
}
