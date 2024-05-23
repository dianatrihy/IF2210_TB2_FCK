package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;
import org.example.if2210_tb2_fck.model.Produk;
import org.example.if2210_tb2_fck.model.DeckAktif;


public class InstantHarvestEffect extends Item implements ItemEffect {
    private DeckAktif deckAktif;

    public InstantHarvestEffect(DeckAktif deckAktif) {
        super("Instant Harvest");
        this.deckAktif = deckAktif;

    }

    @Override
    public void apply(Hewan hewan) {
        Produk produk = new Produk(hewan.getName());
        deckAktif.simpanAuto(produk);
    }

    @Override
    public void apply(Tanaman tanaman) {
        Produk produk = new Produk(tanaman.getName());
        deckAktif.simpanAuto(produk);

    }
}
