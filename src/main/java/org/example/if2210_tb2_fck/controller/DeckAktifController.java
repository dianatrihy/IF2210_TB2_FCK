package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.DeckAktif;
import org.example.if2210_tb2_fck.controller.DragAndDrop;

import java.io.IOException;

public class DeckAktifController {

    private Pane deckAktifPane;

    private DeckAktif deckAktif;

    private DragAndDrop dad;

    public void setDeckAktifPane(Pane deckAktifPane) {
        this.deckAktifPane = deckAktifPane;
        if (this.deckAktif != null) {
            updateDeckAktifView();
        }
    }

    public void setDeckAktif(DeckAktif deckAktif) {
        this.deckAktif = deckAktif;
        this.dad = new DragAndDrop();
        if (this.deckAktifPane != null) {
            updateDeckAktifView();
        }
    }

    public void updateDeckAktifView() {
        deckAktifPane.getChildren().clear();
        for (Kartu kartu : deckAktif.getAllCards()) {
            System.out.println("KARTU TERSIMPAN: " + kartu.getName()); // debug
            if (kartu != null) {
                try {
                    Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                    CardController cardController = (CardController) cardPane.getProperties().get("controller");
                    cardController.setCard(kartu);
                    deckAktifPane.getChildren().add(cardPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
