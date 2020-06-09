package com.decagonhq.hireday.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank(message = "Decadev ID is required")
    private String decaId;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String confirmPassword;

    public RegisterDTO() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean comparePasswords() {
        return this.password.equals(this.confirmPassword);
    }

    @Override
    public String toString() {
        return "Register{" +
                "decaId='" + decaId + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
