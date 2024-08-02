package com.example.elms.controller;

import com.example.elms.model.User;
import com.example.elms.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsersController {

    @FXML
    private TableView<User> usersTableView;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> registrationNumberColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @Autowired
    private UserService userService;

    private ObservableList<User> usersList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize table columns
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

        // Load users into TableView
        usersList.addAll(userService.getAllUsers());
        usersTableView.setItems(usersList);

        // Set up a key press event to delete user when "Delete" key is pressed
        usersTableView.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DELETE) {
                handleDeleteUser();
            }
        });
    }

    @FXML
    private void handleAddUser() {
        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setTitle("Add New User");
        usernameDialog.setHeaderText("Enter user details");
        usernameDialog.setContentText("Username:");

        Optional<String> usernameResult = usernameDialog.showAndWait();
        if (usernameResult.isPresent()) {
            String username = usernameResult.get();

            TextInputDialog emailDialog = new TextInputDialog();
            emailDialog.setTitle("Add New User");
            emailDialog.setHeaderText("Enter user email");
            emailDialog.setContentText("Email:");
            Optional<String> emailResult = emailDialog.showAndWait();

            TextInputDialog regNumberDialog = new TextInputDialog();
            regNumberDialog.setTitle("Add New User");
            regNumberDialog.setHeaderText("Enter user registration number");
            regNumberDialog.setContentText("Registration Number:");
            Optional<String> regNumberResult = regNumberDialog.showAndWait();

            if (emailResult.isPresent() && regNumberResult.isPresent()) {
                String email = emailResult.get();
                String registrationNumber = regNumberResult.get();

                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setRegistrationNumber(registrationNumber);

                userService.createUser(newUser); // Save the user using UserService
                usersList.add(newUser); // Update TableView
                showAlert(AlertType.INFORMATION, "User Added", "User added successfully!");
            }
        }
    }

    @FXML
    private void handleEditUser() {
        User selectedUser = usersTableView.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            TextInputDialog usernameDialog = new TextInputDialog(selectedUser.getUsername());
            usernameDialog.setTitle("Edit User");
            usernameDialog.setHeaderText("Edit user details");
            usernameDialog.setContentText("Username:");

            Optional<String> usernameResult = usernameDialog.showAndWait();
            if (usernameResult.isPresent()) {
                String newUsername = usernameResult.get();
                selectedUser.setUsername(newUsername);

                TextInputDialog emailDialog = new TextInputDialog(selectedUser.getEmail());
                emailDialog.setTitle("Edit User");
                emailDialog.setHeaderText("Edit user email");
                emailDialog.setContentText("Email:");
                Optional<String> emailResult = emailDialog.showAndWait();

                if (emailResult.isPresent()) {
                    String newEmail = emailResult.get();
                    selectedUser.setEmail(newEmail);

                    TextInputDialog regNumberDialog = new TextInputDialog(selectedUser.getRegistrationNumber());
                    regNumberDialog.setTitle("Edit User");
                    regNumberDialog.setHeaderText("Edit user registration number");
                    regNumberDialog.setContentText("Registration Number:");
                    Optional<String> regNumberResult = regNumberDialog.showAndWait();

                    if (regNumberResult.isPresent()) {
                        String newRegistrationNumber = regNumberResult.get();
                        selectedUser.setRegistrationNumber(newRegistrationNumber);

                        userService.updateUser(selectedUser.getUserId(), selectedUser); // Update user using UserService
                        usersTableView.refresh(); // Refresh TableView
                        showAlert(AlertType.INFORMATION, "User Edited", "User details updated successfully!");
                    }
                }
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "No user selected. Please select a user to edit.");
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = usersTableView.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete User");
            confirmationAlert.setHeaderText("Are you sure you want to delete this user?");
            confirmationAlert.setContentText("User: " + selectedUser.getUsername());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                userService.deleteUser(selectedUser.getUserId()); // Delete the user using UserService
                usersList.remove(selectedUser); // Remove from TableView
                showAlert(AlertType.INFORMATION, "User Deleted", "User deleted successfully!");
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "No user selected. Please select a user to delete.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
