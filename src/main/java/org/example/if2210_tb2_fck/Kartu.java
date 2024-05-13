package org.example.if2210_tb2_fck;


public class Kartu {
    private String nama;
    private boolean diLadang;

    public Kartu(){
        this.nama = null;
        this.diLadang = false;
    }

    public Kartu(String name) {
        this.nama = name;
        this.diLadang = false;
    }

    public String getName() {
        return this.nama;
    }
}
