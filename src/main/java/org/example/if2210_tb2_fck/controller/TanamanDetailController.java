package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.example.if2210_tb2_fck.command.Panen;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Tanaman;

import java.net.URL;
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
    private Panen panenCommand;
    private Player player;

    public void setTanaman(Tanaman tanaman, Player p, Panen panenCommand) {
        this.tanaman = tanaman;
        this.player = p;
        this.panenCommand = panenCommand;
        updateTanamanDetails();
    }

    private void updateTanamanDetails() {
        if (tanaman != null) {
            String namaharvest = tanaman.getNamaProdukKW();
            String basePath = "/org/example/if2210_tb2_fck/Assets/Produk/";
            String fullPath = basePath + namaharvest + ".png";
            URL resource = getClass().getResource(fullPath);
            if(tanaman.siapHarvest()){
                tanamanNameText.setText(namaharvest);
            }
            else{
                tanamanNameText.setText(tanaman.getName());
            }
            tanamanWeightText.setText("Umur: " + tanaman.getUmur());

            StringBuilder items = new StringBuilder("");
            for (Map.Entry<String, Integer> entry : tanaman.getItem().entrySet()) {
                if (entry.getValue() > 0) {
                    items.append(" > ").append(entry.getKey()).append(" (").append(entry.getValue()).append(")\n");
                }
            }
            tanamanItemsText.setText(items.toString());
            if(tanaman.siapHarvest()){
                tanamanImageView.setImage(new Image(resource.toString()));
            }
            else{
                tanamanImageView.setImage(new Image(tanaman.getIcon()));
            }
            panenButton.setManaged(tanaman.siapHarvest());
            panenButton.setVisible(tanaman.siapHarvest());
        }
    }

    @FXML
    private void handlePanenButtonAction() {
        panenCommand.panenMakhlukHidup(player, tanaman);
        System.out.println("Panen action triggered");
    }

    @FXML
    private void handleCloseButtonAction() {
        closeButton.setOnAction(event -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
        System.out.println("Close action triggered");
    }
}
