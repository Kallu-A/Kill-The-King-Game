package Game.util.Object.item;

public class KingSword extends Item{

    public KingSword() {
        super(220, 5000);
    }

    @Override
    public String toString() {
        return "King Sword, attack :"+attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "King Sword ATK:"+attack;
    }
}
