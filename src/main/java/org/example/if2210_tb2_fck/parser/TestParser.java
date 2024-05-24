package org.example.if2210_tb2_fck.parser;

import java.io.IOException;
import org.example.if2210_tb2_fck.parser.TxtParser;
import org.example.if2210_tb2_fck.model.LoadState;

public class TestParser {
    public static void main(String[] args){
        try {
//            LoadState loadState = new LoadState(TxtParser.parseTxt("gamestate.txt"), TxtParser.parseTxt("player1.txt"), TxtParser.parseTxt("player2.txt"));
//            LoadState loadState = new LoadState(YamlParser.parseYaml("gamestate.yaml"), YamlParser.parsePlayerYaml("player1.yaml"), YamlParser.parsePlayerYaml("player2.yaml"));
            LoadState loadState = new LoadState(JsonParser.parseJson("gamestate.json"), JsonParser.parsePlayerJson("player1.json"), JsonParser.parsePlayerJson("player2.json"));
            System.out.println(loadState.getCurrentTurn());
            System.out.println(loadState.getToko().getStockMap());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
