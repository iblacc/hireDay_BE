package com.decagonhq.hireday.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Decadev ID number is required")
    @Column(unique = true)
    private String decaId;

    public Identification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecaId() {
        return decaId;
    }

    public void setDecaId(String decaId) {
        this.decaId = decaId;
    }
}
