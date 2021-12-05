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

public class MenuController {

        @FXML
        private Button Leaderboard;

        @FXML
        private Button Load;

        @FXML
        private Button lvlSelectBtn;

        @FXML
        void gotoLoad(ActionEvent event) {

        }

        @FXML
        void loadLeaderboard(ActionEvent event) {

        }
    @FXML
    private Text messageOfDay;
    @FXML
    public void initialize(){
        messageOfDay.setText(MessageOfDay.getMessage());
    }


    @FXML
    void lvlSelect(ActionEvent event) throws IOException {


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("levelSelect.fxml")));

        Scene menu = new Scene(root);
        stage.setScene(menu);
        stage.show();
    }

}
