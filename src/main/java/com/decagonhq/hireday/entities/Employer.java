package com.decagonhq.hireday.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@DynamicUpdate
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Please enter organization name")
    @Column(unique = true)
    private String organizationName;

    @Email(message = "Email needs to be provided")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    private String role;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @ElementCollection
    private List<String> technologies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "stack_number_mapping",
            joinColumns = {@JoinColumn(name = "employer_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "stack")
    @Column(name = "number")
    private Map<String, Integer> interest = new HashMap<>();

    @NotNull(message = "Meeting date needs to be specified")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date meetingDate;

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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public Map<String, Integer> getInterest() {
        return interest;
    }

    public void setInterest(Map<String, Integer> talent) {
        this.interest = talent;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }
}
