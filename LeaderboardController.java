import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/**
 * The type Leaderboard controller.
 * @author Marc
 */
public class LeaderboardController {
    private int levelCheck=0;
    @FXML
    private Text pos1;


    /**
     * Show level 1 ldr.
     *
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void showLevel1Ldr() throws FileNotFoundException {
        levelCheck=1;
      Level.loadLevel1HighScores();
        positionCalc();

    }

    /**
     * Show level 2 ldr.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void showLevel2Ldr(ActionEvent event) throws FileNotFoundException {
        levelCheck=2;
        Level.loadLevel2HighScores();
        positionCalc();
    }

    private void positionCalc() {
        int n=0;
        StringBuilder positions= new StringBuilder();
        ArrayList<String> top10 = new ArrayList<>();
        for (Object o : Level.getHighScore(levelCheck)) {
            Map.Entry mp = (Map.Entry) o;
            System.out.println(mp.getKey() + " By " + mp.getValue());
            top10.add(mp.getKey() + " By " + mp.getValue());
        }
        for (int i = top10.size()-1; i > 0; i--) {
            n++;
            positions.append("Position:").append(n).append(" ").append(top10.get(i)).append("\n");

        }
        pos1.setText(String.valueOf(positions));
    }

    /**
     * Show level 3 ldr.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void showLevel3Ldr(ActionEvent event) throws FileNotFoundException {
        levelCheck=3;
        Level.loadLevel3HighScores();
        positionCalc();
    }

    /**
     * Show level 4 ldr.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void showLevel4Ldr(ActionEvent event) throws FileNotFoundException {
        levelCheck=4;
        Level.loadLevel4HighScores();
        positionCalc();
    }

}
