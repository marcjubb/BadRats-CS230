/**
 * Opens the cswebcat site, decrpyts the message out puts the Message of the Day
 * @author Aaron Davies and Marc Jubb
 * @version 0.0.1
 */



import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;

public class MessageOfDay {
    public static void main(String[] args) {
        generateMessage();
    }

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

            URL urli = new URL("http://cswebcat.swansea.ac.uk/message?solution=" + solve(temp));
            Scanner inurl = new Scanner(urli.openStream());
            message = "";
            while (inurl.hasNext()){
                message += inurl.next() + " ";
            }
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
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
        text = text.length() + text;
        return  text;
    }

    /**
     * The start of the decryption task
     *
     * @param text
     * @return Decrypted text
     */



   // }
    private static String decrypt(String text){
        StringBuilder s = new StringBuilder();
        int len = text.length();
       for (int n = 0; n < len; n++){
           s.append(shiftChar(text.charAt(n), n));
       }
        return s.toString();
    }

    private static char shiftChar(char charToShift, int shiftN){
        char temp;
        if (shiftN % 2 == 1 ){
            temp = (char)(charToShift + (shiftN+1));
            if (temp > 'Z') {
                charToShift = (char) (charToShift - (26 - (shiftN + 1)));
            }else{
                return temp;
            }
        }else {
            temp = (char)(charToShift - (shiftN+1));
            if (temp < 'A') {
                charToShift = (char) (charToShift + (26 - shiftN - 1));
            }else{
                return temp;
            }
        }
        return charToShift;
    }

}
