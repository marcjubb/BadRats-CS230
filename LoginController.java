import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Login controller.
 */
public class LoginController {

    @FXML
    private Button createAccountBtn;

    @FXML
    private Button signInBtn;


    @FXML
    private TextField loginTxtBox;

    /**
     * Into create user.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void intoCreateUser(ActionEvent event) throws IOException {
        Level.createAccountWindow();
        Level.closeLogin();
    }

    /**
     * Login user.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void loginUser(ActionEvent event) throws IOException {
        PlayerProfiles.load();
        if (PlayerProfiles.exists(loginTxtBox.getText())) {
            Level.drawMenu();
            PlayerProfiles.setCurrentUserName(loginTxtBox.getText());
        }
    }

}
