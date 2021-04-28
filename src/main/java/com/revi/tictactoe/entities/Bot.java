package com.revi.tictactoe.entities;
import java.util.Arrays;
import java.util.Random;

public class Bot extends Player {

    private final Random rand = new Random();

    public Bot(char symbol) {
        super(symbol, true);
    }

    public int next(Board b, Player player){
        return b.getFree().stream().filter(integer -> simulate(this, integer, b)).findFirst()
                .orElse(b.getFree().stream().filter(integer -> simulate(player, integer, b)).findFirst()
                        .orElse(b.getFree().get(rand.nextInt(b.getFree().size() - 1))));
    }

    private boolean simulate(Player p, int response, Board b){
        if(!b.isFull()){
            int i = response > 3 && response <= 6 ? 1 : (response <= 3 ? 0 : 2);

            int cell = response <= 3 ? response - 1 : response <= 6 ? response - 4 : response - 7;
            char[][] shells = new char[][]{
                    {'1', '2', '3'},
                    {'4', '5', '6'},
                    {'7', '8', '9'}};

            for(int k = 0; k < 3; k++){
                for(int z = 0; z < 3; z++){
                    shells[k][z] = b.getCells()[k][z];
                }
            }

            shells[i][cell] = p.getSymbol();
            char player = p.getSymbol();
            return (shells[1][0] == player) && (shells[1][1] == player) && (shells[1][2] == player)
                        || (shells[2][0] == player) && (shells[2][1] == player) && (shells[2][2] == player)
                        || (shells[0][0] == player) && (shells[1][0] == player) && (shells[2][0] == player)
                        || (shells[0][1] == player) && (shells[1][1] == player) && (shells[2][1] == player)
                        || (shells[0][2] == player) && (shells[1][2] == player) && (shells[2][2] == player)
                        || (shells[0][0] == player) && (shells[1][1] == player) && (shells[2][2] == player)
                        || (shells[0][2] == player) && (shells[1][1] == player) && (shells[2][0] == player)
                        || (shells[0][0] == player) && (shells[0][1] == player) && (shells[0][2] == player);
        } else {
            return false;
        }
    }

}
