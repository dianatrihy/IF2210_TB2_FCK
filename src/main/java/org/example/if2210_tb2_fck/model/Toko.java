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
        catalog_price.put("SharkFin", 500);
        catalog_price.put("susu", 100);
        catalog_price.put("DagingDomba", 120);
        catalog_price.put("DagingKuda", 150);
        catalog_price.put("telur", 50);
        catalog_price.put("DagingBeruang", 500);
        catalog_price.put("Jagung", 150);
        catalog_price.put("pumpkin", 500);
        catalog_price.put("strawberry", 350);
    
    }

    private void defaultPopulateCatalogStock() {
        for (String key : catalog_price.keySet()) {
            catalog_stock.put(key, 1);
        }
    }

    public Map<String, Integer> getStockMap() {
        return catalog_stock;
    }

    public int getPrice(String key) {
        return catalog_price.get(key);
    }

    public void buy(String name) {
        catalog_stock.put(name, catalog_stock.get(name) - 1);
    }

    public void sell(String name) {
        catalog_stock.put(name, catalog_stock.get(name) + 1);
    }
}
