package org.example.if2210_tb2_fck.model;

import javax.xml.namespace.QName;

public class Tanaman extends MakhlukHidup {
    private Integer umur;

    // constructor
    public Tanaman(String name) {
        super(name, "Tanaman");
        this.umur = 0;
    }

    public Integer getUmur() {
        return umur;
    }

    public Integer getUmurHarvest(String name){
        return switch (name) {
            case "Jagung" -> 3;
            case "Labu" -> 5;
            case "Stroberi" -> 4;
            default -> 0;
        };
    }

    public void addUmur(int n) {
        this.umur += n;
    }

    public void kurangiUmur(int n) {
        this.umur -= n;
    }

    public boolean siapHarvest(){
        return umur >= getUmurHarvest(this.getName());
    }
}
