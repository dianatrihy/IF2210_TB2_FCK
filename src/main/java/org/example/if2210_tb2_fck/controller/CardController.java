package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private ImageView cardImage;

    public ImageView getCardImage() {
        return cardImage;
    }

    public void setImage(String imageUrl) {
        cardImage.setImage(new javafx.scene.image.Image(imageUrl));
    }
}
