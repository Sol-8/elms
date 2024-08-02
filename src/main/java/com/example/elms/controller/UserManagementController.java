package com.example.elms.controller;

import com.example.elms.model.User;
import com.example.elms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by registration number
    public Optional<Optional<User>> getUserByRegistrationNumber(String registrationNumber) {
        return userService.getUserByRegistrationNumber(registrationNumber);
    }

    // Create a new user
    public User createUser(User user) {
        return userService.createUser(user);
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // Delete a user
    public void deleteUser(Long id) {
        if (userService.existsById(id)) {
            userService.deleteUser(id);
        }
    }

    // Find user by username
    public Optional<Optional<User>> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    // Find user by email
    public Optional<Optional<User>> findByEmail(String email) {
        return userService.findByEmail(email);
    }

    // Find user by registration number (alternative method if needed)
    public Optional<Optional<User>> findByRegistrationNumberAlternative(String registrationNumber) {
        return userService.findByRegistrationNumber(registrationNumber);
    }
}
