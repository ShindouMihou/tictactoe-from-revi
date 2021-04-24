package com.revi.tictactoe.controllers;

import java.util.Scanner;

public class Terminal {

    public static final Scanner scanner = new Scanner(System.in);

    public static void log(String log) {
        System.out.printf("[Tic-Tac-Toe]: %s\n", log);
    }

    public static void cleanLog(String log) {
        System.out.printf("[Tic-Tac-Toe]: %s", log);
    }

    public static void cleanLogf(String log, Object... variables) {
        System.out.printf("[Tic-Tac-Toe]: %s", String.format(log, variables));
    }

    public static void logf(String log, Object... variables) {
        System.out.printf("[Tic-Tac-Toe]: %s\n", String.format(log, variables));
    }

    public static void errorf(String error, Object... variables) {
        System.out.printf("[Tic-Tac-Toe ERROR]: %s\n", String.format(error, variables));
    }

    public static void error(String error) {
        System.out.printf("[Tic-Tac-Toe ERROR]: %s\n", error);
    }

}
