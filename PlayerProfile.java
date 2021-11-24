/**
 * This Class represents a Players Profile.
 * @author ryanwake
 */
public class PlayerProfile {

    private String playerName;
    private int maxLevelCompleted;

    /**
     * Constructor when initializing a new User
     */
    public PlayerProfile(String playerName) {
        setPlayerName(playerName);
        maxLevelCompleted = 0;
    }
    /**
     * Constructor when importing an existing User
     */
    public PlayerProfile(String playerName, int maxLevelCompleted){
        setPlayerName(playerName);
        setMaxLevelCompleted(maxLevelCompleted);
    }

    public void incrementLevelCompleted(){
        maxLevelCompleted =+ 1;
    }

    public boolean equals(PlayerProfile p){
        return this.getPlayerName().equals(p.getPlayerName());
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMaxLevelCompleted(int maxLevelCompleted) {
        this.maxLevelCompleted = maxLevelCompleted;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMaxLevelCompleted() {
        return maxLevelCompleted;
    }

    @Override
    public String toString() {
        return playerName + ", " + String.valueOf(maxLevelCompleted);
    }
}
