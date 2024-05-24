package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
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
                    panes[i].getChildren().add(cardPane);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}