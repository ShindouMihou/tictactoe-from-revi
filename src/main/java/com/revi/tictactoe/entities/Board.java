package com.revi.tictactoe.entities;

import com.revi.tictactoe.controllers.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final char[][] cells;

    private final char[] unoccupied = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Board() {
        this.cells = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}};
    }

    public void updateCell(int row, int column, char cell) {
        this.cells[column][row] = cell;
    }

    public boolean isOccupied(int row, int column) {
        return PlayerManager.getPlayers().stream().anyMatch(player -> cells[column][row] == player.getSymbol());
    }

    public boolean isFull() {
        return getFree().isEmpty();
    }

    public List<Integer> getFree() {
        List<Integer> a = new ArrayList<>();
        for (char[] cell : cells) {
            for (char value : cell) {
                for (char c : unoccupied) {
                    if (value == c) {
                        a.add(Integer.parseInt(Character.toString(c)));
                    }
                }
            }
        }

        return a;
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
