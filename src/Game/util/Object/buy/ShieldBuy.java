package Game.util.Object.buy;

import Game.util.Player.Player;

/** shield buyable*/
public class ShieldBuy extends ObjectBuy{

    public ShieldBuy() {
        super(400);
    }

    @Override
    public String toString() {
        return "Armor : 1 piece of armor, price = "+price;
    }

    @Override
    public void use(Player player) {
        player.health.setNewShieldLevelIncrement();
    }
}
