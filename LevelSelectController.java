import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

/**
 * The type Level select controller.
 */
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


    /**
     * Auto load.
     *
     * @param event the event
     */
    @FXML
    void autoLoad(ActionEvent event) {

    }

    /**
     * Load user unlocked.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void loadUserUnlocked(ActionEvent event) throws FileNotFoundException {
        PlayerProfiles.load();
        System.out.println(PlayerProfiles.getCurrentHighestLevel());
        if (PlayerProfiles.getCurrentHighestLevel() == 2){
            this.lvl2btn.setOpacity(100);
            this.lvl3btn.setDisable(true);
            this.lvl4btn.setDisable(true);

        }else if(PlayerProfiles.getCurrentHighestLevel() == 3){
            this.lvl2btn.setOpacity(100);
            this.lvl3btn.setOpacity(100);
            this.lvl4btn.setDisable(true);

        }else if(PlayerProfiles.getCurrentHighestLevel() == 4){
            this.lvl2btn.setOpacity(100);
            this.lvl3btn.setOpacity(100);
            this.lvl4btn.setOpacity(100);

        }else{
            this.lvl2btn.setDisable(true);
            this.lvl3btn.setDisable(true);
            this.lvl4btn.setDisable(true);
        }
    }

    /**
     * Load lvl 1.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
     void loadLvl1(ActionEvent event) throws FileNotFoundException {
        Level.loadLevel1();
    }

    /**
     * Load lvl 2.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void loadLvl2(ActionEvent event) throws FileNotFoundException{
        Level.loadLevel2();
    }

    /**
     * Load lvl 3.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void loadLvl3(ActionEvent event) throws FileNotFoundException{
        Level.loadLevel3();
    }

    /**
     * Load lvl 4.
     *
     * @param event the event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    void loadLvl4(ActionEvent event) throws FileNotFoundException{
        Level.loadLevel4();
    }

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML
    void backToMenu(ActionEvent event) {

    }

    /**
     * Existing save.
     *
     * @param actionEvent the action event
     */
    public void existingSave(ActionEvent actionEvent) {
        Level.loadExisting();
    }
}
