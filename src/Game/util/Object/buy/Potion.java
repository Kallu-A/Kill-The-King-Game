package Game.util.Object.buy;

import Game.util.Player.Player;

public class Potion extends ObjectBuy {

    public final int value;

    public Potion(int value) {
        super(value*10);
        if (value > 100) value = 100;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Potion : " +
                "health=" + value +
                ", price=" + super.price;
    }

    @Override
    public void use(Player player) {
        player.health.setLife(value);
    }
}
