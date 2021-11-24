/**
 * This Class represents a rat in the game.
 * @author samgriffin and Alex Walker
 */
public class Rat {
    protected double speed;
    private final int RAT_ID; 
    private static int numberOfRats = 0;

    public Rat() {
        //Set ID
        RAT_ID = numberOfRats++;
        speed = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public int getRatID() {
        return ratID;
    }

    public String toString(){
        return String.valueOf(speed) + ", " + String.valueOf(ratID);
    }
}
