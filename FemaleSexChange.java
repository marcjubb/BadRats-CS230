import javafx.scene.image.Image;

/**
 * Changes a rat into a female.
 * @author Michael Pokorski
 */

public class FemaleSexChange extends Collectable{

    public FemaleSexChange(int x, int y) {
        super(x, y, "femalesexchange");
        img = new Image("/resources/Images/Items/FemaleSexChange.png");
    }
}

