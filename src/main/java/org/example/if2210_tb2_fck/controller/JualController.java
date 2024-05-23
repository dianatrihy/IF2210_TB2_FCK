package org.example.if2210_tb2_fck.controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import org.example.if2210_tb2_fck.model.Toko;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Kartu;

public class JualController {
    private DragAndDrop dad;
    private Player player;

    @FXML
    private GridPane cardGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button backButton;

    private static final int MAX_COLUMNS = 5;

    public JualController() {
        this.dad = new DragAndDrop();
    }

    @FXML
    public void initialize() throws IOException {
        handleShowJual();
    }

    private void handleShowJual() throws IOException {
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
                Button jualButton = new Button("Jual (" + Toko.getInstance(0).getPrice(kartu.getName()) + "$)");
                jualButton.setOnAction(event -> handleSell(kartu));
                cardGrid.add(jualButton, col, row + 2);
    
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

    public Player getPlayer() {
        return this.player;
    }

    private void handleSell(Kartu kartu) {
        
    }
}
