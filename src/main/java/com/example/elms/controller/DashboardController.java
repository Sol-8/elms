package com.example.elms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class DashboardController {

    private final ConfigurableApplicationContext context;

    @FXML
    private Button catalogButton;

    @FXML
    private Button circulationButton;

    @FXML
    private Button reportButton;

    @FXML
    private AnchorPane rootPane;

    public DashboardController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @FXML
    private void initialize() {
        // Any initialization code can go here
    }

    @FXML
    private void handleCatalog() {
        loadView("Catalog.fxml");
    }

    @FXML
    private void handleCirculation() {
        loadView("Circulation.fxml");
    }

    @FXML
    private void handleReports() {
        loadView("Reports.fxml");
    }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setControllerFactory(context::getBean);
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
