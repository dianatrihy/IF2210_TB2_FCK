package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.if2210_tb2_fck.command.Panen;
import org.example.if2210_tb2_fck.model.Tanaman;

import java.util.Map;

public class TanamanDetailController {
    @FXML
    private ImageView tanamanImageView;
    @FXML
    private Text tanamanNameText;
    @FXML
    private Text tanamanWeightText;
    @FXML
    private Text tanamanItemsText;
    @FXML
    private Button panenButton;
    @FXML
    private Button closeButton;

    private Tanaman tanaman;

    public void setTanaman(Tanaman tanaman) {
        this.tanaman = tanaman;
        updateTanamanDetails();
    }

    private void updateTanamanDetails() {
        if (tanaman != null) {
            tanamanNameText.setText(tanaman.getName());
            tanamanWeightText.setText("Umur: " + tanaman.getUmur());

            StringBuilder items = new StringBuilder("");
            for (Map.Entry<String, Integer> entry : tanaman.getItem().entrySet()) {
                if (entry.getValue() > 0) {
                    items.append(" > ").append(entry.getKey()).append(" (").append(entry.getValue()).append(")\n");
                }
            }
            tanamanItemsText.setText(items.toString());

            tanamanImageView.setImage(new Image(tanaman.getIcon()));
        }
    }

    @FXML
    private void handlePanenButtonAction() {
        // Implement panen action
        System.out.println("Panen action triggered");
    }

    @FXML
    private void handleCloseButtonAction() {
        // Implement close action
        System.out.println("Close action triggered");
    }
}
