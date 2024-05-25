package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.controller.GameManagerController;
import org.example.if2210_tb2_fck.model.Item.Item;

import java.util.ArrayList;

public class Field extends GridMatrix<MakhlukHidup>{
    public Field(int row, int col) {
        super(row, col);
    }

    public synchronized void kasihItemDiLadang(Item item, int row, int col){
        MakhlukHidup makhlukhidup = retrieveKartu(row,col);
        if (makhlukhidup == null){
            return;
        } else {
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
    public synchronized boolean adaTrap(int idrowstart, int idrowend, int idcolstart, int idcolend){
        boolean bool = false;
        for (int i = idrowstart; i <= idrowend; i++){
            for (int j = idcolstart; j <= idcolend; j++){
                if(retrieveKartu(i, j) != null){
                    if (retrieveKartu(i, j).hasTrap()){
                        System.out.println("Si " + i + " " + j + " punya trap");
                        bool = true;
                        break;
                    }
                }
            }
        }
        return bool;
    } 

    public synchronized void bearKills(int idrowstart, int idrowend, int idcolstart, int idcolend, Player player, GameManagerController gameManagerController){
        if (!adaTrap(idrowstart, idrowend, idcolstart, idcolend)){
            for (int i = idrowstart; i <= idrowend; i++){
                for (int j = idcolstart; j <= idcolend; j++){
                    System.out.println("WOWO " + i + j);
                    if(retrieveKartu(i, j) != null){
                        if (!retrieveKartu(i, j).isProtected()){
                            System.out.println("Successfully kills " + retrieveKartu(i, j).getName());
                            removeKartu(i, j);
                            gameManagerController.refreshLadang();
                        }
                    }
                }
            }
        } else {
            System.out.println("Gaada yang mati, beruang ketangkap");
            Karnivora beruang = new Karnivora("BERUANG");
            player.simpanBeruang(beruang);
            System.out.println("HITUNG JUMLAH ELEMEN DI DECK AKTIF "+player.getDeckAktif().getNumOfElements());
        }
    }

    public void simpanAutoLadang(MakhlukHidup kartu){
        for (int i = 0; i < getRow(); i++){
            for (int j = 0; j < getCol(); j++){
                if (retrieveKartu(i, j) == null){
                    placeKartu(i, j, kartu);
                    return;
                }
            }
        }
    }


    public ArrayList<ArrayList<Integer>> getAllKartuLocation(int r, int c) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>(r);

        for (int i = 0; i < r; i++) {
            ArrayList<Integer> row = new ArrayList<>(c);
            for (int j = 0; j < c; j++) {
                if (retrieveKartu(i, j) == null) { // Menganggap retrieveKartu mengembalikan 0 atau non-0
                    row.add(0);
                } else {
                    row.add(1);
                }
            }
            ret.add(row);
        }

        return ret;
    }

    public ArrayList<ArrayList<MakhlukHidup>> getAllKartuNull(int r, int c) {
        ArrayList<ArrayList<MakhlukHidup>> ret = new ArrayList<>(r);

        for (int i = 0; i < r; i++) {
            ArrayList<MakhlukHidup> row = new ArrayList<>(c);
            for (int j = 0; j < c; j++) {
                if (retrieveKartu(i, j) != null) {
                    row.add((MakhlukHidup) retrieveKartu(i, j));
                } else {
                    row.add(null);
                }
            }
            ret.add(row);
        }
        return ret;
    }

    // DEBUGGING TES SHOW LADANG
    public synchronized void addKartu(MakhlukHidup mh, int row, int col){
        placeKartu(row, col, mh);
    }
}
