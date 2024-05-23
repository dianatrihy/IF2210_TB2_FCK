package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.model.Player;
import org.example.if2210_tb2_fck.model.Toko;
import java.util.Random;

public class GameManager {
    private Player player1;
    private Player player2;
    private int currentTurn;
    private static final int MAX_TURNS = 20;
    private Toko toko;

    public GameManager() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.currentTurn = 1;
        this.toko = Toko.getInstance(0); // gatau
    }

    public Player getCurrentPlayer() {
        return currentTurn % 2 == 1 ? player1 : player2;
    }

    public Player getOtherPlayer() {
        return currentTurn % 2 == 1 ? player2 : player1;
    }

    public void nextTurn() {
        currentTurn++;
        if (currentTurn > MAX_TURNS) {
            endGame();
        } else {
            startTurn();
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    private void endGame() {
        System.out.println("Game Over!");
        // to-implement
    }

    public void startTurn() {
        shuffleCards();
        if (bearAttackOccurs()) {
            bearAttack();
        }
        freeAction();
    }

    private void shuffleCards() {
        // to-implement
        getCurrentPlayer().shuffleDeck();
        System.out.println("Shuffling cards...");
    }

    private boolean bearAttackOccurs() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private void bearAttack() {
        // to-implement
        System.out.println("Bear attack!");
    }

    private void freeAction() {
        // to-implement
        System.out.println("Free action phase...");
    }
}
