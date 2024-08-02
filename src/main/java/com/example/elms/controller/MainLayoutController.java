package com.example.elms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainLayoutController {

    private final ConfigurableApplicationContext context;
    @FXML
    private BorderPane rootLayout;

    public MainLayoutController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @FXML
    public void initialize() {
        // Optional: Load a default view or setup initialization
        try {
            showDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void showDashboard() throws IOException {
        loadView("Dashboard.fxml");
    }

    @FXML
    private void showUserManagement() throws IOException {
        loadView("UserManagement.fxml");
    }

    @FXML
    private void showBookCataloging() throws IOException {
        loadView("BookCataloging.fxml");
    }

    @FXML
    private void showCirculation() throws IOException {
        loadView("Circulation.fxml");
    }

    @FXML
    private void showInventoryManagement() throws IOException {
        loadView("InventoryManagement.fxml");
    }

    @FXML
    private void showReports() throws IOException {
        loadView("Reports.fxml");
    }

    @FXML
    private void showSearchBooks() throws IOException {
        loadView("SearchBooks.fxml");
    }

    @FXML
    private void showBorrowingHistory() throws IOException {
        loadView("BorrowingHistory.fxml");
    }

    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxml));
        loader.setControllerFactory(context::getBean);
        BorderPane view = loader.load();
        rootLayout.setCenter(view);
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }
}
