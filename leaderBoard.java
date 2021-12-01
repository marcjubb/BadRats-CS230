import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/* Eventually will have GUI and a few more fixes
 Leader board that takes information from a file with all of the saved scores
 outputs top 5 high scores and the players
  @author Jacob K
*/


class leaderBoard {
    String name;
    int score;


    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the names
     */
    public String getName() {
        return name;
    }

    /**
     * @return the scores
     */
    public int getScore() {
        return score;
    }

    // to print player data by printing player reference object.
    @Override
    public String toString() {
        return "Player [name=" + name + ", score=" + score + "]";
    }

    // to sort players in descending order according to their scores
    static class sortPlayer implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            if (o1.score < o2.score) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}

class TopScorers {
    ArrayList<Player> list = new ArrayList<>();
    String fileName;

    public TopScorers(String fileName) {
        this.fileName = fileName;
    }

    // this method read data from file and then create player object and then store it to list.
    void readData() {
        File file = new File(fileName);
        Scanner console;
        try {
            // getting file
            console = new Scanner(file);
            // loop over file data
            while (console.hasNext()) {
                // getting file data line by line
                String name = console.next();
                int score = console.nextInt();
                // creating Player object
                Player player = new Player(name, score);
                // add object to list
                list.add(player);

            }

            // sort list in descending order by using our static sortPlayer() class.
            Collections.sort(list, new Player.sortPlayer());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    // retuns score of particular player, if name is not found then it returns -1.
    int playerScore(String name) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            Player player = list.get(i);
            if (player.getName().equals(name)) {
                return player.getScore();
            }
        }
        return -1;
    }

    String[] getTopNames() {
        int n = list.size();
        String[] ar = new String[n];
        for (int i = 0; i < n; i++) {
            Player player = list.get(i);
            ar[i] = player.getName();
        }
        return ar;
    }

    int[] getTopScores() {
        int n = list.size();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            Player player = list.get(i);
            ar[i] = player.getScore();
        }
        return ar;
    }

    Player[] getTopFive() {
        Player[] ar = new Player[5];
        for (int i = 0; i < 5; i++) {
            Player player = list.get(i);
            ar[i] = player;
        }
        return ar;
    }

    void topFiveToFile() {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int i = 0; i < 5; i++)
            {
                Player player = list.get(i);
                // writting data into newly created file.
                myWriter.write(player.getName() + "\n");
                myWriter.write(player.getScore() + "\n");
            }
            System.out.println("\n\nInformation of top five scorers to a file, Done!");
            // close writer object
            myWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // ask for the score file.
        System.out.print("Enter file name: ");
        String fileName = sc.next();
        // create TopScorers object using fileName.
        TopScorers ts = new TopScorers(fileName);
        ts.readData();

        Player[] players = ts.getTopFive();
        System.out.println("\n\nPrinting Top 5 Players in descending Order...");
        for (int i = 0; i < players.length; i++) {
            System.out.print(players[i] + " ");
        }

        ts.topFiveToFile();
    }

}
/* Should look like this
----All players --------
Shows all players

---Top Five Players-----
Name: Alex ,Score 3000
Name: Marc,Score 2800
Name: Sam,Score 2200
Name: Michael,Score 750
------------------------
 */
