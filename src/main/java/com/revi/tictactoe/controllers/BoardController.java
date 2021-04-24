package com.revi.tictactoe.controllers;

import com.revi.tictactoe.Main;
import com.revi.tictactoe.entities.Board;
import com.revi.tictactoe.entities.Player;

import java.util.InputMismatchException;

public class BoardController {

    private static Board board;

    public static void startGame() {
        board = new Board();
        board.printBoard();
        try {
            /* Future
            Terminal.log("Do you want to be player one or two. Player two starts second.");
            int user = Terminal.scanner.nextInt() > 2 ? 2 : 1;
            int bot = user == 1 ? 2 : 1;
             */

            Terminal.log("Prepare!");
            for (int i = 0; i < 9; i++) {
                PlayerManager.getPlayers().stream().filter(player -> {
                    makeTurn(player);
                    board.printBoard();
                    return checkWin(player);
                }).findAny().ifPresent(player -> {
                    Terminal.logf("Player (%s) won the game!", player.getSymbol());
                    Main.menuAction();
                });
            }
        } catch (InputMismatchException e) {
            Terminal.error("Please select between (1) or (2)");
        }
    }

    public static void makeTurn(Player player) {
        if (board.isFull()) {
            Terminal.log("No one won, the game ended in a draw!");
            Main.menuAction();
        } else {
            try {
                Terminal.cleanLogf("Player %s, please select the field you want to set your mark on (1-9): ", player.getSymbol());
                int response = Terminal.scanner.nextInt();

                // Imagine the row like this:
                // [1, 2, 3] - 0
                // [4, 5, 6] - 1
                // [7, 8, 9] - 2
                // Response above 3 and below or equal 6 = column 1, response below or equal to 3 = 0 and the rest is 2.
                // This works as a temporary solution but is not scalable with the cell, will do a scalable version later.
                int i = response > 3 && response <= 6 ? 1 : (response <= 3 ? 0 : 2);

                int cell = response <= 3 ? response - 1 : response <= 6 ? response - 4 : response - 7;
                if (board.isOccupied(cell, i)) {
                    Terminal.errorf("The cell %s is occupied, please select another one!", response);
                    makeTurn(player);
                } else {
                    board.updateCell(cell, i, player.getSymbol());
                }
            } catch (InputMismatchException e) {
                Terminal.error("Please select between 1-9!");
                makeTurn(player);
            }
        }
    }

    public static boolean checkWin(Player p) {
        char[][] cells = board.getCells();
        char player = p.getSymbol();
        return (cells[1][0] == player) && (cells[1][1] == player) && (cells[1][2] == player)
                || (cells[2][0] == player) && (cells[2][1] == player) && (cells[2][2] == player)
                || (cells[0][0] == player) && (cells[1][0] == player) && (cells[2][0] == player)
                || (cells[0][1] == player) && (cells[1][1] == player) && (cells[2][1] == player)
                || (cells[0][2] == player) && (cells[1][2] == player) && (cells[2][2] == player)
                || (cells[0][0] == player) && (cells[1][1] == player) && (cells[2][2] == player)
                || (cells[0][2] == player) && (cells[1][1] == player) && (cells[2][0] == player)
                || (cells[0][0] == player) && (cells[0][1] == player) && (cells[0][2] == player);
    }


}
