import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
public class CreateClassController {

    @FXML
    private Button createAccountBtn;

    @FXML
    private TextField passwordTxtBox;

    @FXML
    private TextField usernameTxtBox;

    @FXML
    private Text emptyNotice;

    @FXML
    void createAccountPress(ActionEvent event) throws IOException {
        if (usernameTxtBox.getText().isEmpty()){
            emptyNotice.setOpacity(100);
        }else {
            PlayerProfiles.createProfile(usernameTxtBox.getText()) ;
            Level.closeCreateUser();

        }

    }

}
