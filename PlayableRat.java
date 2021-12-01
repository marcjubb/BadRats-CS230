import javafx.scene.image.Image;

import java.util.Random;

public class PlayableRat extends Rat {
    static final private int ADULT_SPEED = 2; //arbitrary nums for now
    static final private int BABY_SPEED = 5;
    static final private int PREGNANCY_DURATION = 7;

    private enum Sex {MALE, FEMALE}

    private int ticksSinceCreation; //not sure about this attribute here - would also need a tick update from Level

    private Sex sex;
    private boolean isAdult;
    private boolean isPregnant; // can't really remember why we need this - actually it will probs need to have a getter
    //                             and be called by Level to see if a baby rat needs to be made at that point

    private int pregnantTick;
    //3 constructors 1 for new babies at start of game, 1 for new babies after given birth and the other for existed loaded in rats

    public PlayableRat() {
        super.speed = BABY_SPEED; //arbitrary  number for now
        sex = pickSex();
        isAdult = false;
        int[] xy = generateRandomXY();
        super.x = xy[0]; //this is not complete - will need to loop until it finds a tile that is not 'G'
        super.y = xy[1];
        ticksSinceCreation = 0;
        super.direction = generateDirection();
        pregnantTick = 0;
    }


    public PlayableRat(int x, int y) {
        super.x = x;
        super.y = y;
        sex = pickSex();
        super.speed = ADULT_SPEED;
        ticksSinceCreation = 0;
        super.direction = generateDirection();
        pregnantTick = 0;
    }


    public PlayableRat(int x, int y, boolean isAdult, boolean isPregnant, Sex sex, int ticksSinceCreation, Direction direction, int pregnantTick) {
        super.x = x;
        super.y = y;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        this.sex = sex;
        super.speed = ADULT_SPEED;
        this.ticksSinceCreation = ticksSinceCreation;
        super.direction = direction;
    }

    public Sex getSex() {
        return sex;
    }

    public boolean getIsPregnant(){
        return isPregnant;
    }

    public void incrementTick(){
        if (ticksSinceCreation > 4){
            becomeAdult();
        }
        ticksSinceCreation++;
    }

    public void setImageDirection(){
        switch (getDirection()) {
            case "NORTH":
                if (isAdult){
                    if (sex == Sex.MALE) {
                        this.setImg(new Image("/resources/Images/Rat/RatDown.png"));
                    }else{
                        this.setImg(new Image("/resources/Images/Rat/FemaleRatDown.png"));
                    }
                }else {
                    this.setImg(new Image("/resources/Images/Rat/BabyRatDown.png"));
                }

                break;
            case "SOUTH":
                if (isAdult){
                    if (sex == Sex.MALE) {
                        this.setImg(new Image("/resources/Images/Rat/RatUp.png"));
                    }else{
                        this.setImg(new Image("/resources/Images/Rat/FemaleRatUp.png"));
                    }
                }else {
                    this.setImg(new Image("/resources/Images/Rat/BabyRatUp.png"));
                }
                break;
            case "EAST":
                if (isAdult){
                    if (sex == Sex.MALE) {
                        this.setImg(new Image("/resources/Images/Rat/MRatRight.png"));
                    }else{
                        this.setImg(new Image("/resources/Images/Rat/FRatRight.png"));
                    }
                }else {
                    this.setImg(new Image("/resources/Images/Rat/BRatRight.png"));
                }
                break;
            case "WEST":
                if (isAdult){
                    if (sex == Sex.MALE) {
                        this.setImg(new Image("/resources/Images/Rat/MRatLeft.png"));
                    }else{
                        this.setImg(new Image("/resources/Images/Rat/FRatLeft.png"));
                    }
                }else {
                    this.setImg(new Image("/resources/Images/Rat/BRatLeft.png"));
                }
                break;
        }
        checkCollision();
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
    }


    private int[] generateRandomXY() {
        boolean correct = false;
        int[] xy = {(new Random().nextInt(Level.getGridWidth() -1 )), (new Random().nextInt(Level.getGridHeight() -1 ))};
        do{
            if (Level.getLevelLayout()[xy[1]] [xy[0]] == 'G' || Level.getLevelLayout()[xy[1]] [xy[0]] == 'T'){
                xy = new int[]{(new Random().nextInt(Level.getGridWidth() - 1)), (new Random().nextInt(Level.getGridHeight() - 1))};
            }else{
                correct = true;
            }
        }while (!correct);
        return xy;
    }

    private void checkCollision(){
        for (PlayableRat rat: Level.getRatList()) {
            if (rat.getX() == x && rat.getY() == y && sex != rat.getSex() && isAdult && !isPregnant){
                if (this.sex == Sex.FEMALE){
                    System.out.println("mate");
                    isPregnant = true;
                }
            }
        }
    }


    public void incrementTickPregnant(){
        pregnantTick++;
        System.out.println(pregnantTick);
    }

    public void checkPregnancy(){
        if (pregnantTick == PREGNANCY_DURATION){
            Level.giveBirth(x, y);
            isPregnant = false;
            pregnantTick = 0;
        }
    }





    @Override
    public String toString() {
        String returnString = ", " + ticksSinceCreation + ", " + sex.toString() + ", " + isAdult + ", " + isPregnant;
        returnString = "PlayableRat, " + super.toString() + returnString;
        return returnString;
    }
}