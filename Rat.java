import java.util.Random;

/**
 * This Class represents a rat in the game.
 *
 * @author Samuel Griffin, Ryan Wake, Marc Jubb
 */

/*TODO Handle for edge cases in move method and find logic errors*/
public abstract class Rat extends VisibleObject {
    static final protected int ADULT_SPEED = 2; //in ticks per square moved
    static final protected int BABY_SPEED = 1;
    protected int speed;
    private int ticksInGas;

    public enum Direction {NORTH, SOUTH, EAST, WEST}

    protected Direction direction;
    protected Item item;
    protected int ticksSinceCreation;


    //getters

    public String getDirection() {
        return direction.toString();
    }

    public double getSpeed() {
        return speed;
    }

    public int getTicksInGas() {
        return ticksInGas;
    }


    /**
     * Increments the amount of time the rat has been in the gas
     */
    public void incrementTicksInGas() {
        ticksInGas++;
    }

    /**
     * Responsible for checking collisions that both death rats and playable rats have and effecting itself or what it
     * is effecting accordingly.
     */
    public void checkCollisions() {
        for (Item item : Level.getItemList()) {
            if (item instanceof NoEntrySign) {
                switch (direction) {
                    case NORTH:
                        if (item.collisionAt(x, y)) {
                            this.setY(y - 1);
                            direction = Direction.SOUTH;
                            ((NoEntrySign) item).damage();
                        }
                    case SOUTH:
                        if (item.collisionAt(x, y)) {
                            this.setY(y + 1);
                            direction = Direction.NORTH;
                            ((NoEntrySign) item).damage();
                        }
                    case EAST:
                        if (item.collisionAt(x, y)) {
                            this.setX(x - 1);
                            direction = Direction.WEST;
                            ((NoEntrySign) item).damage();
                        }
                    case WEST:
                        if (item.collisionAt(x, y)) {
                            this.setX(x + 1);
                            direction = Direction.EAST;
                            ((NoEntrySign) item).damage();
                        }
                }
            }

            if (item.collisionAt(x,y)) {
                if (item instanceof Poison) {
                    this.setDestroyed(true);
                    item.destroySelf();
                }

                if (item instanceof Bomb) {
                    this.setDestroyed(true);
                }
            }

        }
    }

    /**
     * Responsible for moving the rats in different directions, depending on the way they are facing.
     */
    public void move() {
        int rnd;

        //I have not put anything to stop it from going off the side of the game yet.
        switch (direction) {
            case NORTH:
                moveForNorth();
                break;

            case SOUTH:
                moveForSouth();
                break;

            case EAST:
                moveForEast();
                break;

            case WEST:
                moveForWest();
                break;
        }
    }

