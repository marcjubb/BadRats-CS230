import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * An object that is visible to the user during runtime.
 * @author Michael Pokorski
 */

public abstract class VisibleObject {
    Image img = new Image("file:empty.png");
    boolean destroyed = false;
    int x = 0;
    int y = 0;

    public VisibleObject(int x, int y) {
    }

    protected VisibleObject() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void update() {

    }
    
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }

}
