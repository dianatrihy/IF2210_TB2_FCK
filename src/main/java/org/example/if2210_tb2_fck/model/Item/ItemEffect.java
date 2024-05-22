package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.Hewan;
import org.example.if2210_tb2_fck.Tanaman;

public interface ItemEffect {
    void apply(Tanaman tanaman);
    void apply(Hewan hewan);
}
