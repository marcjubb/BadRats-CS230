/**
 * An item that can be collected by a rat.
 * @author Michael Pokorski
 */

public abstract class Collectable extends Item {

    public void collect() {
        destroySelf();
    }
}
