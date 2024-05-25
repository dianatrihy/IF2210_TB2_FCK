package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.example.if2210_tb2_fck.command.Panen;
import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.controller.GameManagerController;

import java.net.URL;
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
    private Panen panenCommand;
    private Player player;

    public void setHewan(Hewan hewan, Player p, Panen panenCommand) {
        this.hewan = hewan;
        this.player = p;
        this.panenCommand = panenCommand;
        updateHewanDetails();
    }

    private void updateHewanDetails() {
        if (hewan != null) {
            String namaharvest = hewan.getNamaProdukKW();
            String basePath = "/org/example/if2210_tb2_fck/Assets/Produk/";
            String fullPath = basePath + namaharvest + ".png";
            URL resource = getClass().getResource(fullPath);
            if(hewan.siapHarvest()){
                hewanNameText.setText(namaharvest);
            }
            else{
                hewanNameText.setText(hewan.getName());
            }
            hewanWeightText.setText("Berat: " + hewan.getBerat());

            StringBuilder items = new StringBuilder("");
            for (Map.Entry<String, Integer> entry : hewan.getItem().entrySet()) {
                if (entry.getValue() > 0) {
                    items.append(" > ").append(entry.getKey()).append(" (").append(entry.getValue()).append(")\n");
                }
            }
            hewanItemsText.setText(items.toString());
            if(hewan.siapHarvest()){
                hewanImageView.setImage(new Image(resource.toString()));
            }
            else{
                hewanImageView.setImage(new Image(hewan.getIcon()));
            }
            panenButton.setManaged(hewan.siapHarvest());
            panenButton.setVisible(hewan.siapHarvest());
        }
    }

    @FXML
    private void handlePanenButtonAction() {
        panenCommand.panenMakhlukHidup(player, hewan);
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
