package Game.util;

import Game.util.Object.Object;

import java.util.LinkedList;

/**
 * @author kallu
 * Contain all the objet in a case
 */
public class ObjectCase {

    /** LinkedList with all the object*/
    public LinkedList<Object> list;

    public ObjectCase() {
        this.list = new LinkedList<>();
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
