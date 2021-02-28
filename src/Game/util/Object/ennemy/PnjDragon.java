package Game.util.Object.ennemy;

import Game.util.Object.item.DragonFire;

/** dragon pnj*/
public class PnjDragon extends PnjEnnemy{

    public PnjDragon() {
        super(new DragonFire(), 250);
        super.reward = 1300;
    }

    @Override
    public String toString() {
        return "<html> <body> <b />"+
                "Dragon "+ super.toString();
    }

}
