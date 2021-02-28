package Game.util;

import Game.WindowInitializer;
import Game.util.D2Dim.Coord;
import Game.util.Object.*;
import Game.util.Object.Object;
import Game.util.Object.ennemy.PnjEnnemy;
import Game.util.Object.ennemy.PnjKing;
import Game.util.Player.Health;
import Game.util.Player.Player;


import javax.swing.*;
import java.awt.*;


/**
 * @author kallu
 * Case with a linkedList of all the object in and coord
 */
public class Case extends JLabel {

    private boolean wall;
    private final Coord coordObject;
    private final ObjectCase objectCase;

    public Case(Coord coordObject) {
        this.objectCase = new ObjectCase();
        this.coordObject = coordObject;
        this.wall = false;
        this.setOpaque(true);
        setBackground(Color.decode("#b98b5e"));
    }

    public void setObjectCase(Object object) {
        this.objectCase.list.add(object);
    }

    /** remove the object in argument from the linkedList*/
    public void removeObject(Object object){
        this.objectCase.list.remove(object);
        System.gc();
    }

    /** get the shield on the case and remove it*/
    public void getShield(){
        for (Object object: objectCase.list){
            if (object instanceof Shield) {
                Health.setNewShieldLevelIncrement();
                removeObject(object);
            }
        }
    }

    /** get the coin on the case and remove it*/
    public int getCoin(){
        int value = 0;
        for (Object object: objectCase.list){
            if (object instanceof Coin) {
                Coin coin  = (Coin) object;
                value = coin.getValue();
                removeObject(object);
                return value;
            }
        }
        return value;
    }

    /** get the value of the bomb and remove it*/
    public int getBomb(boolean remove){
        int value = 0;
        for (Object object: objectCase.list){
            if (object instanceof Bomb) {
                Bomb bomb  = (Bomb) object;
                value = bomb.valueDamage;
                if (remove) removeObject(object);
                setIcon(new javax.swing.ImageIcon(getClass().getResource("Object/blast.png")));
                return value;
            }
        }
        return value;
    }

    /** get if there is object != coin */
    public boolean isFree(Player player){
        if (player.getCoord().equals(this.coordObject)) {
            System.out.println(player.getCoord().toString());
            System.out.println(this.coordObject.toString());
            return false;
        }
        for (Object object: objectCase.list){
            if (! (object instanceof Coin)){
                return false;
            }
        }
        return true;
    }


    /** get the pnj and interact with him*/
    public boolean interactWithPnj(Player player, WindowInitializer window){
        boolean interact = false;
        for (Object object: objectCase.list){
            if (object instanceof PnjMerchand) {
                ((PnjMerchand) object).getItemToSell(player, window);
                interact = true;
            }
        }
        return interact;
    }

    /** get the ennemy and interact with him*/
    public void interactWithEnnemy(Player player){
        for (Object object: objectCase.list){
            if (object instanceof PnjEnnemy) {
                PnjEnnemy ennemy = ((PnjEnnemy) object);
                ennemy.fight(player);
                if (!ennemy.stillAlive) {
                    this.wall = false;
                    player.setMoney(ennemy.reward);
                    objectCase.list.remove(ennemy);
                    if (!PnjKing.kingAlive && PnjKing.deadKingMessage) {
                        PnjKing.deadKingMessage = false;
                        if (! WindowInitializer.hasCheat)
                            JOptionPane.showMessageDialog(this, "well done you killed the king", "YOU WON", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(this, "well done you killed the king but you cheat so it's not a win", "CHEATER", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    /** scan of the ennmy*/
    public boolean scanEnnemy(){
        boolean ennemyScan = false;
        for (Object object: objectCase.list){
            if (object instanceof PnjEnnemy) {
                PnjEnnemy ennemy = ((PnjEnnemy) object);
                JOptionPane.showMessageDialog(this, ennemy.toString(), "Scan" , JOptionPane.INFORMATION_MESSAGE);
                ennemyScan = true;
            }
        }
        return ennemyScan;
    }

    @Override
    public String toString() {
        return "BtnObject{" +
                "objectCase=" + objectCase.toString() +
                '}';
    }

    /** set wall for the obstacle*/
    public void setWall(ImageIcon wallIcon) {
        this.wall = true;
        this.setIcon(wallIcon);
    }



    public boolean isWall() {
        return wall;
    }
}
