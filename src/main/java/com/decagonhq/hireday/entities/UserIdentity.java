package com.decagonhq.hireday.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_identities")
public class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email needs to be provided")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    public UserIdentity() {
    }

    public UserIdentity(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
