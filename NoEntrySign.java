import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Blocks rat movement until it takes enough damage and is destroyed.
 * @author Michael Pokorski
 */
public class NoEntrySign extends Item {
    private final int NO_ENTRY_SIGN_HEALTH = 5;
    private final int DESTROY_SIGN = 0;
    private final int SIGN_DURABILITY_FIVE = 5;
    private final int SIGN_DURABILITY_FOUR = 4;
    private final int SIGN_DURABILITY_THREE = 3;
    private final int SIGN_DURABILITY_TWO = 2;
    private int durability;

    /**
     * Creates No entry sign at specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public NoEntrySign(int x, int y) {
        super (x, y, "NoEntrySign", "/resources/Images/Items/NoEntry.png");
        durability = NO_ENTRY_SIGN_HEALTH;
    }

    /**
     * Creates No entry sign at specified coordinate with a particular durability.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param durability The durability value.
     */
    public NoEntrySign(int x, int y, int durability) {
        super (x, y, "NoEntrySign", "/resources/Images/Items/NoEntry.png");
        this.durability = durability;
    }

    /**
     * Will destroy the No Entry Sign after specified number of collisions
     */
    public void damage() {
        durability--;
        if (durability <= DESTROY_SIGN) {
            destroySelf();
        }
    }

    /**
     * Draw the No Entry Sign at each durability on the canvas.
     * @param gc The GraphicsContext in which we will draw on
     */
    public void draw(GraphicsContext gc) {
        if (durability == SIGN_DURABILITY_FIVE) {
            gc.drawImage(new Image("/resources/Images/Items/NoEntry.png"),
                    x * tileSize, y * tileSize);
        } else if (durability == SIGN_DURABILITY_FOUR) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry4.png"),
                   x * tileSize, y * tileSize);
        } else if (durability == SIGN_DURABILITY_THREE) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry3.png"),
                   x * tileSize, y * tileSize);
        } else if (durability == SIGN_DURABILITY_TWO) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry2.png"),
                   x * tileSize, y * tileSize);
        } else {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry1.png"),
                   x * tileSize, y * tileSize);
        }
    }

    /**
     * Get the data of the sign.
     * @return the sign's data.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + durability;
    }

}
