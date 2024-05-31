package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadPluginController {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Button uploadButton;

    @FXML
    private Label statusLabel;

    private File selectedFile;

    @FXML
    public void initialize() {
        // Set initial visibility of statusLabel
        statusLabel.setVisible(false);

        // Handle back button action
        backButton.setOnAction(event -> handleBackButton());

        // Handle choose file button action
        chooseFileButton.setOnAction(event -> handleChooseFileButton());

        // Handle upload button action
        uploadButton.setOnAction(event -> handleUploadButton());
    }

    private void handleBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close(); // Assuming you want to close the window
    }

    private void handleChooseFileButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR files (*.jar)", "*.jar"));
        Stage stage = (Stage) chooseFileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            fileNameLabel.setText("File Plugin: " + selectedFile.getName());
        }
    }

    private void handleUploadButton() {
        if (selectedFile == null) {
            statusLabel.setText("Error: No file selected");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            // Perform plugin loading logic here
            boolean success = loadPlugin(selectedFile);

            if (success) {
                statusLabel.setText("Plugin Loaded Successfully");
                statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            } else {
                statusLabel.setText("Error: File is not a valid JAR");
                statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        }

        statusLabel.setVisible(true);
    }

    private boolean loadPlugin(File file) {
        // Implement your plugin loading logic here
        // Return true if loading is successful, otherwise return false
        return true; // Placeholder return value
    }
}