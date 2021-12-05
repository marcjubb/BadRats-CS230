import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {

    @FXML
    private Button createAccountBtn;

    @FXML
    private Button signInBtn;

    @FXML
    void intoCreateUser(ActionEvent event) throws IOException {
        Level.createAccountWindow();
        Level.closeLogin();
    }

    @FXML
    void loginUser(ActionEvent event) throws IOException {


            Level.drawMenu();

    }

}
