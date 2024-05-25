package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;

public class DelayEffect extends Item implements ItemEffect {
    public DelayEffect() {
        super("DELAY");
    }
    @Override
    public void apply(Hewan hewan) {
        hewan.kurangiBerat(5);
    }

    @Override
    public void apply(Tanaman tanaman) {
        tanaman.kurangiUmur(2);
    }
}