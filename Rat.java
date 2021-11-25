import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin and Alex Walker
 */

/*TODO create functioning move method, which should update its location - need to wait to know whether we can make level static*/
public class Rat extends VisibleObject {
    protected int speed;

    //I think this constructor is redundant but i'm not sure - I'll wait for a second opinion
    public Rat(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
    }

    public Rat() {

    }


    public double getSpeed() {
        return speed;
    }

    public void move(){

    }

    public String toString() {
        String returnString = x + ", " + y;
       return returnString;
    }
}
