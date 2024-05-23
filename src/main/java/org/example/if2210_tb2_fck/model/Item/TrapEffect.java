package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.Tanaman;
import org.example.if2210_tb2_fck.model.DeckAktif;

public class TrapEffect extends Item implements ItemEffect {
    private DeckAktif deckAktif;

    public TrapEffect(DeckAktif deckAktif) {
        super("Trap");
        this.deckAktif = deckAktif;
    }

    @Override
    public void apply(Hewan hewan) {
        Kartu kartu = new Kartu("Beruang", "Makhluk Hidup");
        deckAktif.simpanAuto(kartu);

    }

    @Override
    public void apply(Tanaman tanaman) {
        Kartu kartu = new Kartu("Beruang", "Makhluk Hidup");
        deckAktif.simpanAuto(kartu);
    }


}