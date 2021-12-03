/**
 * An item that can be collected by a rat.
 * @author Michael Pokorski
 */

public abstract class Collectable extends Item {

    public Collectable(int x, int y, String itemName, String fpImg) {
        super(x, y, itemName, fpImg);
    }

    public void collect() {
        destroySelf();
    }
}
