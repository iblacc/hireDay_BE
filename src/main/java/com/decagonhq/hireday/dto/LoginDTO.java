package com.decagonhq.hireday.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotBlank(message = "Decadev ID is required")
    private String decaId;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public LoginDTO() {
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
