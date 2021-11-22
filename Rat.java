/**
 * This Class represents a rat in the game.
 * @author samgriffin
 */
public class Rat {
    protected double speed;
    private int ratID;

    public Rat() {
        speed = 0;
        ratID = 0;
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
