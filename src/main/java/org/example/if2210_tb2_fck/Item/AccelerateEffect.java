package org.example.if2210_tb2_fck.Item;

import org.example.if2210_tb2_fck.Hewan;
import org.example.if2210_tb2_fck.Tanaman;

public class AccelerateEffect implements ItemEffect {
    @Override
    public void apply(Hewan hewan) {
        hewan.addBerat(8);
    }

    @Override
    public void apply(Tanaman tanaman) {
        tanaman.addUmur(2);
    }
}
