/**
 * This Class represents the deathRat as an Item. The deathRatItem
 * will then become a deathRat after a set time when placed.
 * @author ryanwake, samgriffin
 */
public class DeathRatItem extends Item {
    private final int CREATE_DEATHRAT_TICK = 5;
    private final int KILL_LIMIT = 5;
    private int ticksSinceCreation;
    private int currentKillCount;

    /**
     * Create DeathRat item at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public DeathRatItem(int x, int y) {
        super (x, y, "DeathRat", "/resources/Images/Rat/DeathRatR.png");
        ticksSinceCreation = 0;
        currentKillCount = 0;
    }

    /**
     * Destory DeathRatItem after timer is up and create new (playable)DeathRat which
     * will be created at same coordinates.
     */
    public void update() {
        ticksSinceCreation++;
        checkCollision();
        if (ticksSinceCreation == CREATE_DEATHRAT_TICK) {
            destroySelf();
            DeathRat rat = new DeathRat(x, y, currentKillCount);
            rat.setImageDirection();
            Level.getRatList().add(rat);
        }

    }

    /**
     * When DeathRatItem collides with a specified number of Rats, then destroy DeathRatItem.
     */
    private void checkCollision() {
        for (Rat rat : Level.getRatList()) {
            if (rat.getX() == x && rat.getY() == y) {
                rat.setDestroyed(true);
                currentKillCount++;
                if (currentKillCount >= KILL_LIMIT) {
                    destroySelf();
                }

            }
        }
    }

    /**
     * Get the number of ticks since creation.
     * @return The ticks since creation of DeathRatItem.
     */
    public int getTicksSinceCreation() {
        return ticksSinceCreation;
    }

    /**
     * Gets the data of the DeathRatItem.
     * @return formatted data of DeathRatItem.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + ticksSinceCreation + "\n";
    }

}
