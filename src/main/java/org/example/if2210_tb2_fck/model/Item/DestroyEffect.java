package org.example.if2210_tb2_fck.model.Item;

import org.example.if2210_tb2_fck.model.Hewan;
import org.example.if2210_tb2_fck.model.Tanaman;
import org.example.if2210_tb2_fck.model.Field;

public class DestroyEffect extends Item implements ItemEffect {
    private Field field;
    private int row;
    private int col;

    public DestroyEffect(Field field, int row, int col) {
        super("Destroy");
        this.field = field;
        this.row = row;
        this.col = col;
    }

    @Override
    public void apply(Hewan hewan) {
        field.removeKartu(row, col);
    }

    @Override
    public void apply(Tanaman tanaman) {
        field.removeKartu(row, col);
    }
}
