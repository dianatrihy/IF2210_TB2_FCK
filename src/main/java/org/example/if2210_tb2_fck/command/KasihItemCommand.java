package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.*;
import org.example.if2210_tb2_fck.model.Item.*;

public class KasihItemCommand implements ICommand {
    private Player player;
    private Integer row;
    private Integer col;
    private Item item;

    public KasihItemCommand(Player player, Integer row, Integer col, Item item) {
        this.player = player;
        this.row = row;
        this.col = col;
        this.item = item;
    }
    @Override
    public void execute() {
        Field ladang = player.getLadang();
        Kartu card = ladang.retrieveKartu(row, col);
        if (card != null) {
            MakhlukHidup makhlukHidup = (MakhlukHidup) card;
            player.kasihItem(item, makhlukHidup);
        } else {
            System.out.println("Maaf, tidak ada kartu yang dipilih.");
        }
    }
}
