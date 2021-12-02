import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This Class represents a typical item in the game.
 * @author ryanwake, Michael Pokorski
 */

public class Item extends VisibleObject{
    private String itemName;

    public Item(int x, int y, String itemName, String fpImg){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
        this.img = new Image(fpImg);
    }

    public String getItemName() {
        return itemName;
    }

    public boolean collisionAt(int x, int y) {
        if (x == this.x && y == this.y) {
            return true;
        }
        return false;
    }

    protected void destroySelf() {
        destroyed = true;
    }

    protected boolean isInExplosion(ArrayList<Item> items) {
        for (Item item : items) {
            if (item.getItemName() == "Bomb" && item.collisionAt(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        return this.itemName + ", " + ", " + x + ", " + y + "\n";
    }
}
