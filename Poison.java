import javafx.scene.image.Image;

/**
 * Kills a rat
 * @author Marc Jubb
 */


public class Poison extends Item {


    /**
     * Create Poison at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Poison(int x, int y) {
        super(x, y, "Poison", "/resources/Images/Items/Poison.png");

    }
}