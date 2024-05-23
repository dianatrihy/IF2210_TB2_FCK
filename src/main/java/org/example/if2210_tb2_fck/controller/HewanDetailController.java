package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.if2210_tb2_fck.command.Panen;
import org.example.if2210_tb2_fck.model.Hewan;

import java.util.Map;

public class HewanDetailController {
    @FXML
    private ImageView hewanImageView;
    @FXML
    private Text hewanNameText;
    @FXML
    private Text hewanWeightText;
    @FXML
    private Text hewanItemsText;
    @FXML
    private Button panenButton;
    @FXML
    private Button closeButton;

    private Hewan hewan;

    public void setHewan(Hewan hewan) {
        this.hewan = hewan;
        updateHewanDetails();
    }

    private void updateHewanDetails() {
        if (hewan != null) {
            hewanNameText.setText(hewan.getName());
            hewanWeightText.setText("Berat: " + hewan.getBerat());

            StringBuilder items = new StringBuilder("");
            for (Map.Entry<String, Integer> entry : hewan.getItem().entrySet()) {
                if (entry.getValue() > 0) {
                    items.append(" > ").append(entry.getKey()).append(" (").append(entry.getValue()).append(")\n");
                }
            }
            hewanItemsText.setText(items.toString());

            hewanImageView.setImage(new Image(hewan.getIcon()));
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
