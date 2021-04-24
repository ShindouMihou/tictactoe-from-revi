package com.revi.tictactoe.entities;

public class Player {

    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public Player changeSymbol(char symbol) {
        this.symbol = symbol;
        return this;
    }

    public char getSymbol() {
        return symbol;
    }

}
