package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.*;

public class KasihMakan implements ICommand{
    private Player player;
    private Integer row;
    private Integer col;
    private Produk produk;

    public KasihMakan(Player player, Integer row, Integer col, Produk produk) {
        this.player = player;
        this.row = row;
        this.col = col;
        this.produk = produk;
    }

    @Override
    public void execute() {
        Field ladang = player.getLadang();
        Kartu card = ladang.retrieveKartu(row, col);
        if (card != null) {
            MakhlukHidup makhluk = (MakhlukHidup) card;
            if (makhluk instanceof Hewan) {
                Hewan hewan = (Hewan) makhluk;
                hewan.addBerat(produk.getWeight());
            } else {
                System.out.println("Bukan merupakan kartu Hewan.");
            }
        } else {
            System.out.println("Ladang kosong atau tidak ada kartu terpilih.");
        }
    }
}
