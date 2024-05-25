package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.if2210_tb2_fck.model.LoadState;
import org.example.if2210_tb2_fck.parser.IParser;
import org.example.if2210_tb2_fck.parser.TxtParser;

import java.io.File;
import java.util.Set;

public class LoadStateController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button loadButton;

    @FXML
    private Label statusLabel;

    private LoadState loadState;
    private GameManagerController gameManagerController;

    @FXML
    public void initialize(){
        formatComboBox.getItems().addAll("txt");
        statusLabel.setVisible(false);
        backButton.setOnAction(event -> handleBackButton());
        loadButton.setOnAction(event -> handleLoadButton());
    }

    public void setGameManagerController(GameManagerController gameManagerController){
        this.gameManagerController = gameManagerController;
    }

    private void handleBackButton(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void handleLoadButton(){
        String format = formatComboBox.getValue();
        System.err.println(format); // debug
        String folder = folderTextField.getText();

        if (format == null || folder.isEmpty()){
            statusLabel.setText("Please select a format and enter a folder name.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else if (!isFolderExists(folder)){
            statusLabel.setText("Folder not found.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            boolean success = loadState(format, folder);
            if (success){
                statusLabel.setText("State Loaded Successfully");
                statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                // close window
                Stage stage = (Stage) loadButton.getScene().getWindow();
                stage.close();
            } else {
                statusLabel.setText("Failed to Load State");
                statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        }

        statusLabel.setVisible(true);
    }

    public boolean isFolderExists(String folder){
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/" + folder);
        return file.exists() && file.isDirectory();
    }

    private boolean loadState(String format, String folder){
        try {
            IParser parser = gameManagerController.getParser(format);
            if (parser == null){
                throw new IllegalArgumentException("No parser found for format: " + format);
            }
            LoadState loadState = new LoadState(parser.parseGameState(folder + "/gamestate." + format), parser.parsePlayer(folder + "/player1." + format), parser.parsePlayer(folder + "/player2." + format));
            this.loadState = loadState;
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public LoadState getLoadState(){
        return this.loadState;
    }

    public void updateFormats(Set<String> formats) {
        System.err.println("DI LOAD CONTROLLER: " + formats); // debug
        formatComboBox.getItems().clear();
        formatComboBox.getItems().addAll(formats);
    }
}