/**
 * Opens the cswebcat site, decrpyts the message out puts the Message of the Day
 * @author Aaron Davies
 */



import java.util.Scanner;
import java.net.URL;

public class MessageOfDay {

    private static String message = null;

    /**
     * Retrieves the Messgae of Day
     * @return Message of the Day
     */

    public static String getMessage() {
        if (message == null) {
            generateMessage();
        }
        return message;
    }

    /**
     * Returns message of the day as string
     */
    private static void generateMessage() {
        //Not sure what the catch would be thats why i havent added it
        try {
            URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
            Scanner in = new Scanner(url.openStream());
            String temp = in.next();
            in.close();

            URL urli =
                    new URL("http://cswebcat.swansea.ac.uk/message?solution=") + text);
            Scanner inurl = new Scanner(urli.openStream());
            message = "";
            while (inurl.hasNext()){
                message += inurl.next() + " ";
            }
        }
    }

    /**
     * Solves the puzzle
     * @param text
     * @return Decrypted text
     */
    private static String solve(String text) {
        text = decrypt(text.toUpperCase());
        text = text + "CS-230";
        return  text;
    }

    /**
     * The start of the decryption task
     *
     * @param text
     * @return Decrypted text
     */

    private static String decrypt(String text) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }


}
