package com.revi.tictactoe.controllers;

import com.revi.tictactoe.entities.Player;

import java.util.*;

public class PlayerManager {

    // We are using a list of players for scalability, in case we want to scale up to 12 players.
    private static final List<Player> players = new ArrayList<>();

    public static Optional<Player> getPlayer(int id) {
        return Optional.ofNullable(players.get(id));
    }

    public static void updatePlayer(int id, Player player) {
        if (Objects.nonNull(players.get(id))) {
            players.set(id, player);
        } else {
            throw new NoSuchElementException("The player [" + id + "] does not exist.");
        }
    }

    public static void addPlayer(int id, Player player) {
        players.add(id, player);
    }

    public static int addPlayer(char symbol) {
        players.add(players.size(), new Player(symbol));
        return players.size() - 1;
    }

    public static List<Player> getPlayers() {
        return players;
    }


}
