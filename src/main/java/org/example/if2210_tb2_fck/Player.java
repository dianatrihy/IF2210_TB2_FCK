package org.example.if2210_tb2_fck;

import org.example.if2210_tb2_fck.Item.Item;

public class Player {
    private String nama;
    private int uang;
    private Field ladang;
    private DeckAktif deck_aktif;
    // private DeckInventory deck_inventory;

    public Player(){
        this.uang = 0;
        this.ladang = new Field(4, 5);
        this.deck_aktif = new DeckAktif();
        // this.deck_inventory = new DeckInventory();
    }

    public String getNama(){
        return this.nama;
    }

    public int getUang(){
        return this.uang;
    }

    public Field getLadang(){
        return this.ladang;
    }

    public DeckAktif getDeckAktif(){
        return this.deck_aktif;
    }

    // public DeckInventory getDeckInventory(){
    //     return this.deck_inventory;
    // }

    public void setUang(int uang){
        this.uang = uang;
    }

    // public void ambilKartu()

    public void panen(int idrow, int idcol){
        // ambil dari ladang, delete + return kartu
        // simpan auto ke deckaktif
    }



    public void kasihItem(Item kartu_item, MakhlukHidup kartuMH){
        // kartu_item.applyEffect(kartuMH);
    }

    public void beli(){

    }

    public void jual(){

    }
}
