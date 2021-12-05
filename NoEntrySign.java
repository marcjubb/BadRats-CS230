import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Blocks rat movement until it takes enough damage and is destroyed.
 * @author Michael Pokorski
 */
public class NoEntrySign extends Item {
    private int durability;

    /**
     * Creates No entry sign at specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public NoEntrySign(int x, int y) {
        super(x, y, "NoEntrySign", "/resources/Images/Items/NoEntry.png");
        durability = 5;
    }

    /**
     * Creates No entry sign at specified coordinate with a particular durability.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param durability The durability value.
     */
    public NoEntrySign(int x, int y, int durability) {
        super(x, y, "NoEntrySign", "/resources/Images/Items/NoEntry.png");
        this.durability = durability;
    }

    /**
     * Will destroy the No Entry Sign after specified number of collisions
     */
    public void damage() {
        durability--;
        if (durability <= 0) {
            System.out.println("here");
            destroySelf();
        }
    }

    /**
     * Draw the No Entry Sign at each durability on the canvas.
     * @param gc The GraphicsContext in which we will draw on
     */
    public void draw(GraphicsContext gc) {
        if (durability == 5) {
            gc.drawImage(new Image("/resources/Images/Items/NoEntry.png"), x * tileSize, y * tileSize);
        }else if (durability == 4) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry4.png"), x * tileSize, y * tileSize);
       }else if (durability == 3) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry3.png"), x * tileSize, y * tileSize);
       }else if (durability == 2) {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry2.png"), x * tileSize, y * tileSize);
       }else {
           gc.drawImage(new Image("/resources/Images/Items/NoEntry1.png"), x * tileSize, y * tileSize);
       }
    }

    /**
     * Get the data of the sign.
     * @return the sign's data.
     */
    @Override
    public String toString(){
        return super.toString() + ", " + durability;
    }
}
