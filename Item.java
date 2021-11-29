import javafx.scene.image.Image;

/**
 * This Class represents a typical item in the game.
 * @author ryanwake
 */
public class Item extends VisibleObject{

    private String itemName;
    private String imagePath;
    private Image itemImage;
    private boolean isDestroyed;

    public Item(int x, int y){
        super(x, y);
        isDestroyed = false;
    }

    public Item(String itemName, String imagePath,int x, int y) {
        super(x, y);
        this.setItemName(itemName);
        this.setImagePath(imagePath);
        this.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        this.isDestroyed = false;

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

    public void setImage(Image itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        return this.itemName + ", " + this.imagePath + ", " + x + ", " + y + this.isDestroyed;
    }

}
