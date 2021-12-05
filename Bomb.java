import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A bomb that explodes after a short period of time.
 * @author Michael Pokorski
 */

public class Bomb extends Item {
    private final int EXPLOSION_TIMER_COUNT_FIVE = 5;
    private final int EXPLOSION_TIMER_COUNT_FOUR = 4;
    private final int EXPLOSION_TIMER_COUNT_THREE = 3;
    private final int EXPLOSION_TIMER_COUNT_TWO = 2;
    private int BOMB_TIMER = 5;
    private int EXPLOSION_TIME = -2;
    private int timer;
    private int distanceUp;
    private int distanceDown;
    private int distanceLeft;
    private int distanceRight;



    /**
     * Create a Bomb at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Bomb(int x, int y) {
        super (x, y, "Bomb", "/resources/Images/Items/Bomb.png");
        timer = BOMB_TIMER;
        updateDistance();
    }

    /**
     * Create a Bomb at a specified coordinate with a particular timer state.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param t The timer value.
     */
    public Bomb(int x, int y, int t) {
        super (x, y, "Bomb", "/resources/Images/Items/Bomb.png");
        timer = t;
        updateDistance();
    }

    /**
     * Will update the timer every tick until the timer runs out and bomb will be destroyed.
     */
    public void update() {
        timer--;
        if (timer <= EXPLOSION_TIME) {
            destroySelf();
        }
    }

    /**
     * Draw the item on the canvas.
     * @param gc The GraphicsContext in which we will draw on
     */
    public void draw(GraphicsContext gc) {

        if (timer > 0) {
            if (timer == EXPLOSION_TIMER_COUNT_FIVE) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb.png"),
                        x * tileSize, y * tileSize);
              }else if (timer == EXPLOSION_TIMER_COUNT_FOUR) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb4.png"),
                        x * tileSize, y * tileSize);
              }else if (timer == EXPLOSION_TIMER_COUNT_THREE) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb3.png"),
                        x * tileSize, y * tileSize);
              }else if (timer == EXPLOSION_TIMER_COUNT_TWO) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb2.png"),
                        x * tileSize, y * tileSize);
              }else {
                gc.drawImage(new Image("/resources/Images/Items/Bomb1.png"),
                        x * tileSize, y * tileSize);
              }
        } else {
            gc.setFill(Color.YELLOW);
            gc.fillRect((x - distanceLeft) * tileSize, y * tileSize,
                    (distanceLeft + 1 + distanceRight)  * tileSize, tileSize);
            gc.fillRect(x  * tileSize, (y - distanceUp) * tileSize, tileSize,
                    (distanceUp + 1 + distanceDown) * tileSize);
        }
    }

    /**
     * Check if a collision has occurred at specific coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if collision has occurred else False.
     */
    public boolean collisionAt(int x, int y) {
        if (timer > 0) {
            return false;
        } else {
            if (x >= this.x && x < this.x + 1 && y >= this.y - distanceUp &&
                    y < this.y + 1 + distanceDown) {
                return true;
            }
            return x >= this.x - distanceLeft && x < this.x + 1 + distanceRight &&
                    y >= this.y && y < this.y + 1;
        }
    }

    /**
     * Determines the distance from current position until a solid tile in each direction.
     */
    private void updateDistance() {
        distanceUp = 0;
        distanceDown = 0;
        distanceLeft = 0;
        distanceRight = 0;

        while (Level.getLevelLayout()[y - (distanceUp + 1)][x] != 'G') {
          distanceUp += 1;
        }
        while (Level.getLevelLayout()[y + (distanceDown + 1)][x] != 'G') {
          distanceDown += 1;
        }
        while (Level.getLevelLayout()[y][x - (distanceLeft + 1)] != 'G') {
          distanceLeft += 1;
        }
        while (Level.getLevelLayout()[y][x + (distanceRight + 1)] != 'G') {
          distanceRight += 1;
        }
    }

    /**
     * Get the data of the bomb.
     * @return the Bombs data.
     */
    public String toString() {
        return super.toString() + ", " + timer + "\n";
    }

}
