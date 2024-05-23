package org.example.if2210_tb2_fck.controller;
import java.io.IOException;

import org.example.if2210_tb2_fck.model.Kartu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CustomLadangController {

    @FXML
    private AnchorPane anchorPane; // Assuming this is your AnchorPane in FXML
    private final double padding = 10;
    private int row = 5;            // Number of rows
    private int col = 4;            // Number of columns

    private final double originalWidth = 92;
    private final double originalHeight = 105;
    private final double aspectRatio = originalWidth / originalHeight;


    public CustomLadangController() {
    }

    public CustomLadangController(int row, int col){
        this.row = row;
        this.col = col;
    }
    // Your initialization method or wherever you set row and col values
    public void initialize() {
        generateCustomPanes();
    }

    int paneCount = 1;
    private void generateCustomPanes() {
        double availableWidth = 629 - (col + 1) * padding;
        double availableHeight = 485 - (row + 1) * padding;
        // double horizontalPadding = (availableWidth - col * 629) / (col + 1);
        // double verticalPadding = (availableHeight - row * 485) / (row + 1);
        double paneWidth = availableWidth / col;
        double paneHeight = availableHeight / row;

        if (paneWidth / originalWidth < paneHeight / originalHeight) {
            paneHeight = paneWidth / aspectRatio;
        } else {
            paneWidth = paneHeight * aspectRatio;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(paneWidth, paneHeight);

                // Set position of the pane based on the row and column
                pane.setLayoutX(padding + j * (paneWidth + padding));
                pane.setLayoutY(padding + i * (paneHeight + padding));
                pane.setStyle("-fx-background-color: FFFFFF; -fx-opacity: 40%;");

                pane.setId("Pane" + i + j);
                System.out.println("Pane" + i + j);
                paneCount++;
                // Add the pane to your AnchorPane
                anchorPane.getChildren().add(pane);
            }
        }
    }
    // Method to set the number of rows
    public void setRow(int row) {
        this.row = row;
    }

    // Method to set the number of columns
    public void setCol(int col) {
        this.col = col;
    }

    // Method to regenerate the panes based on updated row and column sizes
    public void regeneratePanes() {
        anchorPane.getChildren().clear();
        generateCustomPanes();
    }

    public void setCardToPane(int row, int col, Kartu kartu) throws IOException {
        String paneId = "Pane" + row + col;
        Pane pane = (Pane) anchorPane.lookup("#" + paneId);
        if (pane != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Card.fxml"));
            Pane cardPane = loader.load();

            CardController cardController = loader.getController();
            cardController.setCard(kartu);
            pane.getChildren().clear();
            pane.setStyle("");
            pane.getChildren().add(cardPane);
        }
    }
}
