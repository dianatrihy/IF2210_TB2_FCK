package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.Hewan;

import java.util.Map;

public class ShowDetailHewan implements ICommand{
    private Hewan hewan;

    public ShowDetailHewan(Hewan hewan) {
        this.hewan = hewan;
    }

    public void cetakItem() {
        Map<String, Integer> item_aktif = hewan.getItem();
        for (Map.Entry<String, Integer> entry : item_aktif.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
    }

    @Override
    public void execute() {
        System.out.println("Detail Hewan");
        System.out.println(hewan.getName());
        System.out.println("Berat: " + hewan.getBerat());
        System.out.println("Item Aktif: ");
        cetakItem();
    }
}
