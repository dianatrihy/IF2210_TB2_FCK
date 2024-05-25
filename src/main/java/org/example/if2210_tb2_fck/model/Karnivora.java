package org.example.if2210_tb2_fck.model;

public class Karnivora extends Hewan{
    public Karnivora(String name){
        super(name);
    }
    @Override
    public boolean canEat(Produk makanan){
        String name = makanan.getName();
        return name.equals("DAGINGDOMBA") || name.equals("DAGINGKuda") || name.equals("DAGINGBERUANG") || name.equals("Susu") || name.equals("Telur") || name.equals("SharkFin");
    }
}
