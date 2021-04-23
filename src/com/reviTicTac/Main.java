package com.reviTicTac;
import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        menuAction();

        /*
         just stuff that can be ignored >.>
         checks if a player has set one mark too much
        if (player1 > player2 + 1 || player2 > player1 + 1) {
            System.out.println("Impossible.");
        }
         checks if a draw has been made
        else if (player1 + player2 == 9) {
            System.out.println("Draw");
        }
         prints the cell array
         for (char[] cell : cells) {
            System.out.println(Arrays.toString(cell));
        }
        */

    }


    public static void menuAction() {
        // method for the menu so to speak

        // displays userMenu
        System.out.println("""
                --------------------------
                (1) change player symbols\s
                (2) start tic tac toe game
                ---------------------------""");

        System.out.print("What do you want to do? ");

        // takes the users input on the menu's action
        int userMenu = sc.nextInt();

        // takes the user's input and invokes a case
        switch (userMenu) {
            case 1 -> {
                player.changeSymbols();
                menuAction();
            }
            case 2 -> {
                ticTacToe();
                menuAction();
            }
            default -> throw new IllegalStateException("Unexpected value: " + userMenu);
        }

    }


    public static void ticTacToe() {

        // gets the board from the board class
        char[][] cells = board.cells;

        // gets the current players from the player class
        char[] players = {player.player1, player.player2};

        // prints the cell array
        board.createBoard(cells);

        // declares if player or bot is first or second
        int userPlayer = player.userPlayer();
        int botPlayer = 0;
        if (userPlayer == 0) {
            botPlayer++;
        }

        // sets the current player and counts player turns
        int[] playersTotals = {0, 0};
        int player1Turns = playersTotals[0];
        int player2Turns = playersTotals[1];
        int turnCount = 0;

        System.out.println("Ready yourself!");

        while (turnCount < 9) {
                for (char player : players) {
                    makeTurn(cells, player);
                    board.createBoard(cells);
                    turnCount++;
                    if (checkWin(players, cells) != 0){
                        System.out.println(player + " wins!");
                        menuAction();
                        break;
                    }
                }

            }
    }

    // method for the turn
    public static char[][] makeTurn(char[][] cells,char player) {

        // user input of their turn in tic tac toe
        System.out.print("Player " + player + ": enter the field you want to set your mark (1-9): ");
        int userInput = sc.nextInt();
        switch (userInput) {
            case 1 -> {
                if (cells[0][0] == '¹'){   // if the cell is not occupied the users move gets written to the board
                    cells[0][0] = player;
                }   // if the cell is occupied this catches it and asks the user to choose another one
                else { cellOccupied(cells, player); }
            }
            case 2 -> {
                if (cells[0][1] == '²'){
                    cells[0][1] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 3 -> {
                if (cells[0][2] == '³'){
                    cells[0][2] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 4 -> {
                if (cells[1][0] == '⁴'){
                    cells[1][0] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 5 -> {
                if (cells[1][1] == '⁵'){
                    cells[1][1] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 6 -> {
                if (cells[1][2] == '⁶'){
                    cells[1][2] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 7 -> {
                if (cells[2][0] == '⁷'){
                    cells[2][0] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 8 -> {
                if (cells[2][1] == '⁸'){
                    cells[2][1] = player;
                }
                else { cellOccupied(cells, player); }
            }
            case 9 -> {
                if (cells[2][2] == '⁹'){
                    cells[2][2] = player;
                }
                else { cellOccupied(cells, player); }
            }
        }
        return cells;
    }

    // function for printing out that the field the user wanted to make a move
    // is already occupied
    public static void cellOccupied(char[][] cells,char player) {
        System.out.println("The field is occupied! Choose another one!");
        makeTurn(cells, player);
    }

    public static int checkWin(char[] players, char[][] cells) {

        int currentPlayer = 0;
        int winningPlayer = 0;


        // checks if the current player has met a winning condition and returns true to end the loop
        for (char player : players) {
            currentPlayer++;
            if ((cells[1][0] == player) && (cells[1][1] == player) && (cells[1][2] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // horizontal win
            else if ((cells[2][0] == player) && (cells[2][1] == player) && (cells[2][2] == player)) {
                winningPlayer = currentPlayer;
                break;
            } //vertical win
            else if ((cells[0][0] == player) && (cells[1][0] == player) && (cells[2][0] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // vertical win
            else if ((cells[0][1] == player) && (cells[1][1] == player) && (cells[2][1] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // vertical win
            else if ((cells[0][2] == player) && (cells[1][2] == player) && (cells[2][2] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // diagonal win
            else if ((cells[0][0] == player) && (cells[1][1] == player) && (cells[2][2] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // diagonal win
            else if ((cells[0][2] == player) && (cells[1][1] == player) && (cells[2][0] == player)) {
                winningPlayer = currentPlayer;
                break;
            } // horizontal win
            else if ((cells[0][0] == player) && (cells[0][1] == player) && (cells[0][2] == player)) {
                winningPlayer = currentPlayer;
                break;
            }
        }
        return winningPlayer;
    }


    // a method that could be used to continue a game
    // ignore this for now though
    public static void createCells(char[][] cells, String input, char[] players, int player1, int player2){

        // assigns the given cells to the cell array which will function as board
        int loop = 0;
        if (!input.isEmpty())
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    char cell = input.charAt(loop);
                    switch (cell) {
                        case 'X' -> player1++;
                        case 'O' -> player2++;
                    }
                    if (cell == players[0] || cell == players[1])
                        cells[i][j] = cell;
                    else {
                        cells[i][j] = ' ';
                    }
                    loop++;
                }
            }
    }
}
