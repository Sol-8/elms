package com.example.elms.controller;

import com.example.elms.model.Book;
import com.example.elms.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookCatalogingController {

    public Button addBookButton;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField genreField;

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookIdColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    private BookService bookService;

    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    public BookCatalogingController(TextField bookIdField) {
        this.bookIdField = bookIdField;
    }

    @FXML
    public void initialize() {
        bookIdColumn.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

        bookTable.setItems(bookData);
        loadBooks();
    }

    private void loadBooks() {
        bookData.clear();
        bookData.addAll(FXCollections.observableArrayList(bookService.findAll()));
    }

    @FXML
    public void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String   bookId= bookIdField.getText();

        if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty() && !bookId.isEmpty()) {
            Book newBook = new Book(title, author, genre, bookId);
            bookService.save(newBook); // Save book
            loadBooks(); // Reload the table with updated data
        }
    }
}
