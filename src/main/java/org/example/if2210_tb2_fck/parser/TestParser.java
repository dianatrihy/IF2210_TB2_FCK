package org.example.if2210_tb2_fck.parser;

import java.io.File;
import java.io.IOException;
import org.example.if2210_tb2_fck.parser.TxtParser;
import org.example.if2210_tb2_fck.model.LoadState;

public class TestParser {
    public static boolean isFolderExists(String folder){
        File file = new File("src/main/resources/org/example/if2210_tb2_fck/" + folder);
        return file.exists() && file.isDirectory();
    }

    public static void main(String[] args){
        try {
            System.out.println(isFolderExists("state"));
//            LoadState loadState = new LoadState(TxtParser.parseTxt("gamestate.txt"), TxtParser.parseTxt("player1.txt"), TxtParser.parseTxt("player2.txt"));
//            LoadState loadState = new LoadState(YamlParser.parseYaml("gamestate.yaml"), YamlParser.parsePlayerYaml("player1.yaml"), YamlParser.parsePlayerYaml("player2.yaml"));
            IParser JsonParser = new JsonParser();
            LoadState loadState = new LoadState(JsonParser.parseGameState("state/gamestate.json"), JsonParser.parsePlayer("state/player1.json"), JsonParser.parsePlayer("state/player2.json"));
            System.out.println(loadState.getCurrentTurn());
            System.out.println(loadState.getToko().getStockMap());
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
