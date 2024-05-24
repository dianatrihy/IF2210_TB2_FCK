package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.model.Item.Item;

public class Field extends GridMatrix<MakhlukHidup>{
    public Field(int row, int col) {
        super(row, col);
    }

    public synchronized void kasihItemDiLadang(Item item, int row, int col){
        MakhlukHidup makhlukhidup = retrieveKartu(row,col);
        if (makhlukhidup==null){
            return;
        }
        else{
            // item.applyEffect(makhlukhidup);
        }
    }

    // pindah kartu dari petak startidrow dan startidcol, ke finidrow dan finidcol
    public synchronized void moveKartu(int startidcol, int startidrow, int finidrow, int finidcol){
        MakhlukHidup kartu = retrieveKartu(startidrow, startidcol);
        removeKartu(startidrow, startidcol);
        placeKartu(finidrow, finidcol, kartu);
    }

    // cek apakah ada item trap di range petak tertentu
    public boolean adaTrap(int idrowstart, int idrowend, int idcolstart, int idcolend){
        for (int i = idrowstart; i<=idrowend; i++){
            for (int j = idcolstart; j<=idcolend; j++){
                if(retrieveKartu(i, j)!=null){
                    if (retrieveKartu(i, j).hasTrap()){
                        System.out.println("Si " + i +" "+ j + "punya trap");
                        return true;
                    }
                }
            }
        }
        return false;
    } 

    public void bearKills(int idrowstart, int idrowend, int idcolstart, int idcolend){
        if (!adaTrap(idrowstart, idrowend, idcolstart, idcolend)){
            for (int i = idrowstart; i<=idrowend; i++){
                for (int j = idcolstart; j<=idcolend; j++){
                    if(retrieveKartu(i, j)!=null){
                        if (!retrieveKartu(i, j).isProtected()){
                            System.out.println("Successfully kills "+ retrieveKartu(i, j).getName());
                            removeKartu(i, j);
                        }
                    }
                }
            }
        }
        else{
            System.out.println("Gaada yang mati, beruang ketangkap");

        }
    }

    // DEBUGGING TES SHOW LADANG
    public synchronized void addKartu(MakhlukHidup mh, int row, int col){
        placeKartu(row, col, mh);
    }
    
}
