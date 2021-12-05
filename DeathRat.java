import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This Class represents a DeathRat in the game.
 * @author Samuel Griffin and Ryan Wake
 */
public class DeathRat extends Rat {
    private int numCollisions = 0;
    static final private int MAX_KILL_COUNT = 7;
    private int currentKillCount;

    /**
     * Creates a rat object
     * @param x
     * @param y
     * @param currentKillCount
     */
    public DeathRat(int x, int y, int currentKillCount) {
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
        this.currentKillCount = currentKillCount;
    }

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
        return super.toString() + ", " + numCollisions + ", " + currentKillCount;
    }
}
