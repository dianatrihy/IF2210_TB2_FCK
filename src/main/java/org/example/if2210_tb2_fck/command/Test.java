package org.example.if2210_tb2_fck.command;
import java.util.Random;

public class Test {
    private static Random random = new Random();
    public static void Main (String[] args){
        System.out.println("Bear attack!");
        int numRows = 4;
        int numCols = 5;
        
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
        System.out.println("Wdith penyerangan: " + (endCol-startCol+1));
        System.out.println("Height penyerangan: " + (endRow-startRow+1));


        // Start bear attack timer
        // int durationSeconds = getRandomAttackDuration();
        // startBearAttackTimer(durationSeconds);

        // Display timer and notify players about the bear attack
        // System.out.println("Bear attack in subgrid: (" + subgridRow + ", " + subgridCol + ")");
        // System.out.println("You have " + durationSeconds + " seconds to move your plants or animals!");
        
    }
}
