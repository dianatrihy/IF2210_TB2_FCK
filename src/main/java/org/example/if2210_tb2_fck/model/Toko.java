package org.example.if2210_tb2_fck.model;

import java.util.*;

public class Toko {
    private static Toko instance; // Singleton
    private static Map<String, Integer> catalog_price;
    public static Map<String, Integer> catalog_stock = new HashMap<>();

    static {
        catalog_price = new HashMap<>();
        populateCatalogPrice();
    }

    private Toko(int loadOption) {
        if (loadOption == 1) {
        } else {
            defaultPopulateCatalogStock();
        }
    }

    public static Toko getInstance(int loadOption) {
        if (instance == null) {
            instance = new Toko(loadOption);
        }
        return instance;
    }

    private static void populateCatalogPrice() {
        catalog_price.put("sirip hiu", 500);
        catalog_price.put("susu", 100);
        catalog_price.put("daging domba", 120);
        catalog_price.put("daging kuda", 150);
        catalog_price.put("telur", 50);
        catalog_price.put("daging beruang", 500);
        catalog_price.put("jagung", 150);
        catalog_price.put("labu", 500);
        catalog_price.put("stroberi", 350);
    
    }

    private void defaultPopulateCatalogStock() {
        for (String key : catalog_price.keySet()) {
            catalog_stock.put(key, 0);
        }
    }

    public void buy() {}

    public void sell() {}
}
