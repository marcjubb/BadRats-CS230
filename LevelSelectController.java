import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.util.ArrayList;

public class LevelSelectController {

    @FXML
    private Button autoBtn;

    @FXML
    private Button lvl1btn;

    @FXML
    private Button lvl2btn;

    @FXML
    private Button lvl3btn;

    @FXML
    private Button lvl4btn;

    @FXML
    private Button lvlSelectBtn3;

    @FXML
    void autoLoad(ActionEvent event) {

    }

    @FXML
    void loadLvl1(ActionEvent event) {
     /*   BorderPane root = new BorderPane();
        ArrayList<Rat> ratList = new ArrayList<Rat>();
         Character[][] levelLayout = {
                {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
                {'G', 'P', 'P', 'P', 'P', 'T', 'T', 'T', 'P', 'P', 'P', 'G'},
                {'G', 'P', 'G', 'G', 'P', 'G', 'G', 'G', 'P', 'G', 'P', 'G'},
                {'G', 'P', 'P', 'P', 'T', 'P', 'P', 'P', 'P', 'T', 'P', 'G'},
                {'G', 'P', 'G', 'G', 'T', 'G', 'P', 'G', 'P', 'G', 'P', 'G'},
                {'G', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'G'},
                {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};
    Level test = new Level(3,4,levelLayout,80, ratList);
        lvl1btn.getScene().setRoot(root);*/
    }

    @FXML
    void loadLvl2(ActionEvent event) {

    }

    @FXML
    void loadLvl3(ActionEvent event) {

    }

    @FXML
    void loadLvl4(ActionEvent event) {

    }

    @FXML
    void backToMenu(ActionEvent event) {

    }

}
