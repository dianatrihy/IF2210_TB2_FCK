package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.controller.CustomLadangController;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Player;

import java.io.IOException;

public class ShowLadang {
    private CustomLadangController controller;

    public ShowLadang(Player p, CustomLadangController controller) {
        this.controller = controller;
        updateLadang(p);
    }

    public void updateLadang(Player p) {
        System.out.println("PRINT LADANGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");

        controller.generateCustomPanes(p);
    }
}

