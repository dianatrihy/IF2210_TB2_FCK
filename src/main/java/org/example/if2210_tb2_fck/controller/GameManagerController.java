package org.example.if2210_tb2_fck.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.if2210_tb2_fck.command.ShowLadang;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Toko;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Tanaman;

import java.io.IOException;
import java.util.Random;

public class GameManagerController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button startButton;

    @FXML
    private AnchorPane ladangContainer;

    private Player player1;
    private Player player2;
    private int current_turn;
    private static final int MAX_TURNS = 20;
//    private Toko toko;

    public GameManagerController(){
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.current_turn = 1;
//        this.toko = Toko.getInstance(0);
    }

    @FXML
    public void initialize() {
        try {
            loadLadang();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton.setOnAction(event -> {
            try {
                startTurn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadLadang() throws IOException {
        System.out.println("Loading Ladang.fxml");
        FXMLLoader ladangLoader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Ladang.fxml"));
        AnchorPane ladangPane = ladangLoader.load();
        CustomLadangController ladangController = ladangLoader.getController();

        System.out.println("LadangPane ID: " + ladangPane.getId());
        System.out.println("LadangController: " + (ladangController != null));
        System.out.println("LadangPane LayoutX: " + ladangPane.getLayoutX());
        System.out.println("LadangPane LayoutY: " + ladangPane.getLayoutY());
        System.out.println("LadangPane PrefWidth: " + ladangPane.getPrefWidth());
        System.out.println("LadangPane PrefHeight: " + ladangPane.getPrefHeight());

        ladangContainer.getChildren().setAll(ladangPane);

//        // Create a Player object and populate its ladang with some Kartu objects
        Player player = new Player("Thea");
//        MakhlukHidup mh1 = new MakhlukHidup("CornSeeds", "Tanaman");
//        MakhlukHidup mh2 = new MakhlukHidup("PumpkinSeeds", "Tanaman");
//        Tanaman tn1 = new Tanaman("PumpkinSeeds");
//        tn1.setUmur(10);
//        player.getLadang().addKartu(mh1, 0, 0);
//        player.getLadang().addKartu(mh2, 0, 3);
//        player.getLadang().addKartu(tn1, 0, 1);

        // Instantiate ShowLadang to update the ladang view
        System.out.println("Before regenerating panes");
        ladangController.generateCustomPanes();
        System.out.println("After regenerating panes");


        System.out.println("Ladang.fxml loaded and added to the main view");
    }

    public void handleStartButton() throws IOException {
        startTurn();
    }

    private void shuffleCards() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/shuffle.fxml"));
        Pane shufflePane = loader.load();
        ShuffleController shuffleController = loader.getController();
        shuffleController.setPlayer(getCurrentPlayer());
        shuffleController.handleShuffle();

        Stage stage = new Stage();
        stage.setScene(new Scene(shufflePane));
        stage.showAndWait();
        showMainView();
    }

    private void showMainView() throws IOException {
        System.out.println("Loading OOP-GACOR.fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/OOP-GACOR.fxml"));
        Pane mainView = loader.load();
        mainPane.getChildren().setAll(mainView);

        System.out.println("Loading DeckAktif.fxml");
        FXMLLoader deckLoader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/DeckAktif.fxml"));
        Pane deckPane = deckLoader.load();
        DeckAktifController deckController = deckLoader.getController();
        deckController.setDeckAktif(getCurrentPlayer().getDeckAktif());

        AnchorPane rootPane = (AnchorPane) mainView;
        rootPane.getChildren().add(deckPane);
    }

    public Player getCurrentPlayer(){
        return current_turn % 2 == 1 ? player1 : player2;
    }

    public Player getOtherPlayer(){
        return current_turn % 2 == 1 ? player2 : player1;
    }

    public void nextTurn() throws IOException {
        current_turn++;
        if (current_turn > MAX_TURNS){
            endGame();
        } else {
            startTurn();
        }
    }

    public int getCurrentTurn(){
        return current_turn;
    }

    private void endGame(){
        System.out.println("Game Over!");
    }

    public void startTurn() throws IOException {
        shuffleCards();
        if (bearAttackOccurs()){
            bearAttack();
        }
        freeAction();
    }

    private boolean bearAttackOccurs(){
        Random random = new Random();
        return random.nextBoolean();
    }

    private void bearAttack(){
        System.out.println("Bear attack!");
    }

    private void freeAction(){
        System.out.println("Free action phase...");
    }
}
