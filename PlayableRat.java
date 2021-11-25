import java.util.Random;

public class PlayableRat extends Rat {
    private enum Sex {MALE, FEMALE}

    private Sex sex;
    private boolean isAdult;
    private boolean isPregnant;

    //2 constructors 1 for new babies and the other for existed loaded in rats

    public PlayableRat() {
        super.speed = 2; //arbitrary  number for now
        sex = pickSex();
        isAdult = false;
        super.x = generateRandomX(); //this is not complete - will need to loop until it finds a tile that is not 'G'
        super.y = generateRandomY();
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
