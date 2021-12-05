/**
 * This Class represents a player profile.
 * @author ryanwake
 * @version 1.0
 */
public class PlayerProfile {

    private String userName;
    private int maxLevelCompleted;

    /**
     * Create player profile with a username
     * @param userName The username of the player.
     */
    public PlayerProfile(String userName) {
        setUserName(userName);
        maxLevelCompleted = 0;
    }

    /**
     * Load existing player profile
     * @param userName The username of the player.
     * @param maxLevelCompleted The max Level achieved.
     */
    public PlayerProfile(String userName, int maxLevelCompleted) {
        setUserName(userName);
        setMaxLevelCompleted(maxLevelCompleted);
    }

    /**
     * Increments max level achieved.
     */
    public void incrementLevelCompleted() {
        maxLevelCompleted =+ 1;
    }

    /**
     * Checks if player Username already exists.
     * @param p the players profile
     * @return True if profile already exists, else false.
     */
    public boolean equals(PlayerProfile p) {
        return this.getUserName().equals(p.getUserName());
    }

    /**
     * Sets Username of player
     * @param userName The new username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets max level completed
     * @param maxLevelCompleted The new max level achieved.
     */
    public void setMaxLevelCompleted(int maxLevelCompleted) {
        this.maxLevelCompleted = maxLevelCompleted;
    }

    /**
     * Get the Username of player.
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the max Level achieved.
     * @return The max level achieved.
     */
    public int getMaxLevelCompleted() {
        return maxLevelCompleted;
    }

    /**
     * Formats players data
     * @return formatted player data.
     */
    @Override
    public String toString() {
        return userName + ", " + String.valueOf(maxLevelCompleted) + "\n";
    }
}
