package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.Player;

import org.example.if2210_tb2_fck.controller.DragAndDrop;

public class ShuffleController {

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Button shuffleButton;

    private Player player;
    private DragAndDrop dad;

    public ShuffleController(){
        this.dad = new DragAndDrop();
    }

    public ShuffleController(Player player){
        this.dad = new DragAndDrop();
        this.player = player;
    }

    @FXML
    public void initialize() throws IOException {
        if (player != null){
            handleShuffle();
        }
    }

    @FXML
    public void handleShuffle() throws IOException {
        List<Kartu> shuffledCards = player.getDeckInventory().shuffleDeck();

        setCardToPane(pane1, shuffledCards.get(0));
        setCardToPane(pane2, shuffledCards.get(1));
        setCardToPane(pane3, shuffledCards.get(2));
        setCardToPane(pane4, shuffledCards.get(3));
    }

    private void setCardToPane(Pane pane, Kartu kartu) throws IOException {
        Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
        CardController cardController = (CardController) cardPane.getProperties().get("controller");
        cardController.setCard(kartu);
        pane.getChildren().clear();
        pane.getChildren().add(cardPane);
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
