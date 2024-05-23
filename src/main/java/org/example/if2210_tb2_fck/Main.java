package org.example.if2210_tb2_fck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shuffle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1069, 645);
        stage.setTitle("PENANGKAL PETIR ⚡⚔️⚡");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}