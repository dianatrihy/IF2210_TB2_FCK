package org.example.if2210_tb2_fck.model;

public class Herbivora extends Hewan {
    public Herbivora(String name){
        super(name);
    }
    @Override
    public boolean canEat(Produk makanan){
        String name = makanan.getName();
        return name.equals("Jagung") || name.equals("Strawberry") || name.equals("Pumpkin");
    }
}
