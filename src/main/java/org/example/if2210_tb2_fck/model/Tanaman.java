package org.example.if2210_tb2_fck.model;

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

    public void addUmur(int n) {
        this.umur += n;
    }

    public void kurangiUmur(int n) {
        this.umur -= n;
    }

//    public boolean siapHarvest(){
//        return umur >= umur_harvest;
//    }
}
