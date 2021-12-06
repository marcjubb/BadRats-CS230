import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeaderboardController {

    @FXML
    private Button showLevel1btn;

    @FXML
    private Button showLevel2btn;

    @FXML
    private Button showLevel3btn;

    @FXML
    private Button showLevel4btn;

    @FXML
    void showLevel1Ldr(ActionEvent event) throws FileNotFoundException {
      Level.loadLevel1HighScores();



    }

    @FXML
    void existingSave(ActionEvent event) {

    }
    @FXML
    void showLevel2Ldr(ActionEvent event) {

    }

    @FXML
    void showLevel3Ldr(ActionEvent event) {

    }

    @FXML
    void showLevel4Ldr(ActionEvent event) {

    }

}
