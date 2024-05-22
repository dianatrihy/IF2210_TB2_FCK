package org.example.if2210_tb2_fck;
import org.example.if2210_tb2_fck.model.Kartu;
import java.util.*;

public class DeckInventory {
    private ArrayList<Kartu> deckinventory;
    private int numofelements;       //jumlahelement
    public DeckInventory(int numofelements){
        this.numofelements = numofelements;

        this.deckinventory = new ArrayList<>();
        List<String> namahewan = Arrays.asList("Kuda", "Sapi", "Ayam", "Domba", "Hiu");
        List<String> namaproduk = Arrays.asList("DagingBeruang", "DagingDomba", "DagingKuda", "Jagung", "Pumpkin", "SharkFin", "Strawberry", "Susu", "Telur");
        List<String> namatanaman = Arrays.asList("CornSeeds", "StrawberrySeeds", "PumpkinSeeds");
        List<String> namaitem = Arrays.asList("Accelerate", "BearTrap", "Delay", "Destroy", "InstantHarvest", "Protect");

        // Combine all lists into one list
        List<String> allCards = new ArrayList<>();
        allCards.addAll(namahewan);
        allCards.addAll(namaproduk);
        allCards.addAll(namatanaman);
        allCards.addAll(namaitem);        
        Collections.shuffle(allCards);
        int cardsPerType = numofelements / 4;
        int extraCards = numofelements % 4;
        List<String> tempnama = new ArrayList<>();
        tempnama.addAll(getBalancedList(namahewan, cardsPerType, extraCards > 0 ? 1 : 0));
        tempnama.addAll(getBalancedList(namaproduk, cardsPerType, extraCards > 1 ? 1 : 0));
        tempnama.addAll(getBalancedList(namatanaman, cardsPerType, extraCards > 2 ? 1 : 0));
        tempnama.addAll(getBalancedList(namaitem, cardsPerType, extraCards > 3 ? 1 : 0));
        Collections.shuffle(tempnama);
        for (String card : tempnama) {
            System.out.println(card);
            this.deckinventory.add(new Kartu(card));
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
        DeckInventory deck = new DeckInventory(20); // Example with 20 elements

        // Print out the contents of deckinventory
        for (Kartu kartu : deck.deckinventory) {
            System.out.println(kartu);
        }
    }
}
