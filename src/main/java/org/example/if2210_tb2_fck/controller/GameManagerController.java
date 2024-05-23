package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;

//import org.example.if2210_tb2_fck.model.GameManager;
import javafx.stage.Stage;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Toko;

public class GameManagerController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button startButton;

//    private GameManager game_manager;
    private Player player1;
    private Player player2;
    private int current_turn;
    private static final int MAX_TURNS = 20;
    private Toko toko;

    public GameManagerController(){
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.current_turn = 1;
        this.toko = Toko.getInstance(0); // gatau
    }

    @FXML
    public void initialize() {
        startButton.setOnAction(event -> {
            try {
                startTurn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
//        mainPane.getChildren().setAll(shufflePane);
        Stage stage = new Stage();
        stage.setScene(new Scene(shufflePane));
        stage.showAndWait();
        showMainView();
    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/OOP-GACOR.fxml"));
        Pane mainView = loader.load();
        mainPane.getChildren().setAll(mainView);
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
        // to-implement
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
        // to-implement
        System.out.println("Bear attack!");
    }

    private void freeAction(){
        // to-implement
        System.out.println("Free action phase...");
    }
}
