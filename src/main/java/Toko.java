
import java.io.*;
import java.util.*;

public class Toko {
    private static Toko instance; // Singleton
    private static Map<String, Integer> catalog_price;
    public static Map<String, Integer> catalog_stock = new HashMap<>();

    static {
        catalog_price = new HashMap<>();
        populateCatalogPrice();
    }

    public Toko(int loadOption) {
        if (loadOption == 1) {
        } else {
            defaultPopulateCatalogStock();
        }
    }

    private static void populateCatalogPrice() {
        try (BufferedReader br = new BufferedReader(new FileReader("produk/produk.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    continue;
                }
                String productName = parts[1].trim();
                int price = Integer.parseInt(parts[2].trim());
                catalog_price.put(productName, price);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void defaultPopulateCatalogStock() {
        for (String key : catalog_price.keySet()) {
            catalog_stock.put(key, 0);
        }
    }

    public void buy() {}

    public void sell() {}
}
