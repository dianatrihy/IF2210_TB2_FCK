package org.example.if2210_tb2_fck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.if2210_tb2_fck.controller.GameManagerController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OOP-GACOR.fxml"));
        AnchorPane mainView = (AnchorPane) fxmlLoader.load();
        GameManagerController controller = fxmlLoader.getController();
        controller.setRootPane(mainView);

        Scene scene = new Scene(mainView, 1069, 645);
        stage.setTitle("PENANGKAL PETIR ⚡⚔️⚡");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}