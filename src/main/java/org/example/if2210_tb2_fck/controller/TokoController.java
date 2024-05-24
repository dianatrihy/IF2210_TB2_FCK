package org.example.if2210_tb2_fck.controller;

import java.io.IOException;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Toko;
import org.example.if2210_tb2_fck.model.Kartu;

public class TokoController {
    private DragAndDrop dad;
    private Player player;

    @FXML
    private GridPane cardGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button backButton;

    @FXML
    private Button jualButton;

    @FXML
    private void handleJualButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Jual.fxml"));
            JualController jualController = new JualController();
            jualController.setPlayer(this.player);

            loader.setController(jualController);
            Parent jualRoot = loader.load();
            Scene jualScene = new Scene(jualRoot);

            Stage stage = (Stage) jualButton.getScene().getWindow();
            stage.setScene(jualScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int MAX_COLUMNS = 5;

    public TokoController() {
        this.dad = new DragAndDrop();
    }

    @FXML
    public void initialize() throws IOException {
        handleShowToko();
    }

    private void handleShowToko() throws IOException {
        Map<String, Integer> product = Toko.getInstance().getStockMap();
        int row = 0;
        int col = 0;

        for (String key : product.keySet()) {
            if (product.get(key) > 0) {
                Pane cardPane = dad.loadCard("/org/example/if2210_tb2_fck/Card.fxml");
                CardController cardController = (CardController) cardPane.getProperties().get("controller");
                cardController.setProductCard(key);

                // Add stock information as label below card pane
                int stock = product.get(key);
                Label stockLabel = new Label("Stock: " + stock);
                stockLabel.setStyle("-fx-text-fill: white;");
                cardGrid.add(stockLabel, col, row + 1);

                // Add buy button
                Button buyButton = new Button("Buy (" + Toko.getInstance().getPrice(key) + "$)");
                buyButton.setOnAction(event -> handlePlayerBuying(key));
                cardGrid.add(buyButton, col, row + 2);

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

    private void handlePlayerBuying(String productKey) {
        Kartu boughtCard = Toko.getInstance().playerBuying(productKey);
        Integer price = Toko.getInstance().getPrice(productKey);
        this.player.beli(boughtCard, price);
        try {
            handleShowToko();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
