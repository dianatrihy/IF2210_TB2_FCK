package org.example.if2210_tb2_fck;

import org.example.if2210_tb2_fck.Item.Item;

public class Field extends GridMatrix<MakhlukHidup>{
    public Field() {
        super(4, 5);
    }

    public void kasihItemDiLadang(Item item, int row, int col){
        MakhlukHidup makhlukhidup = retrieveKartu(row,col);
        if (makhlukhidup==null){
            return;
        }
        else{
            // item.applyEffect(makhlukhidup);
        }
    }

    // pindah kartu dari petak startidrow dan startidcol, ke finidrow dan finidcol
    public void moveKartu(int startidcol, int startidrow, int finidrow, int finidcol){
        MakhlukHidup kartu = retrieveKartu(startidrow, startidcol);
        removeKartu(startidrow, startidcol);
        placeKartu(finidrow, finidcol, kartu);
    }
}
