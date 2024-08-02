package com.example.elms.controller;

import com.example.elms.model.Inventory;
import com.example.elms.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryManagementController {

    private final InventoryService inventoryService;

    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, Long> bookIdColumn;
    @FXML
    private TableColumn<Inventory, Integer> stockColumn;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField stockField;
    @FXML
    private TextField searchBookIdField;
    @FXML
    private Button updateInventoryButton;
    @FXML
    private Button searchStockButton;
    @FXML
    private Button addNewInventoryButton;

    public InventoryManagementController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @FXML
    private void initialize() {
        bookIdColumn.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty().asObject());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        loadInventory();
    }

    private void loadInventory() {
        ObservableList<Inventory> inventory = FXCollections.observableArrayList(inventoryService.findAllInventory());
        inventoryTable.setItems(inventory);
    }

    @FXML
    private void handleUpdateInventory() {
        Long bookId;
        Integer stock;

        try {
            bookId = Long.parseLong(bookIdField.getText());
            stock = Integer.parseInt(stockField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Form Error", "Please enter valid book ID and stock.");
            return;
        }

        Inventory inventory = new Inventory();
        inventory.setBookId(bookId);
        inventory.setStock(stock);
        inventoryService.saveInventory(inventory);
        loadInventory();
        clearInputFields();
    }

    @FXML
    private void handleSearchStock() {
        Long bookId;

        try {
            bookId = Long.parseLong(searchBookIdField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Form Error", "Please enter a valid book ID to search.");
            return;
        }

        // Use Optional to handle the potential absence of the inventory
        Inventory inventory = (Inventory) inventoryService.findInventoryByBookId(bookId).orElse(null);
        if (inventory != null) {
            showAlert(AlertType.INFORMATION, "Stock Information", "Book ID: " + inventory.getBookId() + "\nStock: " + inventory.getStock());
        } else {
            showAlert(AlertType.INFORMATION, "Stock Information", "No inventory found for Book ID: " + bookId);
        }
    }

    @FXML
    private void handleAddNewInventory() {
        Long bookId;
        Integer stock;

        try {
            bookId = Long.parseLong(bookIdField.getText());
            stock = Integer.parseInt(stockField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Form Error", "Please enter valid book ID and stock.");
            return;
        }

        Inventory inventory = new Inventory();
        inventory.setBookId(bookId);
        inventory.setStock(stock);
        inventoryService.saveInventory(inventory);
        showAlert(AlertType.INFORMATION, "Stock Information", "Book ID: " + inventory.getBookId() + "\nStock: " + inventory.getStock());
        loadInventory();
        clearInputFields();
    }

    private void clearInputFields() {
        bookIdField.clear();
        stockField.clear();
        searchBookIdField.clear();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
