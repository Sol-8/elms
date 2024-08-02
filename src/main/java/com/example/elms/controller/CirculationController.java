package com.example.elms.controller;

import com.example.elms.model.Book;
import com.example.elms.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;

import org.springframework.stereotype.Controller;

@Controller
public class CirculationController {

    private final BookService bookService;

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    public CirculationController(BookService bookService) {
        this.bookService = bookService;
    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        loadBooks();
    }

    private void loadBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findAll());
        bookTable.setItems(books);
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText();
        ObservableList<Book> books = FXCollections.observableArrayList(bookService.findByTitle(searchTerm));
        bookTable.setItems(books);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
