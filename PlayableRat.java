import javafx.scene.image.Image;
import java.util.Random;

/**
 * This class represents a type of rat that is male or female, adult or baby and has different behaviours depending on
 * these factors.
 * @author Samuel Griffin
 */
public class PlayableRat extends Rat {
    public enum Sex {MALE, FEMALE}
    private static final int PREGNANCY_DURATION = 9;
    private static final int RAT_BECOMES_ADULT_TICK = 4;
    private Sex sex;
    private boolean isAdult;
    private boolean isPregnant;
    private boolean isSterile;
    private int pregnantTick;

    //3 constructors 1 for new babies at start of game, 1 for new babies after given birth and the other for existed loaded in rats


    public PlayableRat() {
        super.speed = BABY_SPEED;
        sex = pickSex();
        isAdult = false;
        int[] xy = generateRandomXY();
        super.x = xy[0]; //this is not complete - will need to loop until it finds a tile that is not 'G'
        super.y = xy[1];
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        pregnantTick = 0;
        isSterile = false;
    }


    /**
     * Create a Playable Rat at the x,y coordinate.
     * @param x The x coordinate the rat will first be on the board.
     * @param y The y coordinate the rat will first be on the board.
     */
    public PlayableRat(int x, int y) {
        super.x = x;
        super.y = y;
        isAdult = false;
        sex = pickSex();
        super.speed = BABY_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        pregnantTick = 0;
        isSterile = false;
    }


    /**
     * Constructor for rats to be loaded from if they already existed in another game.
     * @param x The x coordinate the rat will first be on the board.
     * @param y The y coordinate the rat will first be on the board.
     * @param isAdult Whether the rat is an adult or not.
     * @param isPregnant Whether the rat is pregnant or not.
     * @param sex What sex the rat is.
     * @param ticksSinceCreation The number of ticks since the rat was first created.
     * @param direction The direction the rat is facing
     * @param pregnantTick The number of ticks the rat has been pregnant (if not then 0).
     * @param isSterile Whether or not the rat is sterile.
     */
    public PlayableRat(int x, int y, boolean isAdult, boolean isPregnant, Sex sex, int ticksSinceCreation,
                       Direction direction, int pregnantTick, boolean isSterile) {
        super.x = x;
        super.y = y;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        this.sex = sex;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = ticksSinceCreation;
        super.direction = direction;
        this.isSterile = isSterile;
        this.pregnantTick = pregnantTick;
    }

    /**
     * Gets the sex of the rat.
     * @return The sex enum of the rat.
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Gets whether the rat is pregnant or not.
     * @return Whether or not the rat is pregnant.
     */
    public boolean getIsPregnant() {
        return isPregnant;
    }

    /**
     * Sets whether the rats is sterile or not
     * @param sterile An updated version of whether it is sterile or not
     */
    public void setSterile(boolean sterile) {
        isSterile = sterile;
    }

    /**
     * Gets whether the rat is sterile or not.
     * @return Whether the rat is sterile or not
     */
    public boolean isSterile() {
        return isSterile;
    }

    /**
     * Increments the tick counter in rat and checks whether it should become an adult yet depending on how long it has existed.
     */

    public void incrementTick() {
        if (super.ticksSinceCreation > RAT_BECOMES_ADULT_TICK) {
            becomeAdult();
        }
        super.ticksSinceCreation++;
    }

