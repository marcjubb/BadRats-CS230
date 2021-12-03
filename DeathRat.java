import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This Class represents a DeathRat in the game.
 * @author samgriffin and Ryan Wake
 */
 
public class DeathRat extends Rat {
    private int numCollisions = 0;
    //static final private int ADULT_SPEED = 2;
    static final private int MAX_KILL_COUNT = 5;
    private int currentKillCount;
    //private int ticksSinceCreation;



    public DeathRat() {
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        currentKillCount = 0;
    }

    public DeathRat(int x, int y, int currentKillCount) {
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        this.currentKillCount = currentKillCount;
    }

//i was thinking it might be better to remove rats like we do items - just set them as to be destroyed and then just remove those
    public void checkCollisions() {
        Iterator<Rat> iterator = Level.getRatList().listIterator();
        while (iterator.hasNext()) {
            Rat rat = iterator.next();
            if (rat.getX() == x && rat.getY() == y && rat != this) {
                //iterator.remove();
                rat.setDestroyed(true);
                currentKillCount++;
                if (currentKillCount >= 5){
                    this.setDestroyed(true);
                }
            }
        }
        super.checkCollisions();
    }




    public void setImageDirection() {
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
    }
 
}
