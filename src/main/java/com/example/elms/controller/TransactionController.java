package com.example.elms.controller;

import com.example.elms.model.Transaction;
import com.example.elms.service.TransactionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, Long> idColumn;
    @FXML
    private TableColumn<Transaction, String> bookTitleColumn;
    @FXML
    private TableColumn<Transaction, String> studentNameColumn;
    @FXML
    private TextField idField;
    @FXML
    private Button deleteButton;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        bookTitleColumn.setCellValueFactory(cellData -> cellData.getValue().bookTitleProperty());
        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        loadTransactions();
    }

    private void loadTransactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(transactionService.findAll());
        transactionTable.setItems(transactions);
    }

    @FXML
    private void handleDelete() {
        Long id;
        try {
            id = Long.parseLong(idField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Form Error", "Please enter a valid ID.");
            return;
        }


    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
