package com.example.elms.controller;

import com.example.elms.model.Transaction;
import com.example.elms.service.TransactionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class BorrowingHistoryController {

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, Long> idColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> borrowDateColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> returnDateColumn;

    @FXML
    private TableColumn<Transaction, String> studentIdColumn;

    @FXML
    private TableColumn<Transaction, String> bookIdColumn;

    private TransactionService transactionService;

    @Autowired
    private TransactionService TransactionService;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        loadTransactions();
    }

    private void loadTransactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(transactionService.findAll());
        transactionTable.setItems(transactions);
    }
}
