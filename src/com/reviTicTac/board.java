package com.reviTicTac;

public class board {

    //creates the boards array
    public static char[][] cells = {
            {'¹', '²', '³'},
            {'⁴', '⁵', '⁶'},
            {'⁷', '⁸', '⁹'}
    };

    public static void createBoard(char[][] cells) {
        // creates the board string
        StringBuilder board = new StringBuilder();
        board.append("---------\n");

        for (char[] cell : cells) {
            board.append(String.format("| %c %c %c |", cell[0], cell[1], cell[2])).append("\n");
        }

        board.append("---------");
        System.out.println(board);

    }

    public static void boardOutput() {


    }

}
