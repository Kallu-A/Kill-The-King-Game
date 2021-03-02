package Game.util;

import javax.swing.*;
import java.util.HashMap;

public class DifficultySelector {

    private final static String EASY = "Easy mode";
    private final static String MEDIUM = "Medium mode (RECOMMENDED)";
    private final static String HARD = "Hard mode";

    /** get the difficulty for the game*/
    public static Difficulty getDifficulty(){
        String[] selection = {
                EASY, MEDIUM, HARD
        };

        java.lang.Object selectionObject = JOptionPane.showInputDialog(null, "What is the difficulty you want", "Selection difficulty",
                JOptionPane.QUESTION_MESSAGE, null, selection, selection[1]);

        return convertor.get(selectionObject);

    }

    /** hashmap for convertor */
    private static final HashMap<Object, Difficulty> convertor = new HashMap<>();
    static {
        convertor.put("Easy mode", Difficulty.EASY);
        convertor.put("Medium mode (RECOMMENDED)", Difficulty.MEDIUM);
        convertor.put("Hard mode", Difficulty.HARD);
    }

}
