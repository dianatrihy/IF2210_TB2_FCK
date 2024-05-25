package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;

public class ProtectEffect extends Item implements ItemEffect {
    public ProtectEffect() {
        super("PROTECT");
    }

    @Override
    public void apply(Hewan hewan) {
        hewan.setProtected(true);
    }

    @Override
    public void apply(Tanaman tanaman) {
        tanaman.setProtected(true);
    }
}
