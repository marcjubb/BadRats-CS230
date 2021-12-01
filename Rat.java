import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author samgriffin, Ryan Wake, Marc Jubb
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
    int rnd;

        //I have not put anything to stop it from going off the side of the game yet.
        switch (direction) {
            case NORTH:
                System.out.println("north");
                if ((Level.getLevelLayout()[y + 1][x] != 'G')&&(Level.getLevelLayout()[y][x-1] != 'G')&&(Level.getLevelLayout()[y ][x+1] != 'G')) {
                    rnd = new Random().nextInt(3);
                    if (rnd == 0) {
                        moveUp();
                    } else if (rnd == 1) {
                        moveLeft();
                        direction = Direction.WEST;
                    } else {
                        moveRight();
                        direction = Direction.EAST;
                    };
                }else if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveLeft();
                        direction = Direction.WEST;
                    }else{
                        moveUp();
                    }

                } else if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveRight();
                        direction = Direction.EAST;
                    }else{
                        moveUp();
                    }

                }else if((Level.getLevelLayout()[y+1][x] != 'G')) {
                    moveUp();
                }else {

                        if (Level.getLevelLayout()[y + 1][x] != 'G') {
                            moveUp();
                        } else {
                            if ((Level.getLevelLayout()[y][x - 1] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
                                if (new Random().nextInt(2) == 1) {
                                    moveLeft();
                                    direction = Direction.WEST;
                                } else {
                                    moveRight();
                                    direction = Direction.EAST;
                                }
                                ;
                            } else if (Level.getLevelLayout()[y][x - 1] != 'G') {
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
                    }
                break;




            case SOUTH:
                System.out.println("south");

                if ((Level.getLevelLayout()[y -1][x] != 'G')&&(Level.getLevelLayout()[y][x-1] != 'G')&&(Level.getLevelLayout()[y ][x+1] != 'G')) {
                    rnd = new Random().nextInt(3);
                    if (rnd == 0) {
                        moveDown();
                    } else if (rnd == 1) {
                        moveLeft();
                        direction = Direction.WEST;
                    } else {
                        moveRight();
                        direction = Direction.EAST;
                    };
                } else if ((Level.getLevelLayout()[y-1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveLeft();
                        direction = Direction.WEST;
                    }else{
                        moveDown();
                    }

                } else if ((Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1) {
                        moveRight();
                        direction = Direction.EAST;
                    } else {
                        moveDown();
                    }
                }else if((Level.getLevelLayout()[y-1][x] != 'G')) {
                        moveDown();
                    }else {

                    if ((Level.getLevelLayout()[y][x - 1] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
                        if (new Random().nextInt(2) == 1){
                            moveLeft();
                            direction = Direction.WEST;
                        }else{
                            moveRight();
                            direction = Direction.EAST;
                        };
                    } else if (Level.getLevelLayout()[y][x + 1] != 'G') {
                        moveRight();
                        direction = Direction.EAST;
                        System.out.println("Case:South - Go left(West)");
                    } else if (Level.getLevelLayout()[y][x-1] != 'G') {
                        moveLeft();
                        direction = Direction.WEST;
                        System.out.println("Case:South - Go right(East)");
                    } else {
                        moveUp();
                        direction = Direction.NORTH;
                        System.out.println("hit");
                        System.out.println("Case:South - Go down(South)");
                    }
                }
                break;


            case EAST:

                if ((Level.getLevelLayout()[y + 1][x] != 'G')&&(Level.getLevelLayout()[y-1][x] != 'G')&&(Level.getLevelLayout()[y ][x+1] != 'G')) {
                    rnd = new Random().nextInt(3);
                    if (rnd == 0) {
                        moveRight();
                    } else if (rnd == 1) {
                        moveDown();
                        direction = Direction.SOUTH;
                    } else {
                        moveUp();
                        direction = Direction.NORTH;
                    };

                } else if ((Level.getLevelLayout()[y][x+1] != 'G') && (Level.getLevelLayout()[y-1][x] != 'G')) {

                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveDown();
                        direction = Direction.SOUTH;
                    }else{
                        moveRight();
                    }
                } else if ((Level.getLevelLayout()[y][x+1] != 'G') && (Level.getLevelLayout()[y+1][x] != 'G')) {

                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveUp();
                        direction = Direction.NORTH;
                    }else{
                        moveRight();
                    }

                } else if((Level.getLevelLayout()[y][x+1] != 'G')) {
                    moveRight();
                }else{


                    if ((Level.getLevelLayout()[y+1][x] != 'G') && (Level.getLevelLayout()[y-1][x] != 'G')) {
                        if (new Random().nextInt(2) == 1) {
                            moveUp();
                            direction = Direction.NORTH;
                        } else {
                            moveDown();
                            direction = Direction.SOUTH;
                        };
                    }else if(Level.getLevelLayout()[y + 1][x] != 'G') {
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

                if ((Level.getLevelLayout()[y-1][x] != 'G')&&(Level.getLevelLayout()[y][x-1] != 'G')&&(Level.getLevelLayout()[y+1][x] != 'G')) {
                    rnd = new Random().nextInt(3);
                    if (rnd == 0) {
                        moveLeft();
                    } else if (rnd == 1) {
                        moveDown();
                        direction = Direction.SOUTH;
                    } else {
                        moveUp();
                        direction = Direction.NORTH;
                    };

                } else if ((Level.getLevelLayout()[y+1][x] != 'G') && (Level.getLevelLayout()[y][x-1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveUp();
                        direction = Direction.NORTH;
                    }else{
                        moveLeft();
                    }
                } else if ((Level.getLevelLayout()[y-1][x] != 'G') && (Level.getLevelLayout()[y][x-1] != 'G')) {
                    rnd = new Random().nextInt(2);
                    if (rnd == 1){
                        moveDown();
                        direction = Direction.SOUTH;
                    }else{
                        moveLeft();
                    }

                } else if((Level.getLevelLayout()[y][x-1] != 'G')) {
                    moveLeft();
                }else {
                    if ((Level.getLevelLayout()[y+1][x] != 'G') && (Level.getLevelLayout()[y-1][x] != 'G')) {
                        if (new Random().nextInt(2) == 1) {
                            moveUp();
                            direction = Direction.NORTH;
                        } else {
                            moveDown();
                            direction = Direction.SOUTH;
                        };
                    }else if (Level.getLevelLayout()[y - 1][x] != 'G') {
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