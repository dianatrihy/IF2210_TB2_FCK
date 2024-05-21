package org.example.if2210_tb2_fck.Item;

import org.example.if2210_tb2_fck.Hewan;
import org.example.if2210_tb2_fck.Tanaman;

public class DelayEffect implements ItemEffect {
    @Override
    public void apply(Hewan hewan) {
        hewan.kurangiBerat(5);
    }

    @Override
    public void apply(Tanaman tanaman) {
        tanaman.kurangiUmur(2);
    }
}