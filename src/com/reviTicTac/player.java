package com.reviTicTac;

import java.util.Scanner;

public class player {

    // creates the scanner object
    public static Scanner sc = new Scanner(System.in);

    // calls boards class and cells array
    public static char[][] cells = board.cells;

    // variable for counting wins
    int wins;

    // creates the players array
    public static char player1 = 'X';
    public static char player2 = 'O';

    // method for changing player symbols
    public static void changeSymbols() {

        // changes the player symbols according to the user's input
        System.out.print("Enter character for player 1: ");
        char newPlayer1 = sc.next().charAt(0);
        player1 = newPlayer1;

        System.out.print("Enter character for player 2: ");
        char newPlayer2 = sc.next().charAt(0);
        player2 = newPlayer2;

        // prints the new symbols out
        System.out.println("Player 1: " + newPlayer1 + "\nPlayer 2: " + newPlayer2);
    }

    // method for declaring if the user is player 1 or 2
    public static int userPlayer() {
        System.out.println("Do you want to be player 1 or 2? (player 2 starts second) ");
        return sc.nextInt() - 1;
    }

}
