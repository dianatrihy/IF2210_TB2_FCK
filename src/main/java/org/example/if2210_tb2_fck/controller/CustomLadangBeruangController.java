package org.example.if2210_tb2_fck.controller;
import java.io.IOException;

import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.example.if2210_tb2_fck.model.*;
import org.example.if2210_tb2_fck.model.Item.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomLadangBeruangController extends CustomLadangController {
    private int startRow;
    private int endRow;
    private int startCol;
    private int endCol;

    public CustomLadangBeruangController() {
        super();
    }

    public CustomLadangBeruangController(int row, int col){
        super(row, col);
    }

    @Override
    public void generateCustomPanes(Player p) {
        if (isInitialized) {
            System.out.println("Custom panes already initialized.");
            return;
        }
    
        isInitialized = true;
    
        double totalWidth = anchorPane.getPrefWidth();
        double totalHeight = anchorPane.getPrefHeight();
    
        double availableWidth = totalWidth - (col + 1) * padding;
        double availableHeight = totalHeight - (row + 1) * padding;
    
        double paneWidth = availableWidth / col;
        double paneHeight = availableHeight / row;
        // Adjust pane size based on aspect ratio
        if (paneWidth / aspectRatio < paneHeight) {
            paneHeight = paneWidth / aspectRatio;
        } else {
            paneWidth = paneHeight * aspectRatio;
        }
    
        double horizontalPadding = (totalWidth - col * paneWidth) / (col + 1);
        double verticalPadding = (totalHeight - row * paneHeight) / (row + 1);
    
        org.example.if2210_tb2_fck.model.Field ladangplayer = p.getLadang();
        // Tanaman tes = new Tanaman("PumpkinSeeds");
        // p.getLadang().placeKartu(1, 2, tes);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(paneWidth, paneHeight);
    
                // Set position of the pane based on the row and column
                pane.setLayoutX(horizontalPadding + j * (paneWidth + horizontalPadding));
                pane.setLayoutY(verticalPadding + i * (paneHeight + verticalPadding));

                pane.setStyle("-fx-background-color: #FFFFFF; -fx-opacity: 40%;");

                pane.setId("Pane" + i + j);
                // System.out.println("Pane" + i + j + " created with width: " + paneWidth + " and height: " + paneHeight);
    
                // Add drag and drop handlers
                setupDragAndDropHandlers(pane);
    
                // Add the pane to your AnchorPane
                anchorPane.getChildren().add(pane);

                // Check if kartu exists and set it to the pane
                Kartu kartu = ladangplayer.retrieveKartu(i, j);
                if (kartu != null) {
                    System.out.println("DIA GA NULL DI "+i + j);
                    try {
                        setCardToPane(i, j, kartu);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                pane.toFront();

            }
        }

        addOverlaySquare();
    }

    private void setupDragAndDropHandlers(Pane pane) {
        pane.setOnDragOver(event -> {
            System.out.println("Drag over event on " + pane.getId());
            if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });
        pane.setOnDragEntered(event -> {
            System.out.println("Drag entered event on " + pane.getId());
        });

        pane.setOnDragExited(event -> {
            System.out.println("Drag exited event on " + pane.getId());
        });

        pane.setOnDragEntered(event -> {
            System.out.println("Drag entered event on " + pane.getId());
        });

        pane.setOnDragExited(event -> {
            System.out.println("Drag exited event on " + pane.getId());
        });

        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && db.getContent(DeckAktifController.CARD_TYPE) != null) {
                String cardName = db.getString();
                String cardType = db.getContent(DeckAktifController.CARD_TYPE).toString();
                System.out.println("Dropping card with name: " + cardName + " and type: " + cardType);

                Kartu kartu = createCardByType(cardType, cardName);
                try {
                    System.out.println("Setting card to pane at row: " + pane.getId().substring(4, 5) + " col: " + pane.getId().substring(5));
                    setCardToPane(Integer.parseInt(pane.getId().substring(4, 5)), Integer.parseInt(pane.getId().substring(5)), kartu);
                    success = true;
                    System.out.println("Card dropped successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Drop data is invalid.");
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void setBearAttackPositions(int startRow, int endRow, int startCol, int endCol) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }

    private void addOverlaySquare() {
        double totalWidth = anchorPane.getPrefWidth();
        double totalHeight = anchorPane.getPrefHeight();
    
        double availableWidth = totalWidth - (col + 1) * padding;
        double availableHeight = totalHeight - (row + 1) * padding;
    
        double paneWidth = availableWidth / col;
        double paneHeight = availableHeight / row;
        if (paneWidth / aspectRatio < paneHeight) {
            paneHeight = paneWidth / aspectRatio;
        } else {
            paneWidth = paneHeight * aspectRatio;
        }
    
        double horizontalPadding = (totalWidth - col * paneWidth) / (col + 1);
        double verticalPadding = (totalHeight - row * paneHeight) / (row + 1);

        double overlayX = horizontalPadding + startCol * (paneWidth + horizontalPadding);
        double overlayY = verticalPadding + startRow * (paneHeight + verticalPadding);
        double overlayWidth = (endCol - startCol + 1) * paneWidth + (endCol - startCol) * horizontalPadding;
        double overlayHeight = (endRow - startRow + 1) * paneHeight + (endRow - startRow) * verticalPadding;

        Rectangle overlay = new Rectangle(overlayX, overlayY, overlayWidth, overlayHeight);
        overlay.setFill(Color.RED);
        overlay.setOpacity(0.5);
        
        anchorPane.getChildren().add(overlay);
        overlay.toFront();
    }
}