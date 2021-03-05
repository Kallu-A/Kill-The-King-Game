package Game.util.Object.item;

/** best weapon of the game */
public class LegendarieWeapon extends Item{

    public LegendarieWeapon() {
        super(100, 2500);
    }

    @Override
    public String toString() {
        return "Kallu Sword, Attack :"+attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "KalluSword ATK:"+attack;
    }

}
