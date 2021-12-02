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
        super (x, y, "Bomb", "/resources/Images/Items/Bomb.png");
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
                gc.drawImage(new Image("/resources/Images/Items/Bomb.png"), x * tileSize, y * tileSize);
              }else if (timer == 4) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb4.png"), x * tileSize, y * tileSize);
              }else if (timer == 3) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb3.png"), x * tileSize, y * tileSize);
              }else if (timer == 2) {
                gc.drawImage(new Image("/resources/Images/Items/Bomb2.png"), x * tileSize, y * tileSize);
              }else {
                gc.drawImage(new Image("/resources/Images/Items/Bomb1.png"), x * tileSize, y * tileSize);
              }
        }else {
            gc.setFill(Color.YELLOW);
            gc.fillRect((x * tileSize) - (distanceLeft * tileSize), y * tileSize, (distanceLeft * tileSize) + tileSize + (distanceRight * tileSize), tileSize);
            gc.fillRect((x * tileSize), (y * tileSize) - (distanceUp * tileSize), tileSize, (distanceUp * tileSize) + tileSize + (distanceDown * tileSize));
        }
    }

    public boolean collisionAt(int x, int y) {
        if (timer > 0) {
            return false;
        }else {
            if (x >= this.x && x < this.x + 1 && y >= this.y - distanceUp && y < this.y + 1 + distanceDown) {
                if (x >= this.x - distanceRight && x < this.x + 1 + distanceRight && y >= this.y && y < this.y + 1) {
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
}
