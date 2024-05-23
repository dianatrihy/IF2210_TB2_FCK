package org.example.if2210_tb2_fck;

import java.io.IOException;

import org.example.if2210_tb2_fck.controller.CustomLadangController;
import org.example.if2210_tb2_fck.command.ShowLadang;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Field;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesShowLadang extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/if2210_tb2_fck/OOP-GACOR-KW.fxml"));
        Parent root = loader.load();
        CustomLadangController controller = loader.getController();

        // Create a Player object and populate its ladang with some Kartu objects
        Player player = new Player("Thea");
        MakhlukHidup mh1 = new MakhlukHidup("CornSeeds", "Tanaman");
        MakhlukHidup mh2 = new MakhlukHidup("PumpkinSeeds", "Tanaman");

        player.getLadang().addKartu(mh1, 0, 0);
        player.getLadang().addKartu(mh2, 0, 3);

        // Instantiate ShowLadang to update the ladang view
        ShowLadang showLadang = new ShowLadang(player, controller);

        // Optionally, if you need to regenerate the panes (for example, after changing rows/columns)
        controller.regeneratePanes();
        showLadang.updateLadang(player);

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
