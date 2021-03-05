package Game.util.Object.enemy;

import Game.util.Object.item.HandGorila;
import Game.util.Player.Player;

/** pnj gorila*/
public class PnjGorila  extends PnjEnnemy{

    public PnjGorila() {
        super(new HandGorila(), 70);
        super.reward = 320;
    }

    @Override
    public String toString(Player player) {
        return "<html> <body> <b />"+
                "Gorila "+ super.toString(player);
    }

}
