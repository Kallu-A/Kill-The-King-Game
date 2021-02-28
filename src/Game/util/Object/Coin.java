package Game.util.Object;

/** Object with a value of money*/
public class Coin extends Object {

    private final int value;

    public Coin(int value) {
        super();
        this.value = value + 1;
    }

    public int getValue() {
        return value;
    }
}

