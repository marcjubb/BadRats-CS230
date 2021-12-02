import javafx.scene.image.Image;

/**
 * Kills a rat
 * @author Marc Jubb
 */

public class Poison extends Collectable{

    public Poison(int x, int y) {
        super(x, y, "Poison");
        img = new Image("/resources/Images/Items/Poison.png");
    }
}