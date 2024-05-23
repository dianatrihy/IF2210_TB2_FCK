package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class DragAndDrop {

    @FXML
    private Pane deckAktifPane;
    @FXML
    private Pane ladangPane;
    @FXML
    private Pane kartu1; // Ubah tipe dari ImageView menjadi Pane

    @FXML
    public void initialize() {
        try {
            Pane cardPane = loadCard("/org/example/if2210_tb2_fck/Card.fxml");
            kartu1.getChildren().add(cardPane);
            setupDragAndDrop(cardPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupDragAndDrop(Pane card) {
        ImageView cardImageView = (ImageView) card.lookup("#cardImage");
        if (cardImageView == null) {
            System.err.println("cardImageView not found");
            return;
        }
        cardImageView.setOnDragDetected(event -> {
            System.out.println("Drag detected on card");
            Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            db.setContent(content);
            db.setDragView(cardImageView.getImage());
            event.consume();
        });

        cardImageView.setOnMouseDragged(event -> {
            cardImageView.setLayoutX(event.getSceneX() - cardImageView.getBoundsInParent().getWidth() / 2);
            cardImageView.setLayoutY(event.getSceneY() - cardImageView.getBoundsInParent().getHeight() / 2);
            event.consume();
        });

        deckAktifPane.setOnDragOver(event -> {
            System.out.println("Drag over deckAktifPane");
            if (event.getGestureSource() != deckAktifPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        deckAktifPane.setOnDragDropped(event -> {
            System.out.println("Drop on deckAktifPane");
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                deckAktifPane.getChildren().clear();
                try {
                    Pane newCard = loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                    ImageView newCardImageView = (ImageView) newCard.lookup("#cardImage");
                    if (newCardImageView != null) {
                        newCardImageView.setImage(db.getImage());
                    }
                    deckAktifPane.getChildren().add(newCard);
                    setupDragAndDrop(newCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        ladangPane.setOnDragOver(event -> {
            System.out.println("Drag over ladangPane");
            if (event.getGestureSource() != ladangPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        ladangPane.setOnDragDropped(event -> {
            System.out.println("Drop on ladangPane");
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                try {
                    Pane newCard = loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                    ImageView newCardImageView = (ImageView) newCard.lookup("#cardImage");
                    if (newCardImageView != null) {
                        newCardImageView.setImage(db.getImage());
                    }
                    ladangPane.getChildren().add(newCard);
                    setupDragAndDrop(newCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    public Pane loadCard(String fxmlPath) throws IOException {
        URL resourceUrl = getClass().getResource(fxmlPath);
        if (resourceUrl == null) {
            throw new IOException("Cannot find FXML file: " + fxmlPath);
        }
        FXMLLoader loader = new FXMLLoader(resourceUrl);
        Pane card = loader.load();
        card.getProperties().put("controller", loader.getController());
        return card;
    }
}