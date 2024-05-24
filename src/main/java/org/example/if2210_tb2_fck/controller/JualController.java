package org.example.if2210_tb2_fck.controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.example.if2210_tb2_fck.model.Toko;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Kartu;

public class JualController {
    private DragAndDrop dad;
    private Player player;

    @FXML
    private AnchorPane jualPane;

    @FXML
    private GridPane cardGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button backButton;

    @FXML
    private void handleBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Toko.fxml"));
            TokoController tokoController = new TokoController();
            tokoController.setPlayer(this.player);

            loader.setController(tokoController);
            Parent tokoRoot = loader.load();
            Scene tokoScene = new Scene(tokoRoot);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(tokoScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static final int MAX_COLUMNS = 5;

    public JualController() {
        this.dad = new DragAndDrop();
    }

    @FXML
    public void initialize() throws IOException {
        handleShowJual();
    }

    private void handleShowJual() throws IOException {
        System.out.println(this.player.getNama());
        List<Kartu> deck = player.getDeckAktif().getAllKartu();
        int row = 0;
        int col = 0;

        if (!deck.isEmpty()) {
            for (Kartu kartu : deck) {
                Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                CardController cardController = (CardController) cardPane.getProperties().get("controller");
                if (kartu.getJenis().equals("Produk")) {
                    cardController.setProductCard(kartu.getName());
                }
                // Add buy button
                Button jualButton = new Button("Jual (+" + Toko.getInstance().getPrice(kartu.getName()) + "$)");
                jualButton.setOnAction(event -> handlePlayerSell(kartu));
                cardGrid.add(jualButton, col, row + 2);
    
                cardGrid.add(cardPane, col, row);
    
                col++;
                if (col == MAX_COLUMNS) {
                    col = 0;
                    row += 3;
                }
            
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void handlePlayerSell(Kartu kartu) {
        Integer price = Toko.getInstance().getPrice(kartu.getName());
        this.player.jual(kartu, price);
        Toko.getInstance().playerSelling(kartu);
        try {
            cardGrid.getChildren().clear();
            handleShowJual();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
