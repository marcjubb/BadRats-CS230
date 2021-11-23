import javafx.scene.image.Image;

/**
 * This Class represents a typical item in the game.
 * @author ryanwake
 */
public class Item {

    private String itemName;

    private String imagePath;

    private Image itemImage;

    private boolean isDestroyed = false;

    public Item(String itemName, String imagePath) {
        this.setItemName(itemName);
        this.setImagePath(imagePath);
        this.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public Image getImage() {
        return itemImage;
    }

    public void destroySelf(boolean destroyed) {
        //TODO
    }

    public void setImagePath(String itemImage) {
        this.imagePath = itemImage;
    }

    public void setImage(Image image) {
        this.itemImage = image;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        return this.getItemName() + ", " + this.getImagePath();
    }

}
