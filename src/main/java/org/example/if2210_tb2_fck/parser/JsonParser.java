package org.example.if2210_tb2_fck.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    public static String parseJson(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/state/" + fileName);

        if (!file.exists()){
            throw new IOException("File not found: " + fileName);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(jsonNode.get("current_turn").asInt()).append("\n");
        stringBuilder.append(jsonNode.get("jumlah_item_di_shop").asInt()).append("\n");

        for (JsonNode item : jsonNode.get("shop_items")){
            stringBuilder.append(item.get("name").asText()).append(" ").append(item.get("stock").asInt()).append("\n");
        }
        return stringBuilder.toString();
    }

    public static String parsePlayerJson(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/state/" + fileName);

        if (!file.exists()){
            throw new IOException("File not found: " + fileName);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(jsonNode.get("jumlah_gulden").asInt()).append("\n");
        stringBuilder.append(jsonNode.get("jumlah_deck").asInt()).append("\n");
        stringBuilder.append(jsonNode.get("jumlah_deck_aktif").asInt()).append("\n");

        for (JsonNode item : jsonNode.get("deck_aktif")){
            stringBuilder.append(item.get("lokasi").asText()).append(" ").append(item.get("kartu").asText()).append("\n");
        }

        stringBuilder.append(jsonNode.get("jumlah_kartu_ladang").asInt()).append("\n");

        for (JsonNode item : jsonNode.get("ladang")){
            stringBuilder.append(item.get("lokasi").asText()).append(" ").append(item.get("kartu").asText()).append(" ").append(item.get("berat/umur").asInt()).append(" ").append(item.get("jumlah_item_aktif").asInt()).append(" ");
            for (JsonNode subItem : item.get("items")) {
                stringBuilder.append(subItem.asText()).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}