import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A bomb that explodes after a short period of time.
 * @author Michael Pokorski
 */

public class Bomb extends Item {
    private int timer;
    private int distanceUp;
    private int distanceDown;
    private int distanceLeft;
    private int distanceRight;

    public Bomb(int x, int y) {
        super (x, y, "bomb");
        timer = 5;
        updateDistance();
    }

    public void update() {
        timer--;
        if (timer <= -2) {
            destroySelf();
        }
    }

    public void draw(GraphicsContext gc) {
        if (timer > 0) {
            if (timer == 5) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb.png"), x, y);
              }else if (timer == 4) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb4.png"), x, y);
              }else if (timer == 3) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb3.png"), x, y);
              }else if (timer == 2) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb2.png"), x, y);
              }else {
                gc.drawImage(new Image("/resources/Images/Items/Bomb1.png"), x, y);
              }
        }else {
            gc.setFill(Color.YELLOW);
            gc.fillRect(x - distanceLeft, y, distanceLeft + 64 + distanceRight, 64);
            gc.fillRect(x, y - distanceUp, 64, distanceUp + 64 + distanceDown);
        }
    }

    public boolean collisionAt(int x, int y) {
        if (timer > 0) {
            return false;
        }else {
            if (x >= this.x && x < this.x + 64 && y >= this.y - distanceUp && y < this.y + 64 + distanceDown) {
                if (x >= this.x - distanceRight && x < this.x + 64 + distanceRight && y >= this.y && y < this.y + 64) {
                    return true;
                }
            }
            return false;
        }
    }

    private void updateDistance() {
        distanceUp = 0;
        distanceDown = 0;
        distanceLeft = 0;
        distanceRight = 0;
        int xIndex = x / 64;
        int yIndex = y / 64;

        while (Level.getLevelLayout()[yIndex - ((distanceUp + 64) / 64)][xIndex] != 'G') {
          distanceUp += 64;
        }
        while (Level.getLevelLayout()[yIndex + ((distanceDown + 64) / 64)][xIndex] != 'G') {
          distanceDown += 64;
        }
        while (Level.getLevelLayout()[yIndex][xIndex - ((distanceLeft + 64) / 64)] != 'G') {
          distanceLeft += 64;
        }
        while (Level.getLevelLayout()[yIndex][xIndex + ((distanceRight + 64) / 64)] != 'G') {
          distanceRight += 64;
        }
    }
}
