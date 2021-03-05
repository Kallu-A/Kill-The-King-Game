package Game.util.Object.item;

/** item Spear*/
public class Spear extends Item{

    public Spear() {
        super(30, 200);
    }

    @Override
    public String toString() {
        return "Spear, attack :"+attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "Spear ATK:"+attack;
    }
}
