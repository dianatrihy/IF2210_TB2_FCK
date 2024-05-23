package org.example.if2210_tb2_fck.model;

public class Omnivora extends Hewan{
    public Omnivora(String name){
        super(name);
    }

    @Override
    public boolean canEat(Produk makanan){
        return true;
    }
}
