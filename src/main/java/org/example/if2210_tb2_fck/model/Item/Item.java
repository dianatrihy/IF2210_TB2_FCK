package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.Tanaman;

public class Item extends Kartu implements ItemEffect {
    public Item(String name) {
        super(name, "Item");
    }

    @Override
    public void apply(Hewan hewan) {
        // Implementasi default yang kosong
    }

    @Override
    public void apply(Tanaman tanaman) {
        // Implementasi default yang kosong
    }

    public void use(Hewan hewan) {
        apply(hewan);
    }

    public void use(Tanaman tanaman) {
        apply(tanaman);
    }

}
