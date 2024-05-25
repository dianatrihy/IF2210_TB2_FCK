package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.model.Item.Item;

public class Player {
    private String nama;
    private int uang;
    private Field ladang;
    private DeckAktif deck_aktif;
     private DeckInventory deck_inventory;

    public Player(String nama){
        this.nama = nama;
        this.uang = 0;
        this.ladang = new Field(4, 5);
        this.deck_aktif = new DeckAktif();
        this.deck_inventory = new DeckInventory(40);
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

     public DeckInventory getDeckInventory(){
         return this.deck_inventory;
     }

    public void setUang(int uang){
        this.uang = uang;
    }

    public void setDeckInventory(DeckInventory deck_inventory){
        this.deck_inventory = deck_inventory;
    }

    public void simpanDeckAktif(int row, int col, Kartu kartu){
        this.deck_aktif.placeKartu(row, col, kartu);
    }

    public void simpanAutoDeckAktif(Kartu kartu){
        this.deck_aktif.simpanAuto(kartu);
    }

    public void simpanLadang(int row, int col, MakhlukHidup kartu){
        this.ladang.placeKartu(row, col, kartu);
    }

    public void deleteKartuDeckInventory(Kartu kartu){
        this.deck_inventory.deleteKartu(kartu);
    }

    public void kasihItem(Item kartu_item, MakhlukHidup kartuMH) {
        if (kartuMH instanceof Hewan) {
            kartu_item.apply((Hewan) kartuMH);
        } else if (kartuMH instanceof Tanaman) {
            kartu_item.apply((Tanaman) kartuMH);
        }
    }

    public void beli(Kartu kartu, Integer price){
        this.uang -= price;
        simpanAutoDeckAktif(kartu);
    }

    public void jual(Kartu kartu, Integer price){
        this.uang += price;
        this.deck_aktif.deleteCard(kartu);
    }
    public void simpanBeruang(Kartu kartuberuang){
        if(!this.getDeckAktif().isFull()){
            simpanAutoDeckAktif(kartuberuang);
        }
        else{
            System.out.println("Deck aktif penuh sehingga tidak bisa save kartu beruang");
        }
    }
}
