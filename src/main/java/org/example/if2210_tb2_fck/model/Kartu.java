package org.example.if2210_tb2_fck.model;


import java.net.URL;

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

    public String getIcon() {
        String basePath = "/org/example/if2210_tb2_fck/Assets/";
        String subPath = "";
        if (jenis.equals("Makhluk Hidup")) {
            subPath = "MakhlukHidup/";
        } else if (jenis.equals("Produk")) {
            subPath = "Produk/";
        } else if (jenis.equals("Item")) {
            subPath = "Item/";
        }
        String fullPath = basePath + subPath + this.nama + ".png";
        System.out.println("Looking for resource: " + fullPath); // Debug line
        URL resource = getClass().getResource(fullPath);
        if (resource == null) {
            throw new RuntimeException("Resource not found: " + fullPath);
        }
        return resource.toString();
    }

    public String getNamaProdukKW(){
        String subPath = "";
        if (nama.equals("AYAM")) {
            subPath = "TELUR";
        } else if (nama.equals("BERUANG")) {
            subPath = "DAGING_BERUANG";
        } else if (nama.equals("DOMBA")) {
            subPath = "DAGING_DOMBA";
        } else if (nama.equals("KUDA")) {
            subPath = "DAGING_KUDA";
        } else if (nama.equals("BIJI_JAGUNG")) {
            subPath = "JAGUNG";
        } else if (nama.equals("BIJI_LABU")) {
            subPath = "LABU";
        } else if (nama.equals("BIJI_STROBERI")) {
            subPath = "STROBERI";
        } else if (nama.equals("HIU_DARAT")) {
            subPath = "SIRIP_HIU";
        } else if (nama.equals("SAPI")) {
            subPath = "SUSU";
        }
        return subPath;
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
