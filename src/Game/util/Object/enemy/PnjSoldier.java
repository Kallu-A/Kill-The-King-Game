package Game.util.Object.enemy;

import Game.util.Object.item.SwordPnj;
import Game.util.Player.Player;

/** pnj guard*/
public class PnjSoldier extends PnjEnnemy{

    public PnjSoldier() {
        super(new SwordPnj(), 30);
        super.reward = 140;
    }

    @Override
    public String toString(Player player) {
        return "<html> <body> <b />"+
                "Soldier "+ super.toString(player);
    }

}
