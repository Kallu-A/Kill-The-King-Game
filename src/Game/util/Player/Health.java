package Game.util.Player;

/** the health of the player*/
public class Health {

    private double shield;
    public int levelShield;
    public int life;

    public static final int MAXIMUN_LIFE = 100;
    public static final int MINIMUN_LIFE = 0;

    public Health() {
        this.shield = 1.0;
        this.levelShield = 0;
        this.life = 100;
    }

    /** add to the life the current change*/
    public void setLife(int change){
        if (life == MINIMUN_LIFE) return;
        normLife(change);
    }

    /** norm life for 0<= life <= 100*/
    private void normLife(int change){
        if ( life + change > MAXIMUN_LIFE) life = 100;
        else if ( life + change * shield < MINIMUN_LIFE) life = 0;
        else {
            if (change >= MINIMUN_LIFE) life += change;
            else life += change * shield;
        }
    }

    /** i++ the shield*/
    public void setNewShieldLevelIncrement(){
        if (levelShield >= 9) return;
        levelShield++;
        setShield();
    }

    /** adapt the shield in function of levelShield*/
    private void setShield(){
        shield = 1 - ( (double) levelShield/10);
    }

    public double getShield() {
        return shield;
    }
}
