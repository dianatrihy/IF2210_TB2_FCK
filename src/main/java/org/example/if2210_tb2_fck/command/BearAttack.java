package org.example.if2210_tb2_fck.command;

import org.example.if2210_tb2_fck.controller.GameManagerController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import org.example.if2210_tb2_fck.controller.TimerController;
import org.example.if2210_tb2_fck.model.Player;


public class BearAttack {

    private final Lock lock = new ReentrantLock();

    public BearAttack() {}

    public int getRandomAttackDuration() {
        Random random = new Random();
        return random.nextInt(31) ; // Generate a random number within the range of 30 to 60 inclusive
    }

    public void bearAttackCommand(Player player, GameManagerController gm) {
        gm.disableActionButtons(true);
        System.out.println("Bear attack!");
        int numRows = player.getLadang().getRow();
        int numCols = player.getLadang().getCol();

        Random random = new Random();
        int startRow = random.nextInt(numRows - 1);
        int endRow = Math.min(startRow + 1 + random.nextInt(2), numRows - 1);
        int startCol = random.nextInt(numCols - 2);
        int maxCols = Math.min(numCols - startCol, 3);
        int endCol = startCol + random.nextInt(maxCols);

        try {
            gm.loadLadangWithBeruang(player, startRow, endRow, startCol, endCol);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Start Row: " + startRow);
        System.out.println("End Row: " + endRow);
        System.out.println("Start Column: " + startCol);
        System.out.println("End Column: " + endCol);
        System.out.println("Width penyerangan: " + (endCol - startCol + 1));
        System.out.println("Height penyerangan: " + (endRow - startRow + 1));

        int durationSeconds = getRandomAttackDuration();
        TimerController timerController = null;
        try {
            timerController = gm.loadTimer();
        } catch (Exception e){
            e.printStackTrace();
        }
        startBearAttackTimer(player, durationSeconds, startRow, endRow, startCol, endCol, timerController, gm);
        System.out.println("You have " + durationSeconds + " seconds to move your plants or animals!");
    }

    private void startBearAttackTimer(Player player, int initialDurationSeconds, int startRow, int endRow, int startCol, int endCol, TimerController timerController, GameManagerController gameManagerController) {
        final int[] durationSeconds = {initialDurationSeconds}; // Use an array to hold the mutable value

        // Initialize timeline here
        Timeline timeline = new Timeline();
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            Platform.runLater(() -> timerController.setTimer(durationSeconds[0]));
            System.out.println("Bear attack ends in " + durationSeconds[0] + " seconds...");
            timerController.setTimer(durationSeconds[0]);
            durationSeconds[0]--;

            if (durationSeconds[0] < 0) {
                timeline.stop(); // Stop the timeline
                handleBearAttackEnd(player, startRow, endRow, startCol, endCol, gameManagerController); // Call end handler
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(initialDurationSeconds + 1);
        timeline.play();
    }
    private void handleBearAttackEnd(Player player, int startRow, int endRow, int startCol, int endCol, GameManagerController gameManagerController) {
        lock.lock();
        try {
            Platform.runLater(() -> {
                System.out.println("Bear attack ended. Removing plants or animals...");
                gameManagerController.clearTimer();
                player.getLadang().bearKills(startRow, endRow, startCol, endCol, player, gameManagerController);
                try {
                    gameManagerController.loadLadangKW(player); 
                    gameManagerController.refreshDeckAktif();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // gameManagerController.disableActionButtons(true);
            });
        } finally {
            lock.unlock();
        }
    }
    
}
