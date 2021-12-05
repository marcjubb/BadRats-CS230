/*
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

*/
/**
 * Class deals with manipulation of player profiles data (e.g. Save)
 * that exist in the game.
 * @author ryanwake
 * @author Aaron Davies
 * @version 2.0
 *//*



public class PlayerProfiles {
    private static final String PATH = "source/profiles.ser";
    private static ArrayList<PlayerProfile> profiles = new ArrayList<>();

    */
/**
     * Adds a profile to the list of profiles and then refreshes
     *
     * @param profile Profile you want to add
     *//*

    public static void add(PlayerProfile profile) {
        profiles.add(profile);
        refresh();
    }
    ///////////// Commented out javafx stuff for now not too sure what to do with it
    */
/*
    /**
     * Shows and controls the Create a Profile dialog.

    public static void showCreate() {
        refresh();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setGraphic(null);
        dialog.setHeaderText("Create Your Profile");
        dialog.setContentText(null);
        dialog.setTitle(Title.CREATE_PROFILE.toString());
        dialog.getEditor().setPromptText("Nickname");
        dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            PlayerProfile newProfile = new PlayerProfile(result.get());
            if (!(exists(newProfile))) {
                add(newProfile);
                StageController.showConfirmation("The profile " + newProfile.getName() +
                                " has successfully been created.", "Profile Creation Complete",
                        Title.CREATE_PROFILE.toString());
            } else {
                StageController
                        .showError(ErrorMessage.PROFILE_ADD_ERROR, Title.CREATE_PROFILE, false);
                showCreate();
            }
        } else {
            dialog.close();
        }
    }

    /**
     * Shows and controls the Delete a Profile dialog

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void showDelete() {
        refresh();
        ChoiceDialog dialog = new ChoiceDialog();
        dialog.setGraphic(null);
        dialog.setHeaderText("Delete a Profile");
        dialog.setContentText(null);
        dialog.setTitle(Title.DELETE_PROFILE.toString());
        dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
        dialog.getItems().addAll(profiles);
        Optional<PlayerProfile> result = dialog.showAndWait();
        if (result.isPresent()) {
            remove(result.get());
            StageController.showConfirmation("The profile " + result.get().getName() +
                            " has successfully been deleted.", "Delete Complete",
                    Title.DELETE_PROFILE.toString());
        } else {
            dialog.close();
        }
    }
    *//*

    */
/**
     * Calls save() and then load(), refreshing the available profiles
     *//*

    public static void refresh() {
        if (!profiles.isEmpty()) {
            save();
        }
        load();
    }

    */
/**
     * Removes a profile from the list of profiles then refreshes
     *
     * @param profile Profile you want to remove
     *//*

    public static void remove(PlayerProfile profile) {
        profiles.remove(profile);
        refresh();
    }

    */
/**
     * Loads all profiles from profiles.ser
     *//*

    @SuppressWarnings("unchecked")
    public static void load() {
        try {
            FileInputStream fileIn = new FileInputStream(PATH);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            profiles = (ArrayList<PlayerProfile>) objIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            */
/*StageController.showError(ErrorMessage.PROFILE_READ_ERROR, Title.ERROR, false);*//*

        }
    }

    */
/**
     * Saves all the profiles to profiles.ser
     *//*

    public static void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(PATH);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(profiles);
            fileOut.close();
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMessage.PROFILE_WRITE_ERROR, Title.ERROR, false);
        }
    }

    */
/**
     * Checks if a profile of the same name already exists in the list of profiles
     *
     * @param profile Profile you want to check
     * @return If it exists or not
     *//*

    public static boolean exists(PlayerProfile profile) {
        refresh();
        return profiles.stream().anyMatch(p -> p.equals(profile));
    }

    */
/**
     * Gets the list of profiles
     *
     * @return List of profiles
     *//*

    public static ArrayList<PlayerProfile> getProfiles() {
        refresh();
        return profiles;
    }



}
    */
/*
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
*//*


*/
