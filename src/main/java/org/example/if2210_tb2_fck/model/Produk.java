package org.example.if2210_tb2_fck.model;

import java.util.*;

public class Produk extends Kartu {
    private static Map<String, String> producer_product;
    private static Map<String, Integer> weight_map;
    private static Map<String, Integer> type_map;
//    private String name;
    private Integer weight;
    private Integer type;

    static {
        producer_product = new HashMap<>();
        weight_map = new HashMap<>();
        type_map = new HashMap<>();
        populateMaps();
    }

    private static void populateMaps() {
        producer_product.put("hiu darat", "Sirip_Hiu");
        producer_product.put("sapi", "susu");
        producer_product.put("domba", "DAGING_DOMBA");
        producer_product.put("kuda", "DAGING_Kuda");
        producer_product.put("ayam", "telur");
        producer_product.put("beruang", "DAGING_BERUANG");
        producer_product.put("biji jagung", "Jagung");
        producer_product.put("biji labu", "Labu");
        producer_product.put("biji stroberi", "Stroberi");

        weight_map.put("Sirip_Hiu", 12);
        weight_map.put("susu", 4);
        weight_map.put("DAGING_DOMBA", 6);
        weight_map.put("DAGING_Kuda", 8);
        weight_map.put("telur", 2);
        weight_map.put("DAGING_BERUANG", 12);
        weight_map.put("Jagung", 10);
        weight_map.put("Labu", 10);
        weight_map.put("Stroberi", 5);

        type_map.put("Sirip_Hiu", 0);
        type_map.put("susu", 0);
        type_map.put("DAGING_DOMBA", 0);
        type_map.put("DAGING_Kuda", 0);
        type_map.put("telur", 0);
        type_map.put("DAGING_BERUANG", 0);
        type_map.put("Jagung", 1);
        type_map.put("Labu", 1);
        type_map.put("Stroberi", 1);
        
    }

    public Produk(String name) {
        super(name, "Produk");
        this.weight = weight_map.get(name);
        this.type = type_map.get(name);
    }

    public Produk createProduk(Kartu livingBeingName) {
        String name = producer_product.get(livingBeingName.getName());
        return new Produk(name);
    }

//    public String getName() {
//        return this.name;
//    }

    public Integer getWeight() {
        return this.weight;
    }

    public Integer getType() {
        return this.type;
    }
}