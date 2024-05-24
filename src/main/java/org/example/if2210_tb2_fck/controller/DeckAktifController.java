package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.DeckAktif;
import org.example.if2210_tb2_fck.controller.DragAndDrop;

import java.io.IOException;

public class DeckAktifController {

    @FXML
    private Pane deckAktifPane1;
    @FXML
    private Pane deckAktifPane2;
    @FXML
    private Pane deckAktifPane3;
    @FXML
    private Pane deckAktifPane4;
    @FXML
    private Pane deckAktifPane5;
    @FXML
    private Pane deckAktifPane6;

    private DeckAktif deckAktif;
    private DragAndDrop dad;

    public static final DataFormat CARD_TYPE = new DataFormat("cardType");


    public void setDeckAktif(DeckAktif deckAktif){
        this.deckAktif = deckAktif;
        this.dad = new DragAndDrop();
        updateDeckAktifView();
    }

    public void updateDeckAktifView(){
        Pane[] panes = {deckAktifPane1, deckAktifPane2, deckAktifPane3, deckAktifPane4, deckAktifPane5, deckAktifPane6};
        for (int i = 0; i < deckAktif.getCol(); i++){
            Kartu kartu = deckAktif.retrieveKartu(0, i);
            if (kartu != null){
                try {
                    Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                    CardController cardController = (CardController) cardPane.getProperties().get("controller");
                    cardController.setCard(kartu);
                    setupDragDetection(cardPane, kartu);
                    panes[i].getChildren().add(cardPane);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void setupDragDetection(Pane cardPane, Kartu kartu) {
        cardPane.setOnDragDetected(event -> {
            Dragboard db = cardPane.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            String kartuName = kartu.getName();
            String kartuType = kartu.getClass().getSimpleName();

            if (kartuName != null && !kartuName.isEmpty() && kartuType != null) {
                content.putString(kartuName);
                content.put(DeckAktifController.CARD_TYPE, kartuType);
                System.out.println("Drag detected with kartu name: " + kartuName + " and type: " + kartuType);
            } else {
                System.out.println("Kartu name or type is null or empty");
            }

            db.setContent(content);

            // Display the drag view
            db.setDragView(cardPane.snapshot(null, null));

            event.consume();
        });
    }

}