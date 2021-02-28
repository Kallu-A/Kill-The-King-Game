package Game.util.Player;

/** the health of the player*/
public class Health {

    private static double shield = 1.0;
    public static int levelShield = 0;
    public static int life = 100;

    public static final int MAXIMUN_LIFE = 100;
    public static final int MINIMUN_LIFE = 0;
    
    public static boolean stillAlive = true;


    /** add to the life the current change*/
    public static void setLife(int change){
        if (!stillAlive) return;
        normLife(change);
        if (life == 0) stillAlive = false;
    }

    /** norm life for 0<= life <= 100*/
    private static void normLife(int change){
        if ( life + change > MAXIMUN_LIFE) life = 100;
        else if ( life + change * shield < MINIMUN_LIFE) life = 0;
        else {
            if (change >= 0) life += change;
            else life += change * shield;
        }
    }

    /** incremente the shield*/
    public static void setNewShieldLevelIncrement(){
        if (levelShield > 9) return;
        levelShield++;
        setShield();
    }

    /** adapt the shield in function of levelShield*/
    private static void setShield(){
        shield = 1 - ( (double) levelShield/10);
    }

}
