package org.example.if2210_tb2_fck.controller;
import java.io.IOException;

import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CustomLadangController {

    @FXML
    private AnchorPane anchorPane; // Assuming this is your AnchorPane in FXML
    private final double padding = 10;
    private int row = 5;            // Number of rows
    private int col = 6;            // Number of columns

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

    private void generateCustomPanes() {
        double totalWidth = anchorPane.getPrefWidth();
        double totalHeight = anchorPane.getPrefHeight();

        double availableWidth = totalWidth - (col + 1) * padding;
        double availableHeight = totalHeight - (row + 1) * padding;

        double paneWidth = availableWidth / col;
        double paneHeight = availableHeight / row;
        System.out.println(paneWidth);
        System.out.println(paneHeight);
        // Adjust pane size based on aspect ratio
        if (paneWidth / aspectRatio < paneHeight) {
            paneHeight = paneWidth / aspectRatio;
        } else {
            paneWidth = paneHeight * aspectRatio;
        }

        double horizontalPadding = (totalWidth - col * paneWidth) / (col + 1);
        double verticalPadding = (totalHeight - row * paneHeight) / (row + 1);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(paneWidth, paneHeight);

                // Set position of the pane based on the row and column
                pane.setLayoutX(horizontalPadding + j * (paneWidth + horizontalPadding));
                pane.setLayoutY(verticalPadding + i * (paneHeight + verticalPadding));
                pane.setStyle("-fx-background-color: #FFFFFF; -fx-opacity: 40%;");

                pane.setId("Pane" + i + j);
                System.out.println("Pane" + i + j);

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

    public void setCardToPane(int row, int col, MakhlukHidup kartu) throws IOException {
        String paneId = "Pane" + row + col;
        Pane pane = (Pane) anchorPane.lookup("#" + paneId);
        if (pane != null) {
            FXMLLoader loader = null;
            if(this.row == 4){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Card.fxml"));
            }
            else if(this.row == 3){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/CardBigger.fxml"));
            }
            else if(this.row == 5){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/CardTiny.fxml"));
            }
            Pane cardPane = loader.load();

            CardController cardController = loader.getController();
            if (kartu.siapHarvest()){
                cardController.setCardKW(kartu);
            }
            else{
                cardController.setCard(kartu);
            }
            pane.getChildren().clear();
            pane.setStyle("");
            pane.getChildren().add(cardPane);
        }
    }
}
