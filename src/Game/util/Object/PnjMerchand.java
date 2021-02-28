package Game.util.Object;

import Game.WindowInitializer;
import Game.util.Object.buy.ObjectBuy;
import Game.util.Object.item.Item;
import Game.util.ObjectCase;
import Game.util.Player.Player;

import javax.swing.*;

/** pnj with item to sell*/
public class PnjMerchand extends Object {

    private final ObjectCase inventory;

    public PnjMerchand() {
        super();
        inventory= new ObjectCase();
    }

    /** get all the item of the the pnj*/
    public void getItemToSell(Player player, WindowInitializer window){
        if (inventory.list.size() == 0) {
            JOptionPane.showMessageDialog(window,"he has nothing more to sell");
            return;
        }
        java.lang.Object[] selectionValue = new java.lang.Object[inventory.list.size()];
        int i = 0;
        for (Object object : inventory.list){
            selectionValue[i] = object.toString();
            i++;
        }

        String initialSelec = inventory.list.get(0).toString();
        java.lang.Object selection = JOptionPane.showInputDialog(window, "What do you want to buy ?", "Shop",
                JOptionPane.QUESTION_MESSAGE, null, selectionValue, initialSelec);

        if (selection != null) getUseItem(selection.toString(), player, window);
    }

    /** get all the item to sell to the pnj*/
    public void getItemToBuy(Player player, WindowInitializer window){
        if (player.inventory.list.size() == 1) {
            JOptionPane.showMessageDialog(window,"you have nothing to sell");
            return;
        }

        java.lang.Object[] selectionValue = new java.lang.Object[player.inventory.list.size() - 1];
        int i = -1;
        for (Object object : player.inventory.list){
            if ( i != -1 ){
                selectionValue[i] = object.toString();
            }
            i++;
        }

        String initialSelec = inventory.list.get(1).toString();
        java.lang.Object selection = JOptionPane.showInputDialog(window, "What do you want to sell ?", "Shop",
                JOptionPane.QUESTION_MESSAGE, null, selectionValue, initialSelec);

        if (selection != null) sellItem(selection.toString(), player, window);

    }

    /** add a item to sell */
    public void addItemToSell(Object objectAdd){
        inventory.list.add(objectAdd);
    }

    /** use the item selec and remove it*/
    private void getUseItem(String selection, Player player, WindowInitializer window){
        ObjectBuy object = (ObjectBuy) inventory.list.get(getIndexInventory(selection));
        if (player.getMoney() < object.price){
            JOptionPane.showMessageDialog(window, "Sorry you don't have enough money");
            return;
        }
        player.setMoney(-object.price);
        object.use(player);
        inventory.list.remove(object);
    }

    /** sell an item */
    private void sellItem(String selection, Player player, WindowInitializer window){
        Item item =  (Item) player.inventory.list.get(getIndexInventory(player, selection));
        if (item.price == 0) {
            JOptionPane.showMessageDialog(window, "Sorry you can't sell an item of value 0 coins");
            return;
        }
        player.setMoney(item.price);
        player.current = (Item) player.inventory.list.get(0);
        player.inventory.list.remove(item);
    }

    /** transform a string in a index of the shopping inventory*/
    private int getIndexInventory(String selection){
        int i = 0;
        for (Object object : inventory.list){
            if (object.toString().equals(selection)) return i;
            i++;
        }
        return 0;
    }


    /** transform a string in a index of the selling inventory*/
    private int getIndexInventory(Player player, String selection){
        int i = 0;
        for (Object object : player.inventory.list){
            if (object.toString().equals(selection)) return i;
            i++;
        }
        return 0;
    }
}
