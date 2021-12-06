import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents a visible object that can contain a coordinate pair (x,y).
 * @author Michael Pokorski
 */
public abstract class VisibleObject {

    final int tileSize = Level.getGridCellHeight();
    Image img;
    boolean destroyed = false;
    protected int x = 0;
    protected int y = 0;

    /**
     * Instantiates a new Visible object.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public VisibleObject(int x, int y) {

    }

    /**
     * Instantiates a new Visible object.
     */
    protected VisibleObject() {
    }

    /**
     * Gets the x component.
     * @return the x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y component.
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x component.
     * @param x THe new x coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y component.
     * @param y The new y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the image
     * @return The image.
     */
    public Image getImg() {
        return img;
    }

    /**
     * Sets the image.
     * @param img The new image.
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * Sets destroyed.
     * @param destroyed The new destroyed value.
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Update.
     */
    public void update() {

    }

    /**
     * Draw the visual object.
     * @param gc The GraphicsContext in which we will draw on.
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x * tileSize, y * tileSize);
    }

    /**
     * Checks visual Object is/is not destroyed.
     * @return True if destroyed, else false.
     */
    public boolean isDestroyed() {
        return destroyed;
    }

}
