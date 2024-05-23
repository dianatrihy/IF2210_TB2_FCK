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
            // Load the Jual.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/Jual.fxml"));
            System.out.println("hi");
            Parent jualRoot = loader.load();
            JualController jualController = new JualController();
            
            // Create a new Player object (replace this with your actual player initialization logic)
            Player player = new Player("Player 1");
            
            // Pass the Player object to the JualController
            jualController.setPlayer(player);
            System.out.println("Player passed to JualController: " + jualController.getPlayer().getNama());
            loader.setController(jualController);

            // Create a new scene
            Scene jualScene = new Scene(jualRoot);

            // Get the stage from the button
            Stage stage = (Stage) jualButton.getScene().getWindow();

            // Set the scene and show the stage
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
        Map<String, Integer> product = Toko.getInstance(0).getStockMap();
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
                Button buyButton = new Button("Buy (" + Toko.getInstance(0).getPrice(key) + "$)");
                buyButton.setOnAction(event -> handleBuy(key));
                cardGrid.add(buyButton, col, row + 2);

                cardGrid.add(cardPane, col, row);

                col++;
                if (col == MAX_COLUMNS) {
                    col = 0;
                    row += 3; // Increment row by 3 to leave space for stock, button, and next card
                }
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void handleBuy(String productKey) {
        
    }
}
