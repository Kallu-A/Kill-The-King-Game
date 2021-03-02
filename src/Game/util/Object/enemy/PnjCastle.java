package Game.util.Object.enemy;

import Game.util.Object.item.Sword;
import Game.util.Player.Player;

public class PnjCastle  extends PnjEnnemy{

    public PnjCastle() {
        super(new Sword(), 500);
        super.reward = 400;
    }

    @Override
    public String toString(Player player) {
        return "<html> <body> <b />"+
                "Castle "+ super.toString(player);
    }

}
