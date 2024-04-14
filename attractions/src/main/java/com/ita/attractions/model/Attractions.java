package com.ita.attractions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Attractions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attractions_generator")
    @SequenceGenerator(name = "attractions_generator", sequenceName = "attractions_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private String location;

    public Attractions() {
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
