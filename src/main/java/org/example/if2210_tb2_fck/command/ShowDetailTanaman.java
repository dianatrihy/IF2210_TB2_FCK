package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.model.Tanaman;

import java.util.Map;

public class ShowDetailTanaman implements ICommand{
    private Tanaman tanaman;

    public ShowDetailTanaman(Tanaman tanaman) {
        this.tanaman = tanaman;
    }

    public void cetakItem() {
        Map<String, Integer> item_aktif = tanaman.getItem();
        for (Map.Entry<String, Integer> entry : item_aktif.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
    }

    @Override
    public void execute() {
        System.out.println("Detail Tanaman");
        System.out.println(tanaman.getName());
        System.out.println("Umur: " + tanaman.getUmur());
        System.out.println("Item Aktif: ");
        cetakItem();
    }
}
