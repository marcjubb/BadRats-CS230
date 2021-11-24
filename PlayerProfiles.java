import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class deals with manipulation of player profiles data (e.g. Save)
 * that exist in the game.
 * @author ryanwake
 */


public class PlayerProfiles {

    private static ArrayList<PlayerProfile> profiles = new ArrayList<PlayerProfile>();
    private static String profilePath = "resources/playerprofiles.txt";

    public static void add(PlayerProfile p){
        profiles.add(p);
    }

    public static void remove(PlayerProfile p){
        profiles.remove(p);
    }

    private static boolean exists(String username){
        for(PlayerProfile p : PlayerProfiles.profiles){
            if(p.getPlayerName().equals(username)){
                return true;
            }
        }
        return false;
    }


    public static PlayerProfile createProfile(String username) throws UsernameAlreadyExistsException {
        if(PlayerProfiles.exists(username)) {
           throw new UsernameAlreadyExistsException(username);
        } else {
            PlayerProfile p = new PlayerProfile(username);
            PlayerProfiles.profiles.add(p);
            PlayerProfiles.save();
            return p;
        }
    }


    public static ArrayList<PlayerProfile> load() throws FileNotFoundException {
        File inputFile = new File(profilePath);
        Scanner in = null;

        try{
            in = new Scanner(inputFile);
        } catch(FileNotFoundException e){
            System.out.println("Error reading from file at " + profilePath);
        }
        return PlayerProfiles.load(in);

    }

    public static ArrayList<PlayerProfile> load(Scanner in) {
        profiles = null;

        while(in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner newLine = new Scanner (currentLine);
            profiles.add(readProfile(newLine));
        }
        return profiles;
    }

    public static PlayerProfile readProfile(Scanner in){
        PlayerProfile newProfile = null;
        in.useDelimiter(",");

        try{
            String username = in.next();
            int maxLevelCompleted = in.nextInt();
            newProfile = new PlayerProfile(username, maxLevelCompleted);

        }catch(Exception e) {
            System.out.println("Unable to Read file");
        }
        in.close();
        return newProfile;
    }


    public static void save() {
        File file = new File(profilePath);
        PrintWriter writer = null;

        try{
            writer = new PrintWriter(file);
            for(PlayerProfile p : PlayerProfiles.profiles){
                writer.println(p.toString());
            }
        }catch(Exception e){
            System.out.println("Couldn't save profile at " + profilePath);
        }
        writer.close();
    }
}


