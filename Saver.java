import java.io.*;
import java.io.File;

/**
 *Saves all data to a text file that can be loaded
 * @author Aaron Davies, Gonzalo Mandrion
 * @version 2.0.0
 */

public class Saver {
   
    /**
     *Saves the data to the text file#
     */
    public void saveLevelFile(String hw, int sec, int ratsPopulation, String rats, String currentItems, String layout) {
        try {
            File output = new File("resources/LevelFiles/saveLevel.txt");
            if(output.exists())
                 output = new File("resources/LevelFiles/saveLevel"+ java.time.LocalDate.now() +".txt");

            FileWriter writer = new FileWriter(output);

            writer.write(hw);
            writer.write(sec);
            writer.write(ratsPopulation);
            writer.write(rats);
            writer.write(currentItems);
            writer.write(layout);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
