package org.example.if2210_tb2_fck.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.example.if2210_tb2_fck.command.BearAttack;
import org.example.if2210_tb2_fck.command.ShowLadang;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Toko;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Tanaman;
import org.example.if2210_tb2_fck.model.LoadState;

import java.io.IOException;
import java.util.Random;
import javafx.scene.text.Text;

public class GameManagerController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button startButton;

    @FXML
    private Text currentPlayerText;

    @FXML
    private Button nextButton;

    @FXML
    private Button ladangKuButton;

    @FXML
    private Button ladangLawanButton;

    @FXML
    private Button tokoButton;

    @FXML
    private Button loadStateButton;

    @FXML
    private AnchorPane Timer;

    @FXML
    private AnchorPane ladangContainer;

    @FXML
    private Text p1Money;

    @FXML
    private Text p2Money;

    private LoadState loadState;
    private Player player1;
    private Player player2;
    private int current_turn;
    private static final int MAX_TURNS = 20;
    private Toko toko;

    public GameManagerController(){
        this.player1 = new Player("Player 1");
        player1.setUang(400);
        this.player2 = new Player("Player 2");
        this.current_turn = 1;
        this.toko = Toko.getInstance();
    }

    @FXML
    public void initialize() {
        updatePlayerMoney(player1);
        updatePlayerMoney(player2);
        updateCurrentPlayerText();
        try {
            loadLadang(getCurrentPlayer());
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

        updateCurrentPlayerText();

        nextButton.setOnAction(event -> {
            try {
                System.out.println("Next button clicked!");
                nextTurn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ladangKuButton.setOnAction(event -> {
            try {
                loadLadang(getCurrentPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ladangLawanButton.setOnAction(event -> {
            try {
                loadLadang(getOtherPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        tokoButton.setOnAction(event -> {
            try {
                loadToko();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadStateButton.setOnAction(event -> {
            try {
                handleLoadStateButton();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void updatePlayerMoney(Player player) {
        if (player.getNama().equals("Player 1")) {
            p1Money.setText("" + player.getUang());
        } else {
            p2Money.setText("" + player.getUang());
        }
    }

    private void updateCurrentPlayerText(){
        System.out.println("Updating current player text..." + getCurrentTurn() + getCurrentPlayer().getNama()); // debug
        currentPlayerText.setText(getCurrentPlayer().getNama() + " (" + getCurrentTurn() + ")");
        System.out.println("DONE Updating current player text..." + getCurrentTurn() + getCurrentPlayer().getNama()); // debug
    }

    private void loadToko() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Toko.fxml"));
            TokoController tokoController = new TokoController();
            tokoController.setPlayer(getCurrentPlayer());
            tokoController.setGameManagerController(this);
    
            loader.setController(tokoController);
            Parent tokoRoot = loader.load();
            Scene tokoScene = new Scene(tokoRoot);
    
            Stage tokoStage = new Stage();
            tokoStage.setScene(tokoScene);
            tokoStage.setTitle("Toko");
            tokoStage.initOwner(mainPane.getScene().getWindow());
            tokoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTimer() throws IOException{
        System.out.println("Loading Timer.fxml");
        FXMLLoader timerLoader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Timer.fxml"));
        if (timerLoader != null) {
            System.out.println("Resource Timer fxml not found!");
        }
        System.out.println(timerLoader);
        AnchorPane timerPane = timerLoader.load();
        TimerController timerController = timerLoader.getController();

        System.out.println("TimerPane ID: " + timerPane.getId());
        System.out.println("Timercontroller: " + (timerController != null));

        Timer.getChildren().setAll(timerPane);
        BearAttack bearAttack = new BearAttack();
        bearAttack.bearAttackCommand(getCurrentPlayer(), timerController, this);


        System.out.println("Timer.fxml loaded and added to the main view");
    }
    
    private void bearAttack(){
        System.out.println("Bear attack!");
        try {
            loadTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLadang(Player player) throws IOException {
        System.out.println("Loading Ladang.fxml");
        FXMLLoader ladangLoader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Ladang.fxml"));
        if (getClass().getResource("/org/example/if2210_tb2_fck/Ladang.fxml") == null) {
            System.out.println("Resource not found!");
        }

        AnchorPane ladangPane = ladangLoader.load();
        CustomLadangController ladangController = ladangLoader.getController();

        ladangContainer.getChildren().setAll(ladangPane);

        // Create a Player object and populate its ladang with some Kartu objects
        Player player1 = new Player("Thea");
        MakhlukHidup mh1 = new MakhlukHidup("CornSeeds", "Tanaman");
        MakhlukHidup mh2 = new MakhlukHidup("PumpkinSeeds", "Tanaman");
        mh1.addItem("BearTrap");
        mh2.addItem("BearTrap");
        Tanaman tn1 = new Tanaman("StrawberrySeeds");
        tn1.addItem("BearTrap");
        // Tanaman tn2 = new Tanaman("PumpkinSeeds");

        // Tanaman tn3 = new Tanaman("PumpkinSeeds");
        // Tanaman tn4 = new Tanaman("PumpkinSeeds");
        // Tanaman tn5 = new Tanaman("PumpkinSeeds");

        tn1.setUmur(10);
        player.getLadang().addKartu(mh1, 0, 0);
        player.getLadang().addKartu(mh2, 0, 3);
        player.getLadang().addKartu(tn1, 1, 1);
        // player.getLadang().addKartu(tn2, 2, 1);
        // player.getLadang().addKartu(tn3, 3, 1);
        // player.getLadang().addKartu(tn4, 2, 3);
        // player.getLadang().addKartu(tn5, 3, 2);

        // Instantiate ShowLadang to update the ladang view
        ShowLadang showLadang = new ShowLadang(player, ladangController);
        showLadang.updateLadang(player);

        System.out.println("Ladang.fxml loaded and added to the main view");
    }

    private void loadLadangKW(Player player) throws IOException {
        System.out.println("Loading Ladang.fxml");
        FXMLLoader ladangLoader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Ladang.fxml"));
        if (getClass().getResource("/org/example/if2210_tb2_fck/Ladang.fxml") == null) {
            System.out.println("Resource not found!");
        }
    
        AnchorPane ladangPane = ladangLoader.load();
        CustomLadangController ladangController = ladangLoader.getController();
        Tanaman tnx = new Tanaman("PumpkinSeeds");
        player.getLadang().addKartu(tnx, 1, 2);
        System.out.println("LadangPane ID: " + ladangPane.getId());
        System.out.println("LadangController: " + (ladangController != null));
    
        ladangContainer.getChildren().setAll(ladangPane);
    
        // Menggunakan pemain yang diberikan sebagai argumen
        // untuk memperbarui ladang pada controller
        ladangController.regeneratePanes(player);
    
        System.out.println("LadangKWWWWWWWWWW.fxml loaded and added to the main view");
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

        updateCurrentPlayerText();
    }

    public Player getCurrentPlayer(){
        return current_turn % 2 == 1 ? player1 : player2;
    }

    public Player getOtherPlayer(){
        return current_turn % 2 == 1 ? player2 : player1;
    }

    public void nextTurn() throws IOException {
        System.out.println("Next turn..."); // debug
        current_turn++;
        updateCurrentPlayerText();
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
        loadLadangKW(getCurrentPlayer());
        if (bearAttackOccurs()){
            bearAttack();
            System.out.println("HAIHAI SELESAI KENA BERUANG YA");
        }
        freeAction();
    }

    private boolean bearAttackOccurs(){
        // Random random = new Random();
        // return random.nextBoolean();
        return false;
    }

    public void refreshLadang() {
        try {
            loadLadangKW(getCurrentPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void freeAction(){
        System.out.println("Free action phase...");
    }

    public void handleLoadStateButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/LoadState.fxml"));
        Pane loadStatePane = loader.load();
        LoadStateController loadStateController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(loadStatePane));
        stage.showAndWait();

        this.loadState = loadStateController.getLoadState();
        if (loadState == null){
            System.out.println("Load state failed!");
            return;
        }
        this.player1 = loadState.getPlayer1();
        this.player2 = loadState.getPlayer2();
        this.current_turn = loadState.getCurrentTurn();
        this.toko = loadState.getToko();
        System.out.println("Load state successful!" + getCurrentTurn()); // debug
        updateCurrentPlayerText();
        showMainView();
    }
}
