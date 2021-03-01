package Game.util.Object.enemy;

import Game.util.Object.item.Spear;

/** pnj knight*/
public class PnjKnight extends PnjEnnemy{

    public PnjKnight() {
        super(new Spear(), 150);
        super.reward = 330;
    }

    @Override
    public String toString() {
        return "<html> <body> <b />"+
                "Knight "+ super.toString();
    }

}
