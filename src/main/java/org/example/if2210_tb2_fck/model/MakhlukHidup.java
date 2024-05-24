package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.model.Item.ProtectEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakhlukHidup extends Kartu{
    private String type; //Hewan, Tanaman
    private Map<String, Integer> item_aktif;

    //constructor
    public MakhlukHidup(String name, String type){
        super(name, "Makhluk Hidup");
        this.type = type;
        this.item_aktif = new HashMap<>();
    }

    public String getType(){
        return type;
    }

    public Map<String, Integer> getItem(){
        return item_aktif;
    }

    public void addItem(String item){
        if (item_aktif.containsKey(item)){
            item_aktif.put(item, item_aktif.get(item)+1);
        } else {
            item_aktif.put(item, 1);
        };
    }

//    public void removeItem(String item){
//        item_aktif.remove(item);
//    }

    public boolean isProtected() {
        return (item_aktif.containsKey("Protect"));
    }

    // cek apakah dia punya trap terpasang
    public boolean hasTrap() {
        return (item_aktif.containsKey("BearTrap"));
    }

    public void setProtected(boolean isProtected) {
        if (isProtected) {
            if (!item_aktif.containsKey("Protect")) {
                this.addItem("Protect");
            }
        } else {
            item_aktif.remove("Protect");
        }
    }

    public String getProdukName(){
        return switch (this.getName()) {
            case "Hiu" -> "SharkFin";
            case "Sapi" -> "Susu";
            case "Domba" -> "DagingDomba";
            case "Kuda" -> "DagingKuda";
            case "Ayam" -> "Telur";
            case "Beruang" -> "DagingBeruang";
            case "CornSeeds" -> "Jagung";
            case "PumpkinSeeds" -> "Pumpkin";
            case "StrawberrySeeds" -> "Strawberry";
            default -> null;
        };
    }

    public Produk changeToProduk(){
        Produk produk = new Produk(this.getProdukName());
        this.type = null;
        this.item_aktif = null;
        return produk;
    }

    public boolean siapHarvest() {
        return false; 
    }

}
