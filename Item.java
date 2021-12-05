import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This Class represents a typical item in the game.
 *
 * @author ryanwake, Michael Pokorski
 */
public class Item extends VisibleObject{
    private String itemName;

    /**
     * Instantiates a new Item.
     *
     * @param x        the x
     * @param y        the y
     * @param itemName the item name
     * @param fpImg    the fp img
     */
    public Item(int x, int y, String itemName, String fpImg){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
        this.img = new Image(fpImg);
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Collision at boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean collisionAt(int x, int y) {
        return x == this.x && y == this.y;
    }

    /**
     * Destroy self.
     */
    protected void destroySelf() {
        destroyed = true;
    }

    /**
     * Is in explosion boolean.
     *
     * @param items the items
     * @return the boolean
     */
    protected boolean isInExplosion(ArrayList<Item> items) {
        for (Item item : items) {
            if (Objects.equals(item.getItemName(), "Bomb") && item.collisionAt(x, y)) {
                return true;
            }
        }

        return false;
    }
   /* protected boolean isInSterilisation(ArrayList<Item> items) {
        for (Item item : items) {
            if (Objects.equals(item.getItemName(), "Sterilisation") && item.collisionAt(x, y)) {
                return true;
            }
        }
        return false;
    }*/
    public void update() {
        if (isInExplosion(Level.getItemList())) {
            destroySelf();
        }
    }

    /**
     * Sets item name.
     *
     * @param itemName the item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        return this.itemName + ", " + x + ", " + y + "\n";
    }
}
