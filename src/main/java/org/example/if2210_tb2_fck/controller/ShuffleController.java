package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

import javafx.stage.Stage;
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
    private boolean[] cardClicked;

    public ShuffleController(){
        this.dad = new DragAndDrop();
        this.cardClicked = new boolean[4];
        initialize();
    }

    public ShuffleController(Player player) throws IOException {
        this.dad = new DragAndDrop();
        this.player = player;
        this.cardClicked = new boolean[4];
        initialize();
    }

    @FXML
    public void initialize() {
        if (shuffleButton != null) {
            shuffleButton.setOnAction(event -> {
                try {
                    handleShuffle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    public void handleShuffle() throws IOException {
        List<Kartu> shuffledCards = player.getDeckInventory().shuffleDeck();

        setCardToPane(pane1, shuffledCards.get(0), 0);
        setCardToPane(pane2, shuffledCards.get(1), 1);
        setCardToPane(pane3, shuffledCards.get(2), 2);
        setCardToPane(pane4, shuffledCards.get(3), 3);
    }

    private void setCardToPane(Pane pane, Kartu kartu, int index) throws IOException {
        Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
        CardController cardController = (CardController) cardPane.getProperties().get("controller");
        cardController.setCard(kartu);
        pane.getChildren().clear();
        pane.getChildren().add(cardPane);

        pane.setOnMouseClicked(event -> {
            player.simpanAutoDeckAktif(kartu);
            player.deleteKartuDeckInventory(kartu);
            pane.getChildren().clear();
            cardClicked[index] = true;
            if (player.getDeckAktif().isFull() || allCardsClicked()){
                closeShuffle();
            } else {
                shuffleButton.setVisible(false);
            }
        });
    }

    private boolean allCardsClicked(){
        for (boolean b : cardClicked){
            if (!b){
                return false;
            }
        }
        return true;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    private void closeShuffle() {
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.close();
    }
}
