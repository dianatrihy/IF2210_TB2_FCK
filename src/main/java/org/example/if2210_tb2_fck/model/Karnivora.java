package org.example.if2210_tb2_fck.model;

public class Karnivora extends Hewan{
    public Karnivora(String name){
        super(name);
    }
    @Override
    public boolean canEat(Produk makanan){
        String name = makanan.getName();
        return name.equals("DagingDomba") || name.equals("DagingKuda") || name.equals("DagingBeruang") || name.equals("Susu") || name.equals("Telur") || name.equals("SharkFin");
    }
}
