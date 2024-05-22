package org.example.if2210_tb2_fck.model;


public class Kartu {
    private String nama;
    private boolean diLadang;
    private String jenis;

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

    public String getIcon(){
        String basePath = "D:/IF2210_TB2_FCK/src/main/resources/org/example/if2210_tb2_fck/Assets/";
        String subPath = "";
        if (jenis.equals("Hewan")) {
            subPath = "Hewan/";
        } else if (jenis.equals("Produk")) {
            subPath = "Produk/"; 
        } else if (jenis.equals("Tanaman")) {
            subPath = "Tanaman/";
        } else if (jenis.equals("Item")) {
            subPath = "Item/";
        }
        return basePath + subPath + this.nama + ".png";
    }

    public boolean getDiLadang(){
        return this.diLadang;
    }

    public String getJenis(){
        return this.jenis;
    }
}
