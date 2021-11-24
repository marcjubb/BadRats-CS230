import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * An object that is visible to the user during runtime.
 * @author Michael Pokorski
 */

public abstract class VisibleObject {
    Image img = new Image("file:empty.png");
    int x = 0;
    int y = 0;

    public void update() {

    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
