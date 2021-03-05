package Game.util.Object.item;

/** the sword for pnj*/
public class SwordPnj extends Item{
    public SwordPnj() {
        super(16, 0);
    }

    @Override
    public String toString() {
        return "Sword, attack :"+ attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "Sword ATK:"+attack;
    }
}


