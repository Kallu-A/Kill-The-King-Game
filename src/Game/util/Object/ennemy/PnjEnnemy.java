package Game.util.Object.ennemy;

import Game.util.Object.Object;
import Game.util.Object.item.Item;
import Game.util.Player.Health;
import Game.util.Player.Player;

/** Is a hostile pnj*/
public class PnjEnnemy extends Object {

    public final Item weapon;
    public int life;
    public int reward;
    public boolean stillAlive = true;

    public PnjEnnemy(Item weapon, int life) {
        super();
        this.weapon = weapon;
        this.weapon.setAttack();
        this.life = life;
        this.reward = 0;
    }

    /** set the fight with the player */
    public void fight(Player player){
        Health.setLife(-weapon.getAttack());
        this.life -= player.current.getAttack();
        if (life <= 0 ) {
            stillAlive = false;
            if (this instanceof PnjKing) PnjKing.kingAlive = false;
        }
    }

    @Override
    public String toString() {
        return "\n Attack : " + weapon.getAttack() +
                "\n Life : " + life +
                "\n Reward : " + reward ;
    }
}
