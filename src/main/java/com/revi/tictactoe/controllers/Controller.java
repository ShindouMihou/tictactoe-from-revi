package com.revi.tictactoe.controllers;

import com.revi.tictactoe.entities.Player;

import java.util.concurrent.CompletableFuture;

public class Controller {

    public static CompletableFuture<Void> changeSymbols() {
        return CompletableFuture.runAsync(() -> {
            Terminal.log("Enter new character for Player 1:");
            PlayerManager.getPlayer(0).ifPresentOrElse(player -> PlayerManager.updatePlayer(0, player.changeSymbol(Terminal.scanner.next().charAt(0))),
                    () -> PlayerManager.addPlayer(0, new Player(Terminal.scanner.next().charAt(0))));

            Terminal.log("Enter new character for Player 2:");
            PlayerManager.getPlayer(1).ifPresentOrElse(player -> PlayerManager.updatePlayer(1, player.changeSymbol(Terminal.scanner.next().charAt(0))),
                    () -> PlayerManager.addPlayer(1, new Player(Terminal.scanner.next().charAt(0))));
        });
    }

}
