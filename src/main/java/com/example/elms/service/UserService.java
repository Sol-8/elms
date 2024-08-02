package com.example.elms.service;

import com.example.elms.model.User;
import com.example.elms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Optional<User>> getUserByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(userRepository.findByRegistrationNumber(registrationNumber));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            // Update user details
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setEmail(userDetails.getEmail());
            user.setRegistrationNumber(userDetails.getRegistrationNumber());

            // Save and return the updated user
            return userRepository.save(user);
        }).orElse(null); // Return null if user is not found
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<Optional<User>> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<Optional<User>> findByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(userRepository.findByRegistrationNumber(registrationNumber));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<Optional<User>> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
