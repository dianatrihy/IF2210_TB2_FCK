package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Produk;

public class Panen implements ICommand {
    private Player player;
    private Kartu kartu;

    public Panen(Kartu kartu) {
        this.kartu = kartu;
    }

    @Override
    public void execute() {
        if (!player.getDeckAktif().isFull()){
            MakhlukHidup makhluk = (MakhlukHidup) kartu;
            Produk produk = makhluk.changeToProduk();
        } else {
            System.out.println("Deck Aktif penuh.");
        }
        System.out.println("Panen");
    }
}
