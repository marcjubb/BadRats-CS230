import javafx.scene.image.Image;

/**
 * Changes a rat into a male.
 * @author Michael Pokorski
 */

public class MaleSexChange extends Item{

    /**
     * Create MaleSexChange at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public MaleSexChange(int x, int y) {
        super(x, y, "MaleSexChange", "/resources/Images/Items/MaleSexChange.png");
    }
}
