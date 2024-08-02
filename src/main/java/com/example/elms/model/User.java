package com.example.elms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String registrationNumber;
    private String username;
    private String role;
    private String email;

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(String username, String role, String registrationNumber) {
        this.username = username;
        this.role = role;
        this.registrationNumber = registrationNumber;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Property methods for JavaFX bindings
    public StringProperty usernameProperty() {
        return new SimpleStringProperty(username);
    }

    public StringProperty roleProperty() {
        return new SimpleStringProperty(role);
    }
}
