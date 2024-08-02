package com.example.elms.controller;

import com.example.elms.model.Transaction;
import com.example.elms.service.ReportService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    @FXML
    private TableView<Transaction> overdueBooksTableView;

    @FXML
    private TableColumn<Transaction, String> titleColumn;
    @FXML
    private TableColumn<Transaction, String> authorColumn;
    @FXML
    private TableColumn<Transaction, String> dueDateColumn;

    @FXML
    private ListView<String> borrowingFrequencyListView;

    @Autowired
    private ReportService reportService;

    @FXML
    private void initialize() {
        // Initialize the table columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // Load overdue books and borrowing frequency
        loadOverdueBooks();
        loadBorrowingFrequency();
    }

    private void loadOverdueBooks() {
        List<Transaction> overdueBooks = reportService.getOverdueBooks();
        overdueBooksTableView.getItems().setAll(overdueBooks);
    }

    private void loadBorrowingFrequency() {
        Map<String, Long> frequencyMap = reportService.getBookBorrowingFrequency();
        borrowingFrequencyListView.getItems().clear();
        for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
            borrowingFrequencyListView.getItems().add(entry.getKey() + ": " + entry.getValue());
        }
    }
}
