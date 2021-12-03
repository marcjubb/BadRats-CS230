
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Gas extends Item {

    private int timer;
    private boolean visibility;
    private int distanceUp;
    private int distanceDown;
    private int distanceRight;

    public Gas(int x, int y) {
        super(x, y, "Gas", "/resources/Images/Items/Gas.png");
        timer = 5;
    }

   /* public void gasArea() {
        for (int x = getX() - 1; x < getX() + 2; x++) {
            for (int y = getY() - 1; y < getY() + 2; y++) {
                for (Rat rat : Level.getRatList()) {
                    if (rat.getX() == x && rat.getY() == y && rat instanceof PlayableRat) {
                        ((PlayableRat) rat).setSterile(true);
                    }
                }
            }
        }
    }*/

    public void update() {
        timer--;
        if (timer <= 0) {
            destroySelf();
        }
    }

public void draw(GraphicsContext gc) {
    int radius = 2- (Math.floorDiv(timer,2));
        for (int x = getX()- radius; x < getX() + radius+1; x++) {
            for (int y = getY() - radius; y < getY()+ radius+1; y++) {
                if ( isInBounds(x,y)) {
                    if (Level.getLevelLayout()[y][x] != 'G') {
                        gc.drawImage(new Image("/resources/Images/Items/Gas.png"), x * Level.getGridCellHeight(), y * Level.getGridCellHeight());
                    }
                }
            }
        }
 }
 private Boolean isInBounds(int x,int y){
     return y < Level.getGridHeight() - 1 && x < Level.getGridWidth() - 1 &&
             y > 0 && x > 0;
 }
}




