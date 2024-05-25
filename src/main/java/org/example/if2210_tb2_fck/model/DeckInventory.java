package org.example.if2210_tb2_fck.model;
import java.util.*;

import org.example.if2210_tb2_fck.model.Item.Item;

public class DeckInventory {
    private ArrayList<Kartu> deckinventory;
    private int numofelements;

    public DeckInventory(int numofelements) {
        this.numofelements = numofelements;
        this.deckinventory = new ArrayList<>();

        List<String> namaherbivora = Arrays.asList("KUDA", "SAPI", "DOMBA");
        List<String> namakarnivora = Arrays.asList("HIU_DARAT");
        List<String> namaomnivora = Arrays.asList("AYAM");
        List<String> namaproduk = Arrays.asList("DAGING_BERUANG", "DAGING_DOMBA", "DAGING_KUDA", "JAGUNG", "LABU", "SIRIP_HIU", "STROBERI", "SUSU", "TELUR");
        List<String> namatanaman = Arrays.asList("BIJI_JAGUNG", "BIJI_STROBERI", "BIJI_LABU");
        List<String> namaitem = Arrays.asList("ACCELERATE", "TRAP", "DELAY", "DESTROY", "INSTANT_HARVEST", "PROTECT");

        int cardsPerType = numofelements / 6;
        int extraCards = numofelements % 6;

        List<String> tempnama = new ArrayList<>();
        tempnama.addAll(getBalancedList(namaherbivora, cardsPerType, extraCards > 0 ? 1 : 0));
        tempnama.addAll(getBalancedList(namakarnivora, cardsPerType, extraCards > 1 ? 1 : 0));
        tempnama.addAll(getBalancedList(namaomnivora, cardsPerType, extraCards > 2 ? 1 : 0));
        tempnama.addAll(getBalancedList(namaproduk, cardsPerType, extraCards > 3 ? 1 : 0));
        tempnama.addAll(getBalancedList(namatanaman, cardsPerType, extraCards > 4 ? 1 : 0));
        tempnama.addAll(getBalancedList(namaitem, cardsPerType, extraCards > 5 ? 1 : 0));

        Collections.shuffle(tempnama);

        for (String card : tempnama) {
            if (namaherbivora.contains(card)) {
                deckinventory.add(new Herbivora(card));
            } else if (namakarnivora.contains(card)) {
                deckinventory.add(new Karnivora(card));
            } else if (namaomnivora.contains(card)) {
                deckinventory.add(new Omnivora(card));
            } else if (namaproduk.contains(card)) {
                deckinventory.add(new Produk(card));
            } else if (namatanaman.contains(card)) {
                deckinventory.add(new Tanaman(card));
            } else if (namaitem.contains(card)) {
                deckinventory.add(new Item(card));
            }
        }
    }

    private List<String> getBalancedList(List<String> sourceList, int count, int extra) {
        List<String> balancedList = new ArrayList<>();
        for (int i = 0; i < count + extra; i++) {
            balancedList.add(sourceList.get(i % sourceList.size()));
        }
        return balancedList;
    }

    // delete kartu di inventory
    public void deleteKartu(Kartu kartu){
        for (int i = 0; i < numofelements; i++){
            if (deckinventory.get(i).getName().equals(kartu.getName())){
                deckinventory.remove(i);
                return;
            }
        }
    }

    // SHUFFLE 4 CARD
    public ArrayList<Kartu> shuffleDeck(){
        // ambil 4 kartu dari deckinventory secara random
        ArrayList<Kartu> shuffledDeck = new ArrayList<>();
        Collections.shuffle(deckinventory);
        for (int i = 0; i < 4; i++) {
            shuffledDeck.add(deckinventory.get(i));
        }
        return shuffledDeck;
    }

    public static void main(String[] args) {
        DeckInventory deck = new DeckInventory(40);

        for (Kartu kartu : deck.deckinventory) {
            System.out.println(kartu.getName());
            System.out.println(kartu.getJenis());
        }
    }
}
