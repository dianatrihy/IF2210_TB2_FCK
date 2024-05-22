package org.example.if2210_tb2_fck.model;


public class Kartu {
    private String nama;
    private boolean diLadang;
    private String jenis; //MakhlukHidup, Produk, Item

    public Kartu(String name, String jenis) {
        this.nama = name;
        this.diLadang = false;
        this.jenis = jenis;
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

    public void setDiLadang(boolean diLadang){
        this.diLadang = diLadang;
    }

    public String getJenis(){
        return this.jenis;
    }
}
