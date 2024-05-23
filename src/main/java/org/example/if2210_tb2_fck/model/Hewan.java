package org.example.if2210_tb2_fck.model;

public class Hewan extends MakhlukHidup {
    private Integer berat;


    //constructor
    public Hewan(String name){
        super(name, "Hewan");
        this.berat = 0;
    }

    public Integer getBerat() {
        return berat;
    }

    public Integer getBeratHarvest(String name){
        return switch (name) {
            case "Hiu" -> 20;
            case "Sapi" -> 10;
            case "Domba" -> 12;
            case "Kuda" -> 14;
            case "Ayam" -> 5;
            case "Beruang" -> 25;
            default -> 0;
        };
    }

    public void addBerat(Integer berat) {
        this.berat += berat;
    }

    public void kurangiBerat(Integer berat) {
        this.berat -= berat;
    }

    @Override
    public boolean siapHarvest(){
        return berat >= getBeratHarvest(this.getName());
    }

    public boolean canEat(Produk makanan) {
        return false;
    }
}
