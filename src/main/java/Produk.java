
import java.io.*;
import java.util.*;

public class Produk extends Kartu {
    private static Map<String, String> animal_product;
    private static Map<String, Integer> weight_map;
    private static Map<String, Integer> type_map;
    private String name;
    private Integer weight;
    private Integer type;

    static {
        weight_map = new HashMap<>();
        type_map = new HashMap<>();
        populateMaps();
    }

    private static void populateMaps() {
        try (BufferedReader br = new BufferedReader(new FileReader("produk/produk.txt"))) {
            String line;
            int type = -1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) {
                    type++;
                    continue;
                }
                String producerName = parts[0].trim();
                String productName = parts[1].trim();
                Integer weight = Integer.parseInt(parts[3].trim());
                animal_product.put(producerName, productName);
                weight_map.put(productName, weight);
                type_map.put(productName, type);   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Produk(String name) {
        super(name);
        this.weight = weight_map.get(name);
        this.type = type_map.get(name);
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Integer getType() {
        return this.type;
    }
}
