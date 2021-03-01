package Game.util.Object.enemy;

import Game.util.Object.item.KingSword;

/** the king */
public class PnjKing extends PnjEnnemy {

    public static boolean kingAlive = true;
    public static boolean deadKingMessage = true;

    public PnjKing() {
        super(new KingSword(), 450);
        super.reward = 2000;
    }

    @Override
    public String toString() {
        return "<html> <body> <b />"+
                "The King "+ super.toString();
    }
}