    /**
     * Performs the movement for a rat if it is facing North.
     */
    public void moveForNorth(){
        int rnd;
        if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
            rnd = new Random().nextInt(3);
            if (rnd == 0) {
                moveUp();
            } else if (rnd == 1) {
                moveLeft();
                direction = Direction.WEST;
            } else {
                moveRight();
                direction = Direction.EAST;
            }
            ;
        } else if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveLeft();
                direction = Direction.WEST;
            } else {
                moveUp();
            }

        } else if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveRight();
                direction = Direction.EAST;
            } else {
                moveUp();
            }

        } else if ((Level.getLevelLayout()[y + 1][x] != 'G')) {
            moveUp();
        } else {

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
                } else if (Level.getLevelLayout()[y][x + 1] != 'G') {
                    moveRight();
                    direction = Direction.EAST;
                } else {
                    moveDown();
                    direction = Direction.SOUTH;
                }
            }
        }
    }

    /**
     * Performs the movement for a rat if it is facing South.
     */
    private void moveForSouth(){
        int rnd;
        if ((Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
            rnd = new Random().nextInt(3);
            if (rnd == 0) {
                moveDown();
            } else if (rnd == 1) {
                moveLeft();
                direction = Direction.WEST;
            } else {
                moveRight();
                direction = Direction.EAST;
            }
            ;
        } else if ((Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveLeft();
                direction = Direction.WEST;
            } else {
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
        } else if ((Level.getLevelLayout()[y - 1][x] != 'G')) {
            moveDown();
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
            } else if (Level.getLevelLayout()[y][x + 1] != 'G') {
                moveRight();
                direction = Direction.EAST;
            } else if (Level.getLevelLayout()[y][x - 1] != 'G') {
                moveLeft();
                direction = Direction.WEST;
            } else {
                moveUp();
                direction = Direction.NORTH;
            }
        }

    }

    /**
     * Performs the movement for a rat if it is facing East.
     */
    private void moveForEast(){
        int rnd;
        if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x + 1] != 'G')) {
            rnd = new Random().nextInt(3);
            if (rnd == 0) {
                moveRight();
            } else if (rnd == 1) {
                moveDown();
                direction = Direction.SOUTH;
            } else {
                moveUp();
                direction = Direction.NORTH;
            }
            ;

        } else if ((Level.getLevelLayout()[y][x + 1] != 'G') && (Level.getLevelLayout()[y - 1][x] != 'G')) {

            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveDown();
                direction = Direction.SOUTH;
            } else {
                moveRight();
            }
        } else if ((Level.getLevelLayout()[y][x + 1] != 'G') && (Level.getLevelLayout()[y + 1][x] != 'G')) {

            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveUp();
                direction = Direction.NORTH;
            } else {
                moveRight();
            }

        } else if ((Level.getLevelLayout()[y][x + 1] != 'G')) {
            moveRight();
        } else {


            if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y - 1][x] != 'G')) {
                if (new Random().nextInt(2) == 1) {
                    moveUp();
                    direction = Direction.NORTH;
                } else {
                    moveDown();
                    direction = Direction.SOUTH;
                }
                ;
            } else if (Level.getLevelLayout()[y + 1][x] != 'G') {
                moveUp();
                direction = Direction.NORTH;
            } else if (Level.getLevelLayout()[y - 1][x] != 'G') {
                moveDown();
                direction = Direction.SOUTH;
            } else {
                moveLeft();
                direction = Direction.WEST;
            }

        }

    }

    /**
     * Performs the movement for a rat if it is facing West
     */
    private void moveForWest(){
        int rnd;
        if ((Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G') && (Level.getLevelLayout()[y + 1][x] != 'G')) {
            rnd = new Random().nextInt(3);
            if (rnd == 0) {
                moveLeft();
            } else if (rnd == 1) {
                moveDown();
                direction = Direction.SOUTH;
            } else {
                moveUp();
                direction = Direction.NORTH;
            }
            ;

        } else if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveUp();
                direction = Direction.NORTH;
            } else {
                moveLeft();
            }
        } else if ((Level.getLevelLayout()[y - 1][x] != 'G') && (Level.getLevelLayout()[y][x - 1] != 'G')) {
            rnd = new Random().nextInt(2);
            if (rnd == 1) {
                moveDown();
                direction = Direction.SOUTH;
            } else {
                moveLeft();
            }

        } else if ((Level.getLevelLayout()[y][x - 1] != 'G')) {
            moveLeft();
        } else {
            if ((Level.getLevelLayout()[y + 1][x] != 'G') && (Level.getLevelLayout()[y - 1][x] != 'G')) {
                if (new Random().nextInt(2) == 1) {
                    moveUp();
                    direction = Direction.NORTH;
                } else {
                    moveDown();
                    direction = Direction.SOUTH;
                }
                ;
            } else if (Level.getLevelLayout()[y - 1][x] != 'G') {
                moveDown();
                direction = Direction.SOUTH;
            } else if (Level.getLevelLayout()[y + 1][x] != 'G') {
                moveUp();
                direction = Direction.NORTH;

            } else {
                moveRight();
                direction = Direction.EAST;

            }
        }
    }


    /**
     * Abstract method that is defined in classes that extend from this class.
     */
    public abstract void setImageDirection();


    /**
     * Increments the amount of ticks the instance has existed for.
     */
    public void incrementTick() {
        ticksSinceCreation++;
    }

    /**
     * Moves the rat right by 1 square on the board.
     */
    public void moveRight() {
        x = x + 1;
    }

    /**
     * Moves the rat left by 1 square on the board.
     */
    public void moveLeft() {
        x = x - 1;
    }

    /**
     * Moves the rat down by 1 square on the board.
     */
    public void moveDown() {
        y = y - 1;
    }


    /**
     * Moves the rat up by 1 square on the board.
     */
    public void moveUp() {
        y = y + 1;
    }

    /**
     * Generates a random direction for the rat to face.
     * @return the direction for the rat to face.
     */
    protected Direction generateDirection() {
        int elem = new Random().nextInt(Direction.values().length);
        return Direction.values()[elem];
    }


    /**
     *
     * @return data that would need to be saved to the file.
     */
    public String toString() {
        return x + ", " + y + ", "+ ticksSinceCreation + ", " + direction;
    }
}