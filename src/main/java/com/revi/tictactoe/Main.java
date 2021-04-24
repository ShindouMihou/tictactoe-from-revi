package com.revi.tictactoe;

import com.revi.tictactoe.controllers.BoardController;
import com.revi.tictactoe.controllers.Controller;
import com.revi.tictactoe.controllers.PlayerManager;
import com.revi.tictactoe.controllers.Terminal;
import com.revi.tictactoe.entities.Player;

import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        PlayerManager.addPlayer(0, new Player('x'));
        PlayerManager.addPlayer(1, new Player('o'));

        menuAction();
    }

    public static void menuAction() {
        try {
            // Display Menu.
            System.out.println("--------------------------");
            System.out.println("(1). Change Player Symbol(s)");
            System.out.println("(2). Start Game.");
            System.out.println("(3). Exit Game.");
            System.out.println("--------------------------");
            Terminal.log("What do you want to do?");
            int option = Terminal.scanner.nextInt();
            switch (option) {
                case 1:
                    Controller.changeSymbols().thenAccept(unused -> menuAction());
                    break;
                case 2:
                    BoardController.startGame();
                    menuAction();
                    break;
                case 3:
                    Terminal.log("Thanks for playing!");
                    System.exit(1);
                    break;
                default:
                    Terminal.error("Please select an option from the two [1, 2]");
                    menuAction();
                    break;
            }
        } catch (InputMismatchException e) {
            Terminal.error("Please select an option from the two [1, 2]");
            menuAction();
        }

    }
}
