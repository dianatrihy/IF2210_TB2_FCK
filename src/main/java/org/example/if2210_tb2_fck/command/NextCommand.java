package org.example.if2210_tb2_fck.command;

public class NextCommand implements ICommand {
    // private GameManager gameManager;

    // public NextCommand(GameManager gameManager) {
    //     this.gameManager = gameManager;
    // }

    @Override
    public void execute() {
        // gameManager.nextTurn();
        System.out.println("Next turn");
    }
}