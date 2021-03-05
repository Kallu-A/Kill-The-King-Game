package Game.util.Object.item;

/** item Axe*/
public class Axe extends Item{

    public Axe() {
        super(50, 600);
    }

    @Override
    public String toString() {
        return "Axe, attack :"+attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "Axe ATK:"+attack;
    }
}
