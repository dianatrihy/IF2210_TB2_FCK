package org.example.if2210_tb2_fck.parser;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlParser implements IParser {
    public String parseGameState(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/" + fileName);

        if (!file.exists()){
            throw new IOException("File not found: " + fileName);
        }

        Yaml yaml = new Yaml();
        try (FileInputStream inputStream = new FileInputStream(file)){
            Map<String, Object> yamlData = yaml.load(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(yamlData.get("current_turn")).append("\n");
            stringBuilder.append(yamlData.get("jumlah_item_di_shop")).append("\n");

            List<Map<String, Object>> shopItems = (List<Map<String, Object>>) yamlData.get("shop_items");
            for (Map<String, Object> item : shopItems) {
                stringBuilder.append(item.get("name")).append(" ").append(item.get("stock")).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    public String parsePlayer(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/state/" + fileName);

        if (!file.exists()){
            throw new IOException("File not found: " + fileName);
        }

        Yaml yaml = new Yaml();
        try (FileInputStream inputStream = new FileInputStream(file)){
            Map<String, Object> yamlData = yaml.load(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(yamlData.get("jumlah_gulden")).append("\n");
            stringBuilder.append(yamlData.get("jumlah_deck")).append("\n");
            stringBuilder.append(yamlData.get("jumlah_deck_aktif")).append("\n");

            List<Map<String, Object>> deckAktif = (List<Map<String, Object>>) yamlData.get("deck_aktif");
            for (Map<String, Object> item : deckAktif){
                stringBuilder.append(item.get("lokasi")).append(" ").append(item.get("kartu")).append("\n");
            }

            stringBuilder.append(yamlData.get("jumlah_kartu_ladang")).append("\n");

            List<Map<String, Object>> ladang = (List<Map<String, Object>>) yamlData.get("ladang");
            for (Map<String, Object> item : ladang){
                stringBuilder.append(item.get("lokasi")).append(" ").append(item.get("kartu")).append(" ").append(item.get("berat/umur")).append(" ").append(item.get("jumlah_item_aktif")).append(" ");
                List<String> items = (List<String>) item.get("items");
                for (String subItem : items){
                    stringBuilder.append(subItem).append(" ");
                }
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
    }
}
