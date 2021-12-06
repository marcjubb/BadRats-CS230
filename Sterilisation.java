import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This Class represents the item, Sterilisation, in the game.
 * @author ryanwake
 */
public class Sterilisation extends Item {
    private final int SIZE_OF_EFFECT = 2;
    private final int STERILISATION_DURATION = 5;
    private final int XCOORD_LEFT = 1;
    private final int XCOORD_RIGHT = 2;
    private final int YCOORD_DOWN = 1;
    private final int YCOORD_UP = 2;
    private int timer;

    /**
     * Create Sterilisation at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Sterilisation(int x, int y){
        super (x, y, "Sterilisation", "/resources/Images/Items/Sterilisation.png");
        timer = STERILISATION_DURATION;
    }

    /**
     * Create Sterilisation at a specified coordinate and timer value.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param timer The timer value.
     */
    public Sterilisation(int x, int y, int timer){
        super(x, y, "Sterilisation", "/resources/Images/Items/Sterilisation.png");
        this.timer = timer;
    }

    /**
     * Create an area where if the rats enter then they become sterile
     */
    public void sterilizeArea(){
        for (int x = getX() - XCOORD_LEFT; x < getX() + XCOORD_RIGHT; x++) {
            for (int y = getY() - YCOORD_DOWN; y < getY() + YCOORD_UP; y++) {
                for (Rat rat : Level.getRatList()) {
                    if (rat.getX() == x&&rat.getY() == y && rat instanceof PlayableRat) {
                        ((PlayableRat) rat).setSterile(true);
                    }
                }
            }
        }
    }

    /**
     * Will update the timer every tick until timer runs out and sterilisation item will be destroyed.
     */
    public void update() {
        timer--;
        if (timer <= 0) {
            destroySelf();
        }
    }

    /**
     * Draw the item on the canvas.
     * @param gc The GraphicsContext in which we will draw on
     */
    public void draw(GraphicsContext gc) {
        for (int x = getX() -1 ; x < getX() + 2; x++) {
            for (int y = getY() - 1; y < getY() + 2; y++) {
                if (Level.getLevelLayout()[y][x] != 'G') {
                    gc.drawImage(new Image("/resources/Images/Items/Sterilisation.png"),
                            x*Level.getGridCellHeight(), y*Level.getGridCellHeight());
                }
            }
        }
        sterilizeArea();

    }

    /**
     * Get the data of the bomb.
     * @return the formatted Sterilisation data.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + timer;
    }
}

