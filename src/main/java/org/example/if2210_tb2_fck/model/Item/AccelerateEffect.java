package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;

public class AccelerateEffect extends Item implements ItemEffect {
    public AccelerateEffect() {
        super("ACCELERATE");
    }
    @Override
    public void apply(Hewan hewan) {
        hewan.addBerat(8);
    }

    @Override
    public void apply(Tanaman tanaman) {
        tanaman.addUmur(2);
    }
}
