package Game.util.Object.item;

import Game.util.Object.buy.ObjectBuy;
import Game.util.Player.Player;

/** item is the object of a player */
public class Item extends ObjectBuy {

    protected int attack;

    public Item(int attack, int price) {
        super(price);
        this.attack = attack;
    }

    @Override
    public void use(Player player) {
        player.addItemInventory(this);
    }

    @Override
    public String toString() {
        return "Item";
    }

    public String toStringPrint(){
        return  "Hand ATK:"+attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(){
    this.attack = attack/2;
    }
}
