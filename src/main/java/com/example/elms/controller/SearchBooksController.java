package com.example.elms.controller;

import com.example.elms.model.Book;
import com.example.elms.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchBooksController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    private BookService bookService;

    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

        bookTable.setItems(bookData);
    }

    @FXML
    public void handleSearch() {
        String query = searchField.getText();
        if (!query.isEmpty()) {
            bookData.clear();
            bookData.addAll(bookService.searchBooks(query));
        }
    }

    @FXML
    public void handleSearchByAuthor() {
        String author = searchField.getText();
        if (!author.isEmpty()) {
            bookData.clear();
            bookData.addAll(bookService.findByAuthor(author));
        }
    }
}
