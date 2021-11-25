import java.util.Random;

public class PlayableRat extends Rat {
    private enum Sex {MALE, FEMALE}

    private Sex sex;
    private boolean isAdult;
    private boolean isPregnant; // can't really remember why we need this - actually i twill probs need to have a getter
    //                             and be called by Level to see if a baby rat needs to be made at that point

    //3 constructors 1 for new babies at start of game, 1 for new babies after given birth and the other for existed loaded in rats

    public PlayableRat() {
        super.speed = 2; //arbitrary  number for now
        sex = pickSex();
        isAdult = false;
        super.x = generateRandomX(); //this is not complete - will need to loop until it finds a tile that is not 'G'
        super.y = generateRandomY();
    }


    public PlayableRat(int x, int y, boolean isAdult, boolean isPregnant){
        super.x = x;
        super.y = y;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        sex = pickSex();

    }


    public PlayableRat(int x, int y, boolean isAdult, boolean isPregnant, Sex sex){
        super.x = x;
        super.y = y;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        this.sex = sex;

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

    private int generateRandomX() {
        return new Random().nextInt(new Level(5, 5).getLevelWidth()); //not actually a new Level object just not sure how that's gonna work yet
    }

    private int generateRandomY() {
        return new Random().nextInt(new Level(5, 5).getLevelHeight()); //not actually a new Level object just not sure how that's gonna work yet
    }
}
