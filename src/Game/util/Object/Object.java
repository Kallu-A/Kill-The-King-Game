package Game.util.Object;

import Game.util.Player.Player;

/** Parent class of all objects in the game*/
public class Object {

    protected Object() {
    }

    /** use for the item*/
    public void use(Player player){
        player.addItemInventory(this);
    }
}
