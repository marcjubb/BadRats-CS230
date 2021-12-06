import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Class deals with manipulation of player profiles data (e.g. Save)
 * that exist in the game.
 *
 * @author ryanwake
 * @author Marc Jubb
 * @version 2.0
 */


public class PlayerProfiles {
    private static final String PROFILE_PATH = "resources/playerprofiles.txt";
    private static ArrayList<PlayerProfile> profiles = new ArrayList<>();
    private static int currentUserLevel;


    public static void setCurrentUserLevel(int currentUserLevel) {
        PlayerProfiles.currentUserLevel = currentUserLevel;
    }

    public static int getCurrentUserLevel() {
        return currentUserLevel;
    }


    /**
     * Adds players profile to List.
     * @param p The profile
     */
    public static void add(PlayerProfile p) {
        profiles.add(p);
    }

    /**
     * Removes players profile from List.
     * @param p The profile.
     */
    public static void remove(PlayerProfile p) {
        profiles.remove(p);
    }

    /**
     * Check if a player profile exists in List.
     * @param username The username being searched.
     * @return True if profile exists, else False.
     */


    public static boolean exists(String username) {
        for (PlayerProfile p : PlayerProfiles.profiles) {
            if (p.getUserName().equals(username)) {
                currentUserLevel = p.getMaxLevelCompleted();
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new player profile.
     * @param username The username of the new profile.
     * @throws UsernameAlreadyExistsException If username exists then throw UsernameAlreadyExistsException exception.
     * @throws IOException when file cant be read, throw IOException.
     */
    public static void createProfile(String username) throws UsernameAlreadyExistsException, IOException {
        if (PlayerProfiles.exists(username)) {
            throw new UsernameAlreadyExistsException(username);
        } else {
            PlayerProfile p = new PlayerProfile(username);
            PlayerProfiles.profiles.add(p);
            PlayerProfiles.save();
        }
    }

    /**
     * Load existing profiles into the List.
     * @return List of existing profiles.
     * @throws FileNotFoundException if file not found then throw FileNotFoundException exception.
     */
    public static void load() throws FileNotFoundException {
        File inputFile = new File(PROFILE_PATH);
        Scanner in = null;
        try {
            in = new Scanner(inputFile);

        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file at " + PROFILE_PATH);
        }
        load(in);
    }

    /**
     * Read all profiles in File into the List.
     * @param in read in each token.
     * @return List of profiles.
     */
    public static void load(Scanner in) {
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner lineReader = new Scanner(currentLine);
            profiles.add(readProfile(lineReader));

        }
    }

    /**
     * Read players profile.
     * @param in reads in each token.
     * @return a players profile.
     */
    public static PlayerProfile readProfile(Scanner in) {
        PlayerProfile newProfile = null;

        try {
            String username = in.next();
            int maxLevelCompleted = in.nextInt();
            newProfile = new PlayerProfile(username, maxLevelCompleted);

        } catch (Exception e) {
            System.out.println("Unable to Read file");
        }
        in.close();
        return newProfile;
    }

    /**
     * Saves players profiles list to a file.
     */
    public static void save() throws IOException {
        File file = new File(PROFILE_PATH);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            for (PlayerProfile p : PlayerProfiles.profiles) {
                writer.write(p.toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Couldn't save profile at " + PROFILE_PATH);
        }
        assert writer != null;
        writer.close();
    }

}