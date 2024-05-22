package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class DragAndDrop {

    @FXML
    private Pane deckAktifPane;
    @FXML
    private Pane ladangPane;
    @FXML
    private ImageView kartu1;

    @FXML
    public void initialize() {
        setupDragAndDrop(kartu1);
    }

    private void setupDragAndDrop(ImageView kartu) {
        kartu.setOnDragDetected(event -> {
            Dragboard db = kartu.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(kartu.getImage());
            event.consume();
        });

        deckAktifPane.setOnDragOver(event -> {
            if (event.getGestureSource() != deckAktifPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        deckAktifPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                deckAktifPane.getChildren().remove(kartu);
                deckAktifPane.getChildren().add(new ImageView(db.getImage()));
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        ladangPane.setOnDragOver(event -> {
            if (event.getGestureSource() != ladangPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        ladangPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                ladangPane.getChildren().add(new ImageView(db.getImage()));
                deckAktifPane.getChildren().remove(kartu);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }
}