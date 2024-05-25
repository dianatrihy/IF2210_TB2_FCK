package org.example.if2210_tb2_fck.controller;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import org.example.if2210_tb2_fck.command.Panen;
import org.example.if2210_tb2_fck.model.*;
import org.example.if2210_tb2_fck.model.Item.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomLadangController {

    @FXML
    protected AnchorPane anchorPane; // Assuming this is your AnchorPane in FXML
    protected final double padding = 10;
    protected int row = 4;            // Number of rows
    protected int col = 5;            // Number of columns
    protected boolean isInitialized = false;

    protected final double originalWidth = 92;
    protected final double originalHeight = 105;
    protected final double aspectRatio = originalWidth / originalHeight;


    public CustomLadangController() {
    }

    public CustomLadangController(int row, int col){
        this.row = row;
        this.col = col;
    }
    // Your initialization method or wherever you set row and col values
    public void initialize() {
//        System.out.println("AnchorPane ID: " + anchorPane.getId());
//////        System.out.println("CustomLadangController initialized"); // Debugging
//        generateCustomPanes();
    }

    // public void generateCustomPanes() {
    //     if (isInitialized) {
    //         System.out.println("Custom panes already initialized.");
    //         return;
    //     }

    //     isInitialized = true;

    //     double totalWidth = anchorPane.getPrefWidth();
    //     double totalHeight = anchorPane.getPrefHeight();

    //     double availableWidth = totalWidth - (col + 1) * padding;
    //     double availableHeight = totalHeight - (row + 1) * padding;

    //     double paneWidth = availableWidth / col;
    //     double paneHeight = availableHeight / row;
    //     // Adjust pane size based on aspect ratio
    //     if (paneWidth / aspectRatio < paneHeight) {
    //         paneHeight = paneWidth / aspectRatio;
    //     } else {
    //         paneWidth = paneHeight * aspectRatio;
    //     }

    //     double horizontalPadding = (totalWidth - col * paneWidth) / (col + 1);
    //     double verticalPadding = (totalHeight - row * paneHeight) / (row + 1);

    //     for (int i = 0; i < row; i++) {
    //         for (int j = 0; j < col; j++) {
    //             Pane pane = new Pane();
    //             pane.setPrefSize(paneWidth, paneHeight);

    //             // Set position of the pane based on the row and column
    //             pane.setLayoutX(horizontalPadding + j * (paneWidth + horizontalPadding));
    //             pane.setLayoutY(verticalPadding + i * (paneHeight + verticalPadding));
    //             pane.setStyle("-fx-background-color: #FFFFFF; -fx-opacity: 40%;");

    //             pane.setId("Pane" + i + j);
    //             System.out.println("Pane" + i + j + " created with width: " + paneWidth + " and height: " + paneHeight);

    //             // Add drag and drop handlers
    //             setupDragAndDropHandlers(pane);
                
    //             // Add the pane to your AnchorPane
    //             anchorPane.getChildren().add(pane);
    //         }
    //     }
    // }
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
                System.out.println("KECLICKKKKKKKKKKKKKKKKKKKK");
                pane.toFront();
                System.out.println("KECL]sadsadaKK");
                pane.setOnMouseClicked(event -> handlePaneClick(kartu,p));
                
            }
        }
    }
    private void handlePaneClick(Kartu kartu, Player p) {
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLL");
        if (kartu instanceof Hewan) {
            // Show Hewan details in a popup or another UI component
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/KartuHewan.fxml"));
                Pane detailPane = loader.load();
                HewanDetailController controller = loader.getController();
                Panen panenCommand = new Panen(kartu);
                controller.setHewan((Hewan) kartu, p, panenCommand);
                
                Stage stage = new Stage();
                stage.setScene(new Scene(detailPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (kartu instanceof Tanaman){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/KartuTanaman.fxml"));
                Pane detailPane = loader.load();
                TanamanDetailController controller = loader.getController();
                Panen panenCommand = new Panen(kartu);
                controller.setTanaman((Tanaman) kartu, p, panenCommand);
                
                Stage stage = new Stage();
                stage.setScene(new Scene(detailPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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
    public void regeneratePanes(Player p) {
        anchorPane.getChildren().clear();
        generateCustomPanes(p);
    }

    public void setCardToPane(int row, int col, Kartu kartu) throws IOException {
        String paneId = "Pane" + row + col;
        Pane pane = (Pane) anchorPane.lookup("#" + paneId);
        if (pane != null) {
            System.out.println("successfully created pane");
            FXMLLoader loader = null;
            if(this.row == 4){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Card.fxml"));
            } else if(this.row == 3){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/CardBigger.fxml"));
            } else if(this.row == 5){
                loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/CardTiny.fxml"));
            }

            if (loader == null) {
                throw new IOException("Loader is null, cannot load the card.");
            }

            Pane cardPane = loader.load();

            CardController cardController = loader.getController();
            if (kartu instanceof MakhlukHidup) {
                if (((MakhlukHidup) kartu).siapHarvest()) {
                    cardController.setCardKW((MakhlukHidup) kartu);
                } else {
                    cardController.setCard(kartu);
                }
            } else {
                cardController.setCard(kartu);
            }
            pane.getChildren().clear();
            pane.setStyle("");
            pane.getChildren().add(cardPane);
        }  else {
            System.out.println("Pane with ID " + paneId + " not found.");
        }
    }

    protected void setupDragAndDropHandlers(Pane pane) {
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


    protected Kartu createCardByType(String cardType, String cardName) {
        switch (cardType) {
            case "Makhluk Hidup":
                return new MakhlukHidup(cardName, "Makhluk Hidup");
            case "Hewan":
                return new Hewan(cardName);
            case "Tanaman":
                return new Tanaman(cardName);
            case "Produk":
                return new Produk(cardName);
            case "Item":
                return new Item(cardName);
            default:
                return new Kartu(cardName, "Apapun Itu");
        }
    }

}
