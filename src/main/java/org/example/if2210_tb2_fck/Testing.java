package org.example.if2210_tb2_fck;
import java.io.IOException;

import org.example.if2210_tb2_fck.controller.CustomLadangController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Testing extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OOP-GACOR-KW.fxml"));
        CustomLadangController controller = new CustomLadangController(4,5);
        loader.setController(controller);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}
