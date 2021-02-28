package Game.util.Object.ennemy;

import Game.util.Object.item.Sword;

public class PnjCastle  extends PnjEnnemy{

    public PnjCastle() {
        super(new Sword(), 500);
        super.reward = 400;
    }

    @Override
    public String toString() {
        return "<html> <body> <b />"+
                "Castle "+ super.toString();
    }

}
