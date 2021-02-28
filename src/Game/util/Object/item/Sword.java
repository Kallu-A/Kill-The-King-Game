package Game.util.Object.item;

/** item sword */
public class Sword extends Item{

    public Sword() {
        super(20, 100);
    }

    @Override
    public String toString() {
        return "Sword, attack :"+ attack + " Price = "+price;
    }

    public String toStringPrint(){
        return  "Sword ATK:"+attack;
    }
}
