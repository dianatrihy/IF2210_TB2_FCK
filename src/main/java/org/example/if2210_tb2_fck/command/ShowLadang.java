package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.controller.DragAndDrop;
import org.example.if2210_tb2_fck.model.Kartu;
import org.example.if2210_tb2_fck.model.Player;


public class ShowLadang{
    private DragAndDrop dad;
    public void ShowLadang(Player p){
        org.example.if2210_tb2_fck.model.Field ladangplayer = p.getLadang();
        int row = ladangplayer.getRow();
        int col = ladangplayer.getCol();
        String idpane;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                idpane = "pane" + i + j;
                Kartu kartu = ladangplayer.retrieveKartu(i, j);

            }
        }
        

    }
}
