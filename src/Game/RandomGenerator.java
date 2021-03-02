package Game;

import Game.util.Case;
import Game.util.D2Dim.Coord;
import Game.util.Object.Coin;
import Game.util.Object.enemy.PnjDragon;
import Game.util.Object.enemy.PnjGorila;
import Game.util.Object.enemy.PnjKnight;
import Game.util.Object.enemy.PnjSoldier;
import Game.util.Player.Player;

/** the random generator of enemy*/
public class RandomGenerator {

    /** difficulty easy*/
    public static void difficultyEasy(WindowInitializer window, int maxShield){

        int random;
        int shield = 0;
        int BOARD_DIM = window.BOARD_DIM;
        Case[][] board = window.board;
        Player player = window.player;
        player.setMoney(60);
        player.health.setNewShieldLevelIncrement();

        for (int i=0; i<BOARD_DIM; i++){
            for (int j=0; j<BOARD_DIM; j++){
                if ((!board[i][j].isWall()) && (i != player.getLine() || j != player.getCol() )){
                    random = (int) (Math.random() * 108);
                    if (random <= 21 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ) {
                        window.putPnj(new Coord(i, j), new PnjSoldier(), window.soldierIcon);
                    }
                    else if (random == 25 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        window.putPnj(new Coord(i, j), new PnjGorila(), window.gorilaIcon);
                    }
                    else if (maxShield > shield && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        if (random ==  30 || random == 31 ) {
                            board[i][j].putShield(window.shieldIcon);
                            shield++;
                        }
                    }
                }
            }
        }

    }

    /** difficulty medium*/
    public static void difficultyMedium(WindowInitializer window, int maxShield){

        int random;
        int shield = 0;
        int BOARD_DIM = window.BOARD_DIM;
        Case[][] board = window.board;
        Player player = window.player;
        player.setMoney(30);

        for (int i=0; i<BOARD_DIM; i++){
            for (int j=0; j<BOARD_DIM; j++){
                if ((!board[i][j].isWall()) && (i != player.getLine() || j != player.getCol() )){
                    random = (int) (Math.random() * 108);
                    if (random <= 9 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ) {
                        window.putPnj(new Coord(i, j), new PnjSoldier(), window.soldierIcon);
                    }
                    else if (random == 11 || random == 12 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        window.putPnj(new Coord(i, j), new PnjGorila(), window.gorilaIcon);
                    }
                    else if (maxShield > shield && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        if (random ==  13 || random == 14 ) {
                            board[i][j].putShield(window.shieldIcon);
                            shield++;
                        }
                    }
                }
            }
        }
    }

    /** difficulty hard*/
    public static void difficultyHard(WindowInitializer window, int maxShield){

        int random;
        int shield = 0;
        int BOARD_DIM = window.BOARD_DIM;
        Case[][] board = window.board;
        Player player = window.player;

        for (int i=0; i<BOARD_DIM; i++){
            for (int j=0; j<BOARD_DIM; j++){
                if ((!board[i][j].isWall()) && (i != player.getLine() || j != player.getCol() )){
                    random = (int) (Math.random() * 108);
                    if (random <= 5 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ) {
                        window.putPnj(new Coord(i, j), new PnjSoldier(), window.soldierIcon);
                    }
                    else if (random >= 10 && random <= 16 && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        window.putPnj(new Coord(i, j), new PnjGorila(), window.gorilaIcon);
                    }
                    else if ( random == 17 || random == 18  || random == 19 && window.getCaseFromCoord(new Coord(i,j)).isFree(player)){
                        window.putPnj(new Coord(i, j), new PnjKnight(), window.knightIcon);
                    }
                    else if ( random == 19 && window.getCaseFromCoord(new Coord(i,j)).isFree(player)){
                        window.putPnj(new Coord(i,j), new PnjDragon(), window.dragonIcon);
                    }
                    else if (maxShield > shield && window.getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        if (random ==  30 || random == 31 ) {
                            board[i][j].putShield(window.shieldIcon);
                            shield++;
                        }
                    }
                }
            }
        }
    }

}
