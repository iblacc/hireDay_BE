package com.decagonhq.hireday.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@DynamicUpdate
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please provide your first name")
    private String firstName;

    @NotBlank(message = "Please provide your last name")
    private String lastName;

    @NotBlank(message = "Please enter company name")
    private String companyName;

    @Email(message = "Correct email needs to be provided")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @Column(updatable = false)
    private Date create_At;

    public Employer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }
}
