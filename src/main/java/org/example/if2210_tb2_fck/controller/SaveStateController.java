package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.if2210_tb2_fck.model.LoadState;
import org.example.if2210_tb2_fck.model.SaveState;
import org.example.if2210_tb2_fck.parser.IParser;

import java.io.File;
import java.util.Set;

public class SaveStateController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> formatComboBox;

    @FXML
    private TextField folderTextField;

//    @FXML
//    private Button saveButton; // Renamed from loadButton

    @FXML
    private Label statusLabel;

    private SaveState saveState;
    private GameManagerController gameManagerController;

    @FXML
    public void initialize(){
        formatComboBox.getItems().addAll("txt");
        statusLabel.setVisible(false);
        backButton.setOnAction(event -> handleBackButton());
//        saveButton.setOnAction(event -> handleSaveButton()); // Updated method call
    }

    public void setGameManagerController(GameManagerController gameManagerController){
        this.gameManagerController = gameManagerController;
    }

    private void handleBackButton(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void handleSaveButton(){
        String format = formatComboBox.getValue();
        String folder = folderTextField.getText();

        if (format == null || folder.isEmpty()){
            statusLabel.setText("Please select a format and enter a folder name.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else if (!isFolderExists(folder)){
            statusLabel.setText("Folder not found.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            boolean success = saveState(format, folder);
            if (success){
                statusLabel.setText("State Saved Successfully");
                statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                // Optional: close window
//                Stage stage = (Stage) saveButton.getScene().getWindow();
//                stage.close();
            } else {
                statusLabel.setText("Failed to Save State");
                statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        }

        statusLabel.setVisible(true);
    }

    public boolean isFolderExists(String folder){
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/" + folder);
        return file.exists() && file.isDirectory();
    }

    private boolean saveState(String format, String folder){
        try {
            LoadState loadState = gameManagerController.getLoadState();
            this.saveState = new SaveState(loadState);
            this.saveState.saveToFile("src/main/resources/org/example/if2210_tb2_fck/" + folder + "/gamestate." + format);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public SaveState getSaveState(){
        return this.saveState;
    }

    public void updateFormats(Set<String> formats) {
        System.err.println("DI SAVE CONTROLLER: " + formats); // debug
        formatComboBox.getItems().clear();
        formatComboBox.getItems().addAll(formats);
    }

}
