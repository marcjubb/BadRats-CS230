import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The Menu FXML controller.
 * @author Marc
 */
public class MenuController {

    @FXML
    private Button Leaderboard;
    @FXML
    private Text messageOfDay;

    /**
     * Load leaderboard.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void loadLeaderboard(ActionEvent event) throws IOException {
        Stage leaderBoard = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("leaderboard.fxml")));

        Scene leaderboardScene = new Scene(root);
        leaderBoard.setScene(leaderboardScene);
        leaderBoard.show();
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        messageOfDay.setText(MessageOfDay.getMessage());
    }


    /**
     * Lvl select
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void lvlSelect(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("levelSelect.fxml")));
        Scene menu = new Scene(root);
        stage.setScene(menu);
        stage.show();
    }

}