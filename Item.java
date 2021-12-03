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
        return x == this.x && y == this.y;
    }

    protected void destroySelf() {
        destroyed = true;
    }

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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        return this.itemName + ", " + ", " + x + ", " + y + "\n";
    }
}
