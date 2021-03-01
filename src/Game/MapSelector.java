package Game;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/** here is the map selector make sur if you create a new map to put it in*/
public class MapSelector {

    private static boolean hasUIset = false;

    public MapSelector() {
        if (!hasUIset) {
            initUi();
            hasUIset = true;
        }
        System.gc();

        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;

        String[] selectionValue =
                {
                "Default Map",
                "Capital Map",
                //add string of you'r map here
                // <---
                };

        String defaultValue = selectionValue[0];
        java.lang.Object selection = JOptionPane.showInputDialog(null, "What map do you want to play on ?", "Selection Map",
                JOptionPane.QUESTION_MESSAGE, null, selectionValue, defaultValue);

        if (selection == null) return;
        switch (selection.toString()){

            case "Default Map":
                new DefaultMap();
                break;

            case "Capital Map":
                new CapitalMap();
                break;

                // add your case statement here be carefull it's the same String in the selectionValue

            default:break;
        }
    }

    /** set the look and feel*/
    private void initUi(){
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException error) {
            JOptionPane.showMessageDialog(null, "Sorry the look have some issue", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
