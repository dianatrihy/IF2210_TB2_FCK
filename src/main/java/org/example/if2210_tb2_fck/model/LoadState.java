package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.model.Item.Item;

import java.util.*;

public class LoadState {
    private int current_turn;
    private Toko toko;
    Player player1;
    Player player2;

    public LoadState(String gameState, String playerState1, String playerState2){
        this.toko = Toko.getInstance();
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        loadGameState(gameState);
        loadPlayer(playerState1, player1);
        loadPlayer(playerState2, player2);
    }

    public void loadGameState(String gameState){
        String[] lines = gameState.split("\n");

        current_turn = Integer.parseInt(lines[0].trim());

        int jumlahItemDiShop = Integer.parseInt(lines[1].trim());

        Map<String, Integer> newCatalogStock = new HashMap<>();
        for (int i = 0; i < jumlahItemDiShop; i++) {
            String[] itemData = lines[2 + i].trim().split(" ");
            String itemName = itemData[0];
            int itemStock = Integer.parseInt(itemData[1]);
            newCatalogStock.put(itemName, itemStock);
        }

        // Mengupdate stock di toko
        toko.updateStock(newCatalogStock);
    }

    public int getCurrentTurn(){
        return current_turn;
    }

    public Toko getToko(){
        return toko;
    }

    public void loadPlayer(String playerState, Player player){
        String[] lines = playerState.split("\n");
        int lineIndex = 0;

        player.setUang(Integer.parseInt(lines[lineIndex++].trim()));

        int jumlahDeck = Integer.parseInt(lines[lineIndex++].trim());
        int jumlahDeckAktif = Integer.parseInt(lines[lineIndex++].trim());

        DeckInventory deckInventory = new DeckInventory(jumlahDeck);
        player.setDeckInventory(deckInventory);

        for (int i = 0; i < jumlahDeckAktif; i++){
            String[] cardData = lines[lineIndex++].trim().split(" ");
            String lokasi = cardData[0];
            String namaKartu = cardData[1];
            Kartu kartu = createKartu(namaKartu);
            player.simpanDeckAktif(0, i, kartu);
        }

        int jumlahKartuLadang = Integer.parseInt(lines[lineIndex++].trim());
        Field ladang = player.getLadang();
        for (int i = 0; i < jumlahKartuLadang; i++){
            String[] ladangData = lines[lineIndex++].trim().split(" ");
            String lokasi = ladangData[0];
            String namaKartu = ladangData[1];
            System.out.println("LOAD KARTU DI LADANG: " + namaKartu); // debug
            int row = getRowFromLokasi(lokasi);
            int col = getColFromLokasi(lokasi);
            MakhlukHidup kartu = (MakhlukHidup) createKartu(namaKartu);

            if (kartu instanceof Hewan){
                ((Hewan) kartu).setBerat(Integer.parseInt(ladangData[2]));
            } else if (kartu instanceof Tanaman){
                ((Tanaman) kartu).setUmur(Integer.parseInt(ladangData[2]));
            }

            int jumlahItemAktif = Integer.parseInt(ladangData[3]);
            for (int j = 0; j < jumlahItemAktif; j++){
                kartu.addItem(ladangData[4 + j]);
            }
            player.simpanLadang(row, col, kartu);
        }
    }

    private Kartu createKartu(String namaKartu){
        if (namaKartu.equals("HIU")){
            return new Karnivora(namaKartu);
        } else if (namaKartu.equals("SAPI") || namaKartu.equals("DOMBA") || namaKartu.equals("KUDA")){
            return new Herbivora(namaKartu);
        } else if (namaKartu.equals("AYAM") || namaKartu.equals("BERUANG")){
            return new Omnivora(namaKartu);
        } else if (namaKartu.equals("CORNSEEDS") || namaKartu.equals("PUMPKINSEEDS") || namaKartu.equals("STRAWBERRYSEEDS")){
            return new Tanaman(namaKartu);
        } else if (namaKartu.equals("ACCELERATE") || namaKartu.equals("BEARTRAP") || namaKartu.equals("DELAY") || namaKartu.equals("DESTROY") || namaKartu.equals("INSTANTHARVEST") || namaKartu.equals("PROTECT")){
            return new Item(namaKartu);
        } else {
            return new Produk(namaKartu);
        }
    }

    private int getRowFromLokasi(String lokasi){
        return Integer.parseInt(lokasi.substring(1)) - 1;
    }

    private int getColFromLokasi(String lokasi){
        return lokasi.charAt(0) - 'A';
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }
}
