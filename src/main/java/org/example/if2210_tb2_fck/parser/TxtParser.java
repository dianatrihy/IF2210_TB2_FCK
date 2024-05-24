package org.example.if2210_tb2_fck.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TxtParser implements IParser {
    public String parseGameState(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/" + fileName);

        if (!file.exists()) {
            throw new IOException("File not found: " + fileName);
        }

        System.out.println("File found: " + fileName);
        return Files.readString(file.toPath());
    }

    public String parsePlayer(String fileName) throws IOException {
        return parseGameState(fileName);
    }
}
