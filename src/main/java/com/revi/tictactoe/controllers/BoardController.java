package com.revi.tictactoe.controllers;

import com.revi.tictactoe.Main;
import com.revi.tictactoe.entities.Board;
import com.revi.tictactoe.entities.Bot;
import com.revi.tictactoe.entities.Player;

import java.util.InputMismatchException;
import java.util.concurrent.CompletableFuture;

public class BoardController {

    public Board board;

    public BoardController() {
        this.board = new Board();
    }

    public void startGame(boolean bot) {
        board.printBoard();
        Terminal.log("Prepare!");
        if (!bot) {
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
        } else {
            if (PlayerManager.getPlayers().stream().findFirst().isEmpty()) {
                PlayerManager.addPlayer(0, new Player('x'));
            }

            botPlay(PlayerManager.getPlayers().get(0));
        }
    }

    public void botPlay(Player player) {
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
                    botPlay(player);
                } else {
                    board.updateCell(cell, i, player.getSymbol());
                    board.printBoard();

                    Bot bot = new Bot('b');
                    // Calculate the next move of the bot.
                    int s = bot.next(board, player);
                    int x = s > 3 && s <= 6 ? 1 : (s <= 3 ? 0 : 2);
                    int v = s <= 3 ? s - 1 : s <= 6 ? s - 4 : s - 7;

                    Terminal.logf("Bot (%s) placed bet on %d", bot.getSymbol(), response + 1);
                    board.updateCell(v, x, bot.getSymbol());
                    board.printBoard();
                    if (!checkWin(bot) && !checkWin(player) && !board.isFull()) {
                        botPlay(player);
                    } else {
                        if (!board.isFull()) {
                            Terminal.logf("Player (%s) won the game!", checkWin(player) ? player.getSymbol() : bot.getSymbol());
                        } else {
                            Terminal.logf("The game ended in a draw!");
                        }
                        Main.menuAction();
                    }
                }
            } catch (InputMismatchException e) {
                Terminal.error("Please select between 1-9!");
                botPlay(player);
            }
        }
    }

    public CompletableFuture<Void> makeTurn(Player player) {
        return CompletableFuture.runAsync(() -> {
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
                        board.printBoard();
                    }
                } catch (InputMismatchException e) {
                    Terminal.error("Please select between 1-9!");
                    makeTurn(player);
                }
            }
        });
    }

    public boolean checkWin(Player p) {
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
