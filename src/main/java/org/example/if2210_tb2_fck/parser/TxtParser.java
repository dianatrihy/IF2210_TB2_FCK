package org.example.if2210_tb2_fck.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TxtParser {
    public static String parseTxt(String fileName) throws IOException {
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/state/" + fileName);

        if (!file.exists()) {
            throw new IOException("File not found: " + fileName);
        }

        System.out.println("File found: " + fileName);
        return Files.readString(file.toPath());
    }
}
