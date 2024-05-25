package org.example.if2210_tb2_fck.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.example.if2210_tb2_fck.model.*;
import org.example.if2210_tb2_fck.model.Item.Item;

public class SaveState {
    private LoadState loadState;

    public SaveState(LoadState loadState) {
        this.loadState = loadState;
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            saveGameState(writer);
            savePlayerState(writer, loadState.getPlayer1());
            savePlayerState(writer, loadState.getPlayer2());
        }
    }

    private void saveGameState(BufferedWriter writer) throws IOException {
        writer.write(loadState.getCurrentTurn() + "\n");
        Toko toko = loadState.getToko();
        Map<String, Integer> catalog = toko.getStockMap();
        writer.write(catalog.size() + "\n");
        for (Map.Entry<String, Integer> entry : catalog.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
    }

    private void savePlayerState(BufferedWriter writer, Player player) throws IOException {
        writer.write(player.getUang() + "\n");

        DeckAktif deckAktif = player.getDeckAktif();

        writer.write(player.getDeckInventory().getNumOfElements() + "\n");
        writer.write(deckAktif.getAllKartu().size() + "\n");

        List<Kartu> listKartuDA = deckAktif.getAllKartuNull();

        for (int i = 0; i < 6; i++) {
            if (deckAktif.getAllKartuLocation().get(i) != 0) {
                char letter = (char) ('A' + i);
                String lokasi = letter + "1";
                writer.write(formatLokasi(lokasi) + " " + listKartuDA.get(i).getName() + "\n");
            }
        }

        Field ladang = player.getLadang();
        writer.write(ladang.getNumOfElements() + "\n");

        ArrayList<ArrayList<MakhlukHidup>> listKartuLadang = ladang.getAllKartuNull(ladang.getRow(), ladang.getCol());
        ArrayList<ArrayList<Integer>> kartuLocations = ladang.getAllKartuLocation(ladang.getRow(), ladang.getCol());

        for (int i = 0; i < ladang.getRow(); i++) {
            for (int j = 0; j < ladang.getCol(); j++) {
                if (kartuLocations.get(i).get(j) == 1) {
                    MakhlukHidup kartu = listKartuLadang.get(i).get(j);
                    if (kartu != null) {
                        char letter = (char) ('A' + j);
                        String lokasi = letter + String.valueOf(i + 1);
                        writer.write(formatLokasi(lokasi) + " " + kartu.getName());
                        if (kartu instanceof Hewan) {
                            writer.write(" " + ((Hewan) kartu).getBerat());
                        } else if (kartu instanceof Tanaman) {
                            writer.write(" " + ((Tanaman) kartu).getUmur());
                        }

                        Map<String, Integer> itemList = kartu.getItem();
                        writer.write(" " + itemList.size());
                        if (!itemList.isEmpty()) {
                            writer.write(" ");
                            for (Map.Entry<String, Integer> entry : itemList.entrySet()) {
                                for (int k = 0; k < entry.getValue(); k++) {
                                    writer.write(entry.getKey() + " ");
                                }
                            }
                        }
                        writer.write("\n");
                    }
                }
            }
        }
    }

    private String formatLokasi(String lokasi) {
        int row = Integer.parseInt(lokasi.substring(1)) - 1;
        char col = (char) (lokasi.charAt(0) - 'A');
        return col + "" + row;
    }
}
