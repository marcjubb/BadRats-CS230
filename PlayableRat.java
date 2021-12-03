import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.Random;

/* */
public class PlayableRat extends Rat {

    static final private int PREGNANCY_DURATION = 9;

    private enum Sex {MALE, FEMALE}


    private Sex sex;
    private boolean isAdult;
    private boolean isPregnant;
    private boolean isSterile;
    private int pregnantTick;
    //3 constructors 1 for new babies at start of game, 1 for new babies after given birth and the other for existed loaded in rats

    public PlayableRat() {
        super.speed = BABY_SPEED; //arbitrary  number for now
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


    public PlayableRat(int x, int y) {
        super.x = x;
        super.y = y;
        sex = pickSex();
        super.speed = BABY_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        pregnantTick = 0;
        isSterile = false;
    }


    public PlayableRat(int x, int y, boolean isAdult, boolean isPregnant, Sex sex, int ticksSinceCreation, Direction direction, int pregnantTick, boolean isSterile) {
        super.x = x;
        super.y = y;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        this.sex = sex;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = ticksSinceCreation;
        super.direction = direction;
        this.isSterile = isSterile;
    }

    public Sex getSex() {
        return sex;
    }

    public boolean getIsPregnant() {
        return isPregnant;
    }

    public void setSterile(boolean sterile) {
        isSterile = sterile;
    }

    public boolean isSterile() {
        return isSterile;
    }

    public void incrementTick() {
        if (super.ticksSinceCreation > 4) {
            becomeAdult();
        }
        super.ticksSinceCreation++;
    }

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

    public void changeSex() {
        if (sex == Sex.MALE) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
    }

    private Sex pickSex() {
        int elem = new Random().nextInt(Sex.values().length);
        return Sex.values()[elem];
    }

    public void becomeAdult() {
        isAdult = true;
        speed = ADULT_SPEED;
    }


    private int[] generateRandomXY() {
        boolean correct = false;
        int[] xy = {(new Random().nextInt(Level.getGridWidth() - 1)), (new Random().nextInt(Level.getGridHeight() - 1))};
        do {
            if (Level.getLevelLayout()[xy[1]][xy[0]] == 'G' || Level.getLevelLayout()[xy[1]][xy[0]] == 'T') {
                xy = new int[]{(new Random().nextInt(Level.getGridWidth() - 1)), (new Random().nextInt(Level.getGridHeight() - 1))};
            } else {
                correct = true;
            }
        } while (!correct);
        return xy;
    }

    public void checkCollisions() {
        for (Rat rat : Level.getRatList()) {
            if (rat instanceof PlayableRat && rat.getX() == x && rat.getY() == y && sex != ((PlayableRat) rat).getSex() && isAdult && !isPregnant && !this.isSterile && !((PlayableRat) rat).isSterile()) {
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


    public void incrementTickPregnant() {
        pregnantTick++;
    }

    public void checkPregnancy() {
        if (pregnantTick == PREGNANCY_DURATION) {
            Level.giveBirth(x, y);
            isPregnant = false;
            pregnantTick = 0;
        }
    }


    @Override
    public String toString() {
        String returnString = ", " + sex.toString() + ", " + isAdult + ", " + isPregnant;
        returnString = "PlayableRat, " + super.toString() + returnString;
        return returnString;
    }
}