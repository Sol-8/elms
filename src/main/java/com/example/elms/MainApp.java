package com.example.elms;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        // Launch the Spring Boot application
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void init() throws Exception {
        // Initialize Spring Boot application context
        springContext = new SpringApplicationBuilder(MainApp.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file and set the controller factory to use Spring
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainLayout.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        // Set up the JavaFX stage
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ELMS - Library Management System");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Close the Spring context and exit JavaFX
        if (springContext != null) {
            springContext.close();
        }
        Platform.exit();
    }
}
