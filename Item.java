import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This Class represents a typical item in the game.
 * @author ryanwake, Michael Pokorski
 */

public class Item extends VisibleObject {
    private String itemName;

    /**
     * Create an item with specified x and y coordinates, its Name and the image to represent the image.
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param itemName the name of the item.
     * @param fpImg the image to represent the image.
     */
    public Item(int x, int y, String itemName, String fpImg){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
        this.img = new Image(fpImg);
    }

    /**

     * Get the items name.
     * @return The itemname.

     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Check if a collision has occurred at specific coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if collision occurred else false.

     */
    public boolean collisionAt(int x, int y) {
        return x == this.x && y == this.y;
    }

    /**
     * Sets the item as destroyed
     */
    protected void destroySelf() {
        destroyed = true;
    }

    /**
     * Will check if an item is in an explosion of a Bomb.
     * @param items The list of items.
     * @return True if item is in the explosion else false.
     */
    protected boolean isInExplosion(ArrayList<Item> items) {
        for (Item item : items) {
            if (Objects.equals(item.getItemName(), "Bomb") && item.collisionAt(x, y)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Destroy an item if it's within the explosion of the Bomb.
     */
    public void update() {
        if (isInExplosion(Level.getItemList())) {
            destroySelf();
        }
    }

    /**
     * Sets Item Name.
     * @param itemName the new item name.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Format Item data.
     * @return The formatted data of item.
     */
    @Override
    public String toString(){
        return this.itemName + ", " + x + ", " + y;
    }
}
