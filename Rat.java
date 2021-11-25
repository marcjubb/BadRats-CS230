import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin and Alex Walker
 */

/*TODO Remove RAT_ID and related references
 *  create functioning move method, which should update its location*/
public class Rat extends VisibleObject {
    protected int speed;

    public Rat(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
    }

    public Rat() {

    }


    public double getSpeed() {
        return speed;
    }



    public String toString() {
        return String.valueOf(speed) + ", " + x + ", " + y;
    }
}
