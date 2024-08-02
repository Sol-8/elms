package com.example.elms.controller;

import com.example.elms.service.ReportService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.stereotype.Controller;

@Controller
public class ReportsController {

    private final ReportService reportService;

    @FXML
    private TableView<?> reportTable;
    @FXML
    private Button generateReportButton;

    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @FXML
    private void initialize() {
        // Initialize columns and data here
        // For example:
        // TableColumn<?, ?> column1 = new TableColumn<>("Column 1");
        // reportTable.getColumns().add(column1);
    }

    @FXML
    private void handleGenerateReport() {
        // Add your logic to generate reports here
        // Example:
        // List<Report> reports = reportService.generateReport();
        // ObservableList<Report> reportList = FXCollections.observableArrayList(reports);
        // reportTable.setItems(reportList);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


