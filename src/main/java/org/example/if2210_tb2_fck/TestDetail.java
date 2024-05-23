package org.example.if2210_tb2_fck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.if2210_tb2_fck.controller.HewanDetailController;
import org.example.if2210_tb2_fck.controller.TanamanDetailController;
import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;

import java.io.IOException;

public class TestDetail extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KartuTanaman.fxml"));
        Scene scene = new Scene(loader.load());

        TanamanDetailController controller = loader.getController();
        Tanaman tanaman = new Tanaman("StrawberrySeeds");
        tanaman.addItem("Accelerate");
        tanaman.addItem("Protected");
        tanaman.addItem("Protected");
        tanaman.addItem("Another1");
        tanaman.addItem("Another2");
        tanaman.addItem("Another3"); // Example item
        controller.setTanaman(tanaman);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tanaman Details");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
