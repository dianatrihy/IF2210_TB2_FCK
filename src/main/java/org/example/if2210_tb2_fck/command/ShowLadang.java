package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.controller.CustomLadangController;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.Player;

import java.io.IOException;

public class ShowLadang {
    private CustomLadangController controller;

    public ShowLadang(Player p, CustomLadangController controller) {
        this.controller = controller;
        updateLadang(p);
    }

    public void updateLadang(Player p) {
        org.example.if2210_tb2_fck.model.Field ladangplayer = p.getLadang();
        int row = ladangplayer.getRow();
        int col = ladangplayer.getCol();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Kartu kartu = ladangplayer.retrieveKartu(i, j);
                if(kartu != null){
                    try {
                        controller.setCardToPane(i, j, kartu);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
