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
        producer_product.put("hiu darat", "sirip hiu");
        producer_product.put("sapi", "susu");
        producer_product.put("domba", "daging domba");
        producer_product.put("kuda", "daging kuda");
        producer_product.put("ayam", "telur");
        producer_product.put("beruang", "daging beruang");
        producer_product.put("biji jagung", "jagung");
        producer_product.put("biji labu", "labu");
        producer_product.put("biji stroberi", "stroberi");

        weight_map.put("sirip hiu", 12);
        weight_map.put("susu", 4);
        weight_map.put("daging domba", 6);
        weight_map.put("daging kuda", 8);
        weight_map.put("telur", 2);
        weight_map.put("daging beruang", 12);
        weight_map.put("jagung", 10);
        weight_map.put("labu", 10);
        weight_map.put("stroberi", 5);

        type_map.put("sirip hiu", 0);
        type_map.put("susu", 0);
        type_map.put("daging domba", 0);
        type_map.put("daging kuda", 0);
        type_map.put("telur", 0);
        type_map.put("daging beruang", 0);
        type_map.put("jagung", 1);
        type_map.put("labu", 1);
        type_map.put("stroberi", 1);
        
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
