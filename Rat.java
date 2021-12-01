import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin and Alex Walker
 */

/*TODO Handle for edge cases in move method and find logic errors*/
public class Rat extends VisibleObject {
    protected int speed;
    protected enum Direction {NORTH, SOUTH, EAST, WEST}
    protected Direction direction; //this should probably randomly generated in the constructor
    private enum LeftOrRight {LEFT, RIGHT}

 //I Don't think you need an empty
    public Rat() {

    }
    public String getDirection() {
        return direction.toString();
    }
    public double getSpeed() {
        return speed;
    }
    public void move(){
        Direction leftOrRight = Direction.values()[new Random().nextInt(Direction.values().length)];
        //System.out.println(leftOrRight.toString());

        //I have not put anything to stop it from going off the side of the game yet.
            switch (direction) {
                case NORTH:
                    System.out.println("Case:North - Go up(North)");
                    if (Level.getLevelLayout()[y + 1][x] != 'G') {
                        moveUp();
                    } else {
                        if (Level.getLevelLayout()[y][x - 1] != 'G') {
                            moveLeft();
                            direction = Direction.WEST;
                            System.out.println("Case:North - Go left(West)");
                        } else if (Level.getLevelLayout()[y][x + 1] != 'G') {
                            moveRight();
                            direction = Direction.EAST;
                            System.out.println("Case:North - Go right(East)");
                        } else {
                            moveDown();
                            direction = Direction.SOUTH;
                            System.out.println("Case:North - Go down(South)");
                        }
                    }
                    break;


                case SOUTH:
                    if (Level.getLevelLayout()[y - 1][x] != 'G') {
                        moveDown();
                        System.out.println("Case:South - Go down(South)");
                    } else {
                        if (Level.getLevelLayout()[y + 1][x] != 'G') {
                            moveUp();
                            direction = Direction.NORTH;
                            System.out.println("Case:South - Go Right(East)");
                        } else if (Level.getLevelLayout()[y][x - 1] != 'G') {
                            moveRight();
                            direction = Direction.WEST;
                            System.out.println("Case:South - Go Left(West)");
                        } else {
                            moveLeft();
                            direction = Direction.EAST;
                            System.out.println("Case:South - Go up(North)");
                        }
                    }
                    break;


                case EAST:
                    if (Level.getLevelLayout()[y][x + 1] != 'G') {
                        moveRight();
                        System.out.println("Case:East - Go Right(East)");
                    } else {
                        if (Level.getLevelLayout()[y + 1][x] != 'G') {
                            moveUp();
                            direction = Direction.NORTH;
                            System.out.println("Case:East - Go Up(North)");
                        } else if (Level.getLevelLayout()[y - 1][x] != 'G') {
                            moveDown();
                            direction = Direction.SOUTH;
                            System.out.println("Case:East - Go Down(South)");
                        } else {
                            moveLeft();
                            direction = Direction.WEST;
                            System.out.println("Case:East - Go Left(West)");
                        }
                    }
                    break;

                case WEST:
                    if (Level.getLevelLayout()[y][x - 1] != 'G') {
                        moveLeft();
                        System.out.println("Case:West - Go Left(West)");
                    } else {
                        if (Level.getLevelLayout()[y - 1][x] != 'G') {
                            moveDown();
                            direction = Direction.SOUTH;
                            System.out.println("Case:East - Go Left(West)");
                        } else if (Level.getLevelLayout()[y + 1][x] != 'G') {
                            moveUp();
                            direction = Direction.NORTH;
                            System.out.println("Case:North - Go Up(North)");

                        } else {
                            moveRight();
                            direction = Direction.EAST;
                            System.out.println("Case:South - Go up(North)");

                        }
                    }
                    break;

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
        return x + ", " + y;
    }
}
