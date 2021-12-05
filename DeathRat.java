import javafx.scene.image.Image;

/**
 * This Class represents a DeathRat in the game.
 * @author Samuel Griffin and Ryan Wake
 */
public class DeathRat extends Rat {
    static final private int MAX_KILL_COUNT = 7;
    private int currentKillCount;

    /**
     * Creates a DeathRat object at some coordinates, setting its current kill count as well.
     * @param x The x coordinate for where the rat object should be created.
     * @param y The y coordinate for where the rat object should be created.
     * @param currentKillCount The number of kills the death rat has already got.
     */
    public DeathRat(int x, int y, int currentKillCount) {
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        this.currentKillCount = currentKillCount;
    }


    /**
     * Creates a death rat at some coordinates, setting its current kill count, the ticks since it h
     * @param x The x coordinate for where the rat object should be created.
     * @param y The y coordinate for where the rat object should be created.
     * @param currentKillCount
     * @param ticksSinceCreation
     * @param direction
     */
    public DeathRat(int x, int y, int currentKillCount, int ticksSinceCreation, String direction){
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = ticksSinceCreation;
        this.currentKillCount = currentKillCount;
        switch(direction){
            case "NORTH":
                this.direction = Direction.NORTH;
                break;
            case "SOUTH":
                this.direction = Direction.SOUTH;
                break;
            case "EAST":
                this.direction = Direction.EAST;
                break;
            case "WEST":
                this.direction = Direction.WEST;
        }
    }

    /**
     * Checks whether the rat collides with other rats and kills them as well as checking for the collisions that happen
     * to all rats.
     */
    public void checkCollisions() {
        for (Rat rat : Level.getRatList()) {
            if (rat.getX() == x && rat.getY() == y && rat != this) {
                rat.setDestroyed(true);
                currentKillCount++;
                if (currentKillCount >= MAX_KILL_COUNT) {
                    this.setDestroyed(true);
                }
            }
        }
        super.checkCollisions();
    }


    /**
     * Sets the image to one that faces in the direction of the rat and if it is in a tunnel it changes to a blank png.
     */
    public void setImageDirection() {
        if (Level.getLevelLayout()[y][x] != 'T') {
            switch (getDirection()) {
                case "NORTH":
                    this.setImg(new Image("resources/Images/Rat/DeathRatDown.png"));
                    break;
                case "SOUTH":
                    this.setImg(new Image("resources/Images/Rat/DeathRatUp.png"));
                    break;
                case "EAST":
                    this.setImg(new Image("resources/Images/Rat/Rat10.png"));
                    break;
                case "WEST":
                    this.setImg(new Image("resources/Images/Rat/Rat11.png"));
                    break;
            }
        } else {
            this.setImg(new Image ("resources/Images/BLANK.png"));
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + currentKillCount;
    }
}
