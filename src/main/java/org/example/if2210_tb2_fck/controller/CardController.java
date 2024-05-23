package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.if2210_tb2_fck.model.Kartu;

public class CardController {

    @FXML
    private ImageView cardImage;

    @FXML
    private Text cardName;

    public ImageView getCardImage() {
        return cardImage;
    }

    public void setImage(String imageUrl) {
        cardImage.setImage(new javafx.scene.image.Image(imageUrl));
    }

    public void setCard(Kartu kartu) {
        cardName.setText(kartu.getName());
        cardImage.setImage(new Image(kartu.getIcon()));
    }
}
