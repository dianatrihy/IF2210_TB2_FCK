package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;

import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;

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

    // Khusus untuk ladang
    // untuk handle change icon ke produk jika sudah siap panen
    public void setCardKW(MakhlukHidup mh){
        String prodname = mh.getNamaProdukKW();
        String basePath = "/org/example/if2210_tb2_fck/Assets/Produk/";
        String fullPath = basePath + prodname + ".png";
        System.out.println("Looking for resource: " + fullPath); // Debug line
        URL resource = getClass().getResource(fullPath);
        if (resource == null) {
            throw new RuntimeException("Resource not found: " + fullPath);
        }
        cardName.setText(prodname);
        cardImage.setImage(new Image(resource.toString()));
    }

    public void setProductCard(String name) {
        cardName.setText(name);
        cardImage.setImage(new Image(new Kartu(name, "Produk").getIcon()));
    }
}
