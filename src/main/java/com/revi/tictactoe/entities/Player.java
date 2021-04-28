package com.revi.tictactoe.entities;

public class Player {

    private char symbol;
    private boolean bot;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public Player(char symbol, boolean bot) {
        this.symbol = symbol;
        this.bot = bot;
    }

    public boolean isBot() {
        return bot;
    }

    public Player changeSymbol(char symbol) {
        this.symbol = symbol;
        return this;
    }

    public char getSymbol() {
        return symbol;
    }

}
