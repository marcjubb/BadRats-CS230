import java.io.*;
import java.text.SimpleDateFormat;

/**
 *Saves all data to a text file that can be loaded
 *
 * @author Aaron Davies
 * @version 0.0.1
 */

public class Saver {
    /**
     *
     */
    // not sure what data actually needs to be saved or how to save it
    public static void save();

    /**
     *
     */

    public static void saveGame(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        File file = new File ("source/saves/" + dateFormat.format(date) + "_BadRats_Save");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);

        fileOutputStream.close();
        objectOutputStream.close();
    }

    /**
     *
     * @param filename
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NullPointerException
     */
    // Not needed........
    public static load(File filename)
        throws IOException, ClassNotFoundException, NullPointerException{
        if(!filename.exists()) {
            throw new NullPointerException(
                    "The file located at <" + filename + "> does not exist.");
        }else{
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        }
    }

    /**
     * Creates a constant global key for each value used to save the
     * correct data whatever that will be
     */
    public enum Key {

    }
}
