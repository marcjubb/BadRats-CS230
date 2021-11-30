import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin and Alex Walker
 */

/*TODO create functioning move method, which should update its location - need to wait to know whether we can make level static*/
public class Rat extends VisibleObject {
    protected int speed;
    private enum Direction {NORTH, SOUTH, EAST, WEST}
    private Direction direction = Direction.EAST; //this should probably randomly generated in the constructor

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
        switch (direction) {
            case NORTH:
                //


            case SOUTH:
                //


            case EAST:
                if (Level.getLevelLayout()[x + 1][y] != 'G') {
                    moveRight();
                } else {
                    if (Level.getLevelLayout()[x][y + 1] != 'G') {
                        moveUp();
                        direction = Direction.NORTH;
                    } else if (Level.getLevelLayout()[x][y - 1] != 'G') {
                        moveDown();
                        direction = Direction.SOUTH;
                    } else {
                        moveLeft();
                        direction = Direction.WEST;
                    }
                }
        }
    }

    public void moveRight(){
        x = x + 1;
    }
    public void moveLeft(){
        x = x - 1;
    }

    public void moveDown(){
        y = y - 1;
    }

    public void moveUp(){
        y = y + 1;
    }


    public String toString() {
        String returnString = x + ", " + y;
       return returnString;
    }
}
