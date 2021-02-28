package Game.util.Object.ennemy;

import Game.util.Object.item.SwordPnj;

/** pnj guard*/
public class PnjSoldier extends PnjEnnemy{

    public PnjSoldier() {
        super(new SwordPnj(), 30);
        super.reward = 140;
    }

    @Override
    public String toString() {
        return "<html> <body> <b />"+
                "Soldier "+ super.toString();
    }

}
