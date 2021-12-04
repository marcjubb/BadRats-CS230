import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * An object that is visible to the user during runtime.
 *
 * @author Michael Pokorski
 */
public abstract class VisibleObject {
    /**
     * The Tile size.
     */
    final int tileSize = Level.getGridCellHeight();
    /**
     * The Img.
     */
    Image img;
    /**
     * The Destroyed.
     */
    boolean destroyed = false;
    /**
     * The X.
     */
    protected int x = 0;
    /**
     * The Y.
     */
    protected int y = 0;

    /**
     * Instantiates a new Visible object.
     *
     * @param x the x
     * @param y the y
     */
    public VisibleObject(int x, int y) {

    }

    /**
     * Instantiates a new Visible object.
     */
    protected VisibleObject() {
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * Sets img.
     *
     * @param img the img
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * Sets destroyed.
     *
     * @param destroyed the destroyed
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
     * Draw.
     *
     * @param gc the gc
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x * tileSize, y * tileSize);
    }

    /**
     * Is destroyed boolean.
     *
     * @return the boolean
     */
    public boolean isDestroyed() {
        return destroyed;
    }

}
