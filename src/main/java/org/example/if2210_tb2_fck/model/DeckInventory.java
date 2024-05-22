package org.example.if2210_tb2_fck.model;
import java.util.*;

import org.example.if2210_tb2_fck.model.Item.Item;

public class DeckInventory {
    private ArrayList<Kartu> deckinventory;
    private int numofelements;

    public DeckInventory(int numofelements) {
        this.numofelements = numofelements;
        this.deckinventory = new ArrayList<>();

        List<String> namaherbivora = Arrays.asList("Kuda", "Sapi", "Domba");
        List<String> namakarnivora = Arrays.asList("Hiu");
        List<String> namaomnivora = Arrays.asList("Ayam");
        List<String> namaproduk = Arrays.asList("DagingBeruang", "DagingDomba", "DagingKuda", "Jagung", "Pumpkin", "SharkFin", "Strawberry", "Susu", "Telur");
        List<String> namatanaman = Arrays.asList("CornSeeds", "StrawberrySeeds", "PumpkinSeeds");
        List<String> namaitem = Arrays.asList("Accelerate", "BearTrap", "Delay", "Destroy", "InstantHarvest", "Protect");

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

    public static void main(String[] args) {
        DeckInventory deck = new DeckInventory(40);

        for (Kartu kartu : deck.deckinventory) {
            System.out.println(kartu.getName());
            System.out.println(kartu.getJenis());
        }
    }
}
