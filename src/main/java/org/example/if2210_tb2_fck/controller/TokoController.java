package org.example.if2210_tb2_fck.controller;

import java.io.IOException;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import org.example.if2210_tb2_fck.model.Toko;

public class TokoController {
    private DragAndDrop dad;

    @FXML
    private GridPane cardGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button backButton;

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

    private void handleBuy(String productKey) {
        
    }
}
