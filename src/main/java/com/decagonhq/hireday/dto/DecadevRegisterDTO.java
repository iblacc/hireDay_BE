package com.decagonhq.hireday.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DecadevRegisterDTO {

    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotBlank(message = "Decadev ID number is required")
    @Column(unique = true)
    private String decaId;

    @Email(message = "Email needs to be provided")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Stack is required")
    private String stack;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String confirmPassword;

    public DecadevRegisterDTO() {
    }

    public String getDecaId() {
        return decaId;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public void setDecaId(String decaId) {
        this.decaId = decaId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "RegisterDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
