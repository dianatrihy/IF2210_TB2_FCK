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

    private Toko() {
        defaultPopulateCatalogStock();
    }

    public static Toko getInstance() {
        if (instance == null) {
            instance = new Toko();
        }
        return instance;
    }

    private static void populateCatalogPrice() {
        catalog_price.put("SIRIP_HIU", 500);
        catalog_price.put("SUSU", 100);
        catalog_price.put("DAGING_DOMBA", 120);
        catalog_price.put("DAGING_KUDA", 150);
        catalog_price.put("TELUR", 50);
        catalog_price.put("DAGING_BERUANG", 500);
        catalog_price.put("JAGUNG", 150);
        catalog_price.put("LABU", 500);
        catalog_price.put("STROBERI", 350);
    
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

    public Produk playerBuying(String name) {
        catalog_stock.put(name, catalog_stock.get(name) - 1);
        return new Produk(name);
    }

    public void playerSelling(Kartu kartu) {
        String name = kartu.getName();
        catalog_stock.put(name, catalog_stock.get(name) + 1);
    }

    public void updateStock(Map<String, Integer> newStock){
        catalog_stock.clear();
        catalog_stock.putAll(newStock);
    }
    
}