    /**
     * Sets the image for the rat in the correct orientation for the direction is facing.
     */
    public void setImageDirection() {
        if (Level.getLevelLayout()[y][x] != 'T') {
            switch (getDirection()) {
                case "NORTH":
                    if (isAdult) {
                        if (sex == Sex.MALE) {
                            this.setImg(new Image("/resources/Images/Rat/RatDown.png"));
                        } else {
                            this.setImg(new Image("/resources/Images/Rat/FemaleRatDown.png"));
                        }
                    } else {
                        this.setImg(new Image("/resources/Images/Rat/BabyRatDown.png"));
                    }

                    break;
                case "SOUTH":
                    if (isAdult) {
                        if (sex == Sex.MALE) {
                            this.setImg(new Image("/resources/Images/Rat/RatUp.png"));
                        } else {
                            this.setImg(new Image("/resources/Images/Rat/FemaleRatUp.png"));
                        }
                    } else {
                        this.setImg(new Image("/resources/Images/Rat/BabyRatUp.png"));
                    }
                    break;
                case "EAST":
                    if (isAdult) {
                        if (sex == Sex.MALE) {
                            this.setImg(new Image("/resources/Images/Rat/MRatRight.png"));
                        } else {
                            this.setImg(new Image("/resources/Images/Rat/FRatRight.png"));
                        }
                    } else {
                        this.setImg(new Image("/resources/Images/Rat/BRatRight.png"));
                    }
                    break;
                case "WEST":
                    if (isAdult) {
                        if (sex == Sex.MALE) {
                            this.setImg(new Image("/resources/Images/Rat/MRatLeft.png"));
                        } else {
                            this.setImg(new Image("/resources/Images/Rat/FRatLeft.png"));
                        }
                    } else {
                        this.setImg(new Image("/resources/Images/Rat/BRatLeft.png"));
                    }
                    break;
            }
        } else {
            this.setImg(new Image("/resources/Images/BLANK.png"));
        }
    }

    /**
     * Changes the rat to the opposite Sex.
     */
    public void changeSex() {
        if (sex == Sex.MALE) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
    }

    /**
     * Randomly generates a sex.
     * @return A random sex.
     */
    private Sex pickSex() {
        int elem = new Random().nextInt(Sex.values().length);
        return Sex.values()[elem];
    }

    /**
     * Makes the rat an adult.
     */
    public void becomeAdult() {
        isAdult = true;
        speed = ADULT_SPEED;
    }

//not commenting this one because I don't think it is needed.
    public int[] generateRandomXY() {
        boolean correct = false;
        int[] xy = {(new Random().nextInt(Level.getGridWidth() - 1)),
                (new Random().nextInt(Level.getGridHeight() - 1))};
        do {
            if (Level.getLevelLayout()[xy[1]][xy[0]] == 'G' ||
                    Level.getLevelLayout()[xy[1]][xy[0]] == 'T') {
                xy = new int[]{(new Random().nextInt(Level.getGridWidth() - 1)),
                        (new Random().nextInt(Level.getGridHeight() - 1))};
            } else {
                correct = true;
            }
        } while (!correct);
        return xy;
    }

    /**
     * Checks whether the rat is colliding with other PlayableRats or some items on the board.
     */
    public void checkCollisions() {
        for (Rat rat : Level.getRatList()) {
            if (rat instanceof PlayableRat && rat.getX() == x && rat.getY() == y
                    && sex != ((PlayableRat) rat).getSex() && isAdult
                    && !isPregnant && !this.isSterile
                    && !((PlayableRat) rat).isSterile()) {
                if (this.sex == Sex.FEMALE) {
                    isPregnant = true;
                }
            }
        }

        for (Item item : Level.getItemList()) {
            if (item.getX() == x && item.getY() == y) {
                if (item instanceof MaleSexChange && this.sex == Sex.FEMALE) {
                    changeSex();
                    item.destroySelf();
                } else if (item instanceof MaleSexChange && this.sex == Sex.MALE) {
                        item.destroySelf();
                } else if (item instanceof FemaleSexChange && this.sex == Sex.MALE) {
                    changeSex();
                    item.destroySelf();
                } else if (item instanceof FemaleSexChange && this.sex == Sex.FEMALE) {
                    item.destroySelf();
                }
            }
        }
        super.checkCollisions();
    }


    /**
     * Increments the ticks since the rat has become pregnant.
     */
    public void incrementTickPregnant() {
        pregnantTick++;
    }

    /**
     * Rat gives birth after set duration.
     */
    public void checkPregnancy() {
        if (pregnantTick == PREGNANCY_DURATION) {
            Level.giveBirth(x, y);
            isPregnant = false;
            pregnantTick = 0;
        }
    }

    /**
     * Returns some data from the class in a neatly organised way.
     * @return The attributes stored in the class as well as in Rat.
     */
    @Override
    public String toString() {
        String returnString = ", " + sex.toString() + ", " + isAdult + ", " +
                isPregnant + ", " + pregnantTick;
        returnString = "PlayableRat, " + super.toString() + returnString;
        return returnString;
    }

}