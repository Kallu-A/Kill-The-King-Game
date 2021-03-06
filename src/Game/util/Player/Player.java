package Game.util.Player;
import Game.WindowInitializer;
import Game.util.D2Dim.Coord;
import Game.util.D2Dim.Move;
import Game.util.Object.Object;
import Game.util.Object.item.Hand;
import Game.util.Object.item.Item;
import Game.util.ObjectCase;

import javax.swing.*;

import java.util.LinkedList;

/** Player of the game*/
public class Player {

    private Coord coord;
    private final WindowInitializer board;
    public final ImageIcon iconPlayer;
    public final ImageIcon ground;
    public final ImageIcon playerBomb;
    private int money;
    public final ObjectCase inventory;
    public Item current;
    public Health health;

    public Player(WindowInitializer board, Coord coord) {
        ImageIcon[] stateIcon = board.getPlayerState();
        health = new Health();
        this.coord = coord;
        this.board = board;
        this.money = 0;
        this.inventory = new ObjectCase();
        inventory.list.add(new Hand());
        current = (Item) inventory.list.get(0);

        iconPlayer = stateIcon[0];
        setPlayerNewPosition(coord, true);
        playerBomb = stateIcon[1];
        ground = stateIcon[2];
    }


    /** try if the coord is valid or not and do the change*/
    public void setCoord(Coord coord) {
        if (!board.isBoard(coord) || board.getCaseFromCoord(coord).isWall()) return;
        LinkedList<Coord> coordAdj = this.coord.getCoordAdjacent(board);
        for (Coord value : coordAdj) {
            if (value.isCoordEqual(coord) && !value.isCoordEqual(this.coord)) {
                setPlayerPosition(new Move(value, this.coord));
                return;
            }
        }
    }

    /** change the position of the player with the Move*/
    public void setPlayerPosition(Move move){
        setPlayerNewPosition(move.from, false);
        setPlayerOldPosition(move.to);
        board.setInfoGame();
    }

    /** change the position of the player on the coord*/
    private void setPlayerNewPosition(Coord coord, boolean firstTime){
        board.getCaseFromCoord(this.coord);
        board.getCaseFromCoord(coord).setIcon(iconPlayer);
        this.coord = coord;
        if (!firstTime) set(coord);

    }

    /** change the oldest position of the player on the coord*/
    private void setPlayerOldPosition(Coord coord){
        board.getCaseFromCoord(coord).setIcon(ground);
    }

    public int getLine() {
        return coord.line;
    }

    public int getCol() {
        return coord.col;
    }

    public int getMoney() {
        return money;
    }

    /** set all the action when a move*/
    public void set(Coord coord){
        setMoney(board.getCaseFromCoord(coord).getCoin() );
        setMine(board.getCaseFromCoord(coord).getBomb(true, this.board));
        board.getCaseFromCoord(coord).getShield(this);
    }

    /** set the money*/
    public void setMoney(int money) {
        this.money += money;
    }

    public void setMine(int value){
        health.setLife(value);
        board.setInfoGame();
    }

    public Coord getCoord() {
        return coord;
    }


    /** get all the item of the the player*/
    public void getItemInventory(){
        if (inventory.list.size() == 0) {
            JOptionPane.showMessageDialog(board,"you don't have items");
            return;
        }
        java.lang.Object[] selectionValue = new java.lang.Object[inventory.list.size()];
        int i = 0;
        for (Object object :  inventory.list){
            selectionValue[i] = object.toString();
            i++;
        }

        String initialSelec = inventory.list.get(0).toString();
        java.lang.Object selection = JOptionPane.showInputDialog(board, "What do you want to use ?", "Select you'r weapon",
                JOptionPane.QUESTION_MESSAGE, null, selectionValue, initialSelec);

        if (selection != null) current =  (Item) inventory.list.get(getIndexInventory(selection.toString()));
    }


    /** transform a string in a index of the player inventory*/
    private int getIndexInventory(String selection){
        int i = 0;
        for (Object object : inventory.list){
            if (object.toString().equals(selection)) return i;
            i++;
        }
        return 0;
    }

    public void addItemInventory(Object object){
        inventory.list.add(object);
        current = (Item) object;
    }
}
