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

    public void addBerat(Integer berat) {
        this.berat += berat;
    }

    public void kurangiBerat(Integer berat) {
        this.berat -= berat;
    }

//    public boolean siapHarvest(){
//        return berat >= berat_harvest;
//    }

//    public boolean canEat(Produk makanan) {return false;}
}
