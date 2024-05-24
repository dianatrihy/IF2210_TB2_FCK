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
        producer_product.put("hiu darat", "SharkFin");
        producer_product.put("sapi", "susu");
        producer_product.put("domba", "DagingDomba");
        producer_product.put("kuda", "DagingKuda");
        producer_product.put("ayam", "telur");
        producer_product.put("beruang", "DagingBeruang");
        producer_product.put("biji jagung", "Jaguung");
        producer_product.put("biji labu", "pumpkin");
        producer_product.put("biji stroberi", "strawberry");

        weight_map.put("SharkFin", 12);
        weight_map.put("susu", 4);
        weight_map.put("DagingDomba", 6);
        weight_map.put("DagingKuda", 8);
        weight_map.put("telur", 2);
        weight_map.put("DagingBeruang", 12);
        weight_map.put("Jaguung", 10);
        weight_map.put("pumpkin", 10);
        weight_map.put("strawberry", 5);

        type_map.put("SharkFin", 0);
        type_map.put("susu", 0);
        type_map.put("DagingDomba", 0);
        type_map.put("DagingKuda", 0);
        type_map.put("telur", 0);
        type_map.put("DagingBeruang", 0);
        type_map.put("Jaguung", 1);
        type_map.put("pumpkin", 1);
        type_map.put("strawberry", 1);
        
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