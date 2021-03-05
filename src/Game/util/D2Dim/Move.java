package Game.util.D2Dim;

/** A move so 2 Coord from to */
public class Move {

    public Coord to;
    public Coord from;

    public Move(Coord from, Coord to) {
        this.to = to;
        this.from = from;
    }
}
