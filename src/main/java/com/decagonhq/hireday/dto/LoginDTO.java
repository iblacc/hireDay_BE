package com.decagonhq.hireday.dto;

import javax.validation.constraints.NotBlank;

public class Login {

    @NotBlank(message = "Decadev ID is required")
    private String decaId;

    @NotBlank(message = "Password is required")
    private String password;

    public Login() {
    }

    public String getDecaId() {
        return decaId;
    }

    public void setDecaId(String decaId) {
        this.decaId = decaId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "decaId='" + decaId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
