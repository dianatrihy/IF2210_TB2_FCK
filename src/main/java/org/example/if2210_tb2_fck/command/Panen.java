package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.MakhlukHidup;
import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Produk;


public class Panen {

    private Kartu kartu;
    
    public Panen(Kartu kartu) {
        this.kartu = kartu;
    }

    public void panenMakhlukHidup(Player player, MakhlukHidup kartu ) {
        if (!player.getDeckAktif().isFull()){
            Produk produk = kartu.changeToProduk();
            player.simpanAutoDeckAktif(produk);
            
        } else {
            System.out.println("Deck Aktif penuh.");
        }
        System.out.println("Panen");
    }
}
