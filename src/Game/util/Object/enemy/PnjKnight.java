package Game.util.Object.enemy;

import Game.util.Object.item.Spear;
import Game.util.Player.Player;

/** pnj knight*/
public class PnjKnight extends PnjEnnemy{

    public PnjKnight() {
        super(new Spear(), 150);
        super.reward = 330;
    }

    @Override
    public String toString(Player player) {
        return "<html> <body> <b />"+
                "Knight "+ super.toString(player);
    }

}
