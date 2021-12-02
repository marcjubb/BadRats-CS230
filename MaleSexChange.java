import javafx.scene.image.Image;

/**
 * Changes a rat into a male.
 * @author Michael Pokorski
 */

public class MaleSexChange extends Collectable{

    public MaleSexChange(int x, int y) {
        super(x, y, "MaleSexChange");
        img = new Image("/resources/Images/Items/MaleSexChange.png");
    }
}