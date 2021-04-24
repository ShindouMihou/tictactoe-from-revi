package com.revi.tictactoe.entities;

import com.revi.tictactoe.controllers.PlayerManager;

public class Board {

    //creates the boards array
    private final char[][] cells = {
            {'¹', '²', '³'},
            {'⁴', '⁵', '⁶'},
            {'⁷', '⁸', '⁹'}
    };

    private static final char[] unoccupied = {'¹', '²', '³', '⁴', '⁵', '⁶', '⁷', '⁸', '⁹'};

    public void updateCell(int row, int column, char cell) {
        cells[column][row] = cell;
    }

    public boolean isOccupied(int row, int column) {
        return PlayerManager.getPlayers().stream().anyMatch(player -> cells[column][row] == player.getSymbol());
    }

    public boolean isFull() {
        // As much as I want to avoid multiple inner loops, it is too time-consuming to find a way for that.
        int y = 0;
        for (char[] cell : cells) {
            for (char value : cell) {
                for (char c : unoccupied) {
                    y = value == c ? y + 1 : y;
                }
            }
        }
        return y == 0;
    }

    public char[][] getCells() {
        return cells;
    }

    public char getCell(int row, int column) {
        return cells[column][row];
    }

    public void printBoard() {
        System.out.println(getBoard());
    }

    public String getBoard() {
        StringBuilder board = new StringBuilder();
        board.append("---------\n");

        for (char[] cell : cells) {
            board.append(String.format("| %c %c %c |", cell[0], cell[1], cell[2])).append("\n");
        }

        board.append("---------");
        return board.toString();
    }

}
