package com.example.elms.controller;

import com.example.elms.model.Book;
import com.example.elms.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class BooksController {


    @FXML
    private TableView<Book> booksTableView;

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @Autowired
    private BookService bookService;

    @FXML
    private void initialize() {
        // Initialize your controller (if needed)
    }

    @FXML
    private void handleAddBook() {
        // Display a dialog to enter book details
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Book");
        dialog.setHeaderText("Enter book details");
        dialog.setContentText("Title:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String title = result.get();
            // Similar dialogs for author and genre
            TextInputDialog authorDialog = new TextInputDialog();
            authorDialog.setTitle("Add New Book");
            authorDialog.setHeaderText("Enter book author");
            authorDialog.setContentText("Author:");
            Optional<String> authorResult = authorDialog.showAndWait();

            TextInputDialog genreDialog = new TextInputDialog();
            genreDialog.setTitle("Add New Book");
            genreDialog.setHeaderText("Enter book genre");
            genreDialog.setContentText("Genre:");
            Optional<String> genreResult = genreDialog.showAndWait();

            if (authorResult.isPresent() && genreResult.isPresent()) {
                String author = authorResult.get();
                String genre = genreResult.get();

                Book newBook = new Book();
                newBook.setTitle(title);
                newBook.setAuthor(author);
                newBook.setGenre(genre);

                bookService.save(newBook); // Save the book using BookService
                booksTableView.getItems().add(newBook); // Update TableView
                showAlert(AlertType.INFORMATION, "Book Added", "Book added successfully!");
            }
        }
    }

    @FXML
    private void handleEditBook() {
        // Get the selected book from the TableView
        Book selectedBook = booksTableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Display a dialog to edit book details
            TextInputDialog dialog = new TextInputDialog(selectedBook.getTitle());
            dialog.setTitle("Edit Book");
            dialog.setHeaderText("Edit book details");
            dialog.setContentText("Title:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String newTitle = result.get();
                selectedBook.setTitle(newTitle);

                // Update the book details in the service
                bookService.updateBook(selectedBook.getbookId(), selectedBook);

                // Refresh TableView
                booksTableView.refresh();
                showAlert(AlertType.INFORMATION, "Book Edited", "Book details updated successfully!");
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "No book selected. Please select a book to edit.");
        }
    }

    @FXML
    private void handleDeleteBook() {
        // Get the selected book from the TableView
        Book selectedBook = booksTableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Confirm deletion
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Book");
            confirmationAlert.setHeaderText("Are you sure you want to delete this book?");
            confirmationAlert.setContentText("Book: " + selectedBook.getTitle());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bookService.deleteBook(selectedBook.getbookId()); // Delete the book using BookService
                booksTableView.getItems().remove(selectedBook); // Remove from TableView
                showAlert(AlertType.INFORMATION, "Book Deleted", "Book deleted successfully!");
            }
        } else {
            showAlert(AlertType.WARNING, "No Selection", "No book selected. Please select a book to delete.");
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