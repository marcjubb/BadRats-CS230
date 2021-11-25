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

    public Rat() {
        super(0, 0);//0 is just a placeholder value it should actually be randomly generated
        speed = 0; //0 is  placeholder value for whatever value we decide on in the end
    }

    public Rat(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
    }


    public double getSpeed() {
        return speed;
    }

    private void generateRandomX(){
        //return new Random().nextInt(Level.getLevelHeight());
    }

    private void generateRandomY(){
        //return
    }

    public String toString() {
        return String.valueOf(speed) + ", " + x + ", " + y;
    }
}
