import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin and Alex Walker
 */

/*TODO Handle for edge cases in move method*/
public class Rat extends VisibleObject {
    protected int speed;
    protected enum Direction {NORTH, SOUTH, EAST, WEST}
    protected Direction direction; //this should probably randomly generated in the constructor
    private enum LeftOrRight {LEFT, RIGHT}

    public Rat() {

    }


    public double getSpeed() {
        return speed;
    }

    public void move(){
        Direction leftOrRight = Direction.values()[new Random().nextInt(Direction.values().length)];
        //I have not put anything to stop it from going off the side of the game yet.
        switch (direction) {
            case NORTH:
                if (Level.getLevelLayout()[x][y+1] != 'G') {
                    moveUp();
                } else {
                    if (Level.getLevelLayout()[x-1][y] != 'G') {
                        moveLeft();
                        direction = Direction.WEST;
                    } else if (Level.getLevelLayout()[x+1][y] != 'G') {
                        moveRight();
                        direction = Direction.EAST;
                    } else {
                        moveDown();
                        direction = Direction.SOUTH;
                    }
                }


            case SOUTH:
                if (Level.getLevelLayout()[x][y-1] != 'G') {
                    moveDown();
                } else {
                    if (Level.getLevelLayout()[x+1][y] != 'G') {
                        moveLeft();
                        direction = Direction.EAST;
                    } else if (Level.getLevelLayout()[x-1][y] != 'G') {
                        moveRight();
                        direction = Direction.WEST;
                    } else {
                        moveUp();
                        direction = Direction.NORTH;
                    }
                }


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

            case WEST:
                if (Level.getLevelLayout()[x - 1][y] != 'G') {
                    moveLeft();
                } else {
                    if (Level.getLevelLayout()[x][y - 1] != 'G') {
                        moveDown();
                        direction = Direction.SOUTH;
                    } else if (Level.getLevelLayout()[x][y + 1] != 'G') {
                        moveUp();
                        direction = Direction.NORTH;
                    } else {
                        moveRight();
                        direction = Direction.EAST;
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

    protected Direction generateDirection(){
        int elem = new Random().nextInt(Direction.values().length);
        return Direction.values()[elem];
    }


    public String toString() {
        String returnString = x + ", " + y;
       return returnString;
    }
}
