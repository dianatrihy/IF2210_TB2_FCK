package org.example.if2210_tb2_fck.command;

import java.util.Random;

import org.example.if2210_tb2_fck.controller.TimerController;
import org.example.if2210_tb2_fck.model.Player;

public class BearAttack {
    public BearAttack(){

    }
    public int getRandomAttackDuration() {
        Random random = new Random();

        // Generate a random number within the range of 30 to 60 inclusive
        int duration = random.nextInt(31) + 30;

        return duration;
    }
    public void bearAttackCommand(Player player, TimerController timerController) {
        System.out.println("Bear attack!");
        int numRows = player.getLadang().getRow();
        int numCols = player.getLadang().getCol();

        Random random = new Random();
        // Determine start index for row
        int startRow = random.nextInt(numRows - 1); // Ensure subgrid has max 2 rows

        // Determine end index for row
        int endRow = Math.min(startRow + 1 + random.nextInt(2), numRows - 1); // Ensure subgrid has max 3 rows

        // Determine start index for column
        int startCol = random.nextInt(numCols - 2); // Ensure subgrid has max 3 columns

        // Determine end index for column
        int maxCols = Math.min(numCols - startCol, 3); // Ensure subgrid has max 3 columns
        int endCol = startCol + random.nextInt(maxCols);

        // Display the indices
        System.out.println("Start Row: " + startRow);
        System.out.println("End Row: " + endRow);
        System.out.println("Start Column: " + startCol);
        System.out.println("End Column: " + endCol);
        System.out.println("Width penyerangan: " + (endCol-startCol+1));
        System.out.println("Height penyerangan: " + (endRow-startRow+1));

        // Start bear attack timer
        int durationSeconds = getRandomAttackDuration();
        startBearAttackTimer(player, durationSeconds, startRow, endRow, startCol, endCol, timerController);
        System.out.println("You have " + durationSeconds + " seconds to move your plants or animals!");
    }

    private void startBearAttackTimer(Player player, int durationSeconds, int startRow, int endRow, int startCol, int endCol, TimerController timerController) {
        Thread timerThread = new Thread(() -> {
            for (int i = durationSeconds * 10; i >= 0; i--) { // Multiply durationSeconds by 10 to convert seconds to tenths of a second
                try {
                    Thread.sleep(100); // Sleep for 0.1 second (100 milliseconds)
                    // Update timer display or notify players
                    timerController.setTimer(i/10);
                    System.out.println("Bear attack ends in " + (i / 10.0) + " seconds..."); // Convert tenths of a second back to seconds for display
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Perform actions after bear attack ends (e.g., remove plants or animals)
            handleBearAttackEnd(player, startRow, endRow, startCol, endCol);
        });
        timerThread.start();
    }
    
    

    private void handleBearAttackEnd(Player player, int startrowattacked, int endrowattacked, int startcolattacked, int endcolattacked) {
        // Remove plants or animals that were not moved during the bear attack
        // This method should be synchronized to avoid race conditions
        synchronized (this) {
            // Implement logic to remove plants or animals
            System.out.println("Bear attack ended. Removing plants or animals...");
            player.getLadang().bearKills(startrowattacked, endrowattacked, startcolattacked, endcolattacked);
        }
    }
}
