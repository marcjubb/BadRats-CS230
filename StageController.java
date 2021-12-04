package controllers;

import constants.ErrorMessage;
import constants.Title;
import constants.Window;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controls all operations on the {@link Stage}
 *
 *
 */
public class StageController {

    private static Stage stage;
    private static Scene previousScene;
    private static String previousTitle;

    /**
     * Navigates to the previous scene
     */
    public static void previousScene() {
        setNewScene(previousScene, previousTitle);
    }

    /**
     * Sets the scene on the stage to the specified scene
     *
     * @param scene New scene to be shown
     * @param title Title of the window, as string
     * @throws IllegalStateException If the scene parameter is null
     */
    private static void setNewScene(Scene scene, String title) {
        if (scene != null) {
            previousScene = stage.getScene();
            previousTitle = stage.getTitle();
            stage.hide();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            throw new IllegalStateException("Cannot display null scene");
        }
    }

    /**
     * Displays a Conformation Dialog containing the passes strings
     *
     * @param msg     Message to be displayed
     * @param heading The Heading to be displayed
     * @param title   The title of the dialog box window
     */
    public static void showConfirmation(String msg, String heading,
                                        String title) {
        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.getDialogPane().getStylesheets().add("/resources/css/dialog.css");
        alert.showAndWait();
    }

    /**
     * Opens the main menu on startup
     *
     * @param primaryStage The main stage for this instance of the application
     */
    public static void start(Stage primaryStage) {
        stage = primaryStage;
        home();

    }

    /**
     * changes the scene to the main menu
     */
    public static void home() {
        stage.getIcons().add(new Image(StageController.class
                .getResourceAsStream(
                        "/resources/menu" +
                                "/Gun_Raccoon.png")));
        changeScene(Window.HOME);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * Changes the scene to a specified Window
     *
     * @param window {@link Window} to open
     */
    public static void changeScene(Window window) {
        Scene scene;
        try {
            Parent root = FXMLLoader
                    .load(StageController.class.getResource(window.getPath()));
            scene = new Scene(root);
            setNewScene(scene, window.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
            showError(ErrorMessage.FXML_NOT_FOUND, Title.ERROR, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an Error Dialog containing the passed strings
     *
     * @param error {@link ErrorMessage} template to use
     * @param quit  Whether to quit the application
     * @param title The title to use (member of {@link Title})
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void showError(ErrorMessage error, Title title, boolean quit) {
        Alert alert = new Alert(Alert.AlertType.ERROR, error.getMessage(),
                ButtonType.OK);
        alert.setTitle(title.toString());
        alert.setHeaderText(error.getHeader());
        alert.getDialogPane().getStylesheets().add("/resources/css/dialog.css");
        Optional<ButtonType> result = alert.showAndWait();
        if (quit && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public static void changeScene(Window window, Object[] args) {
        Scene scene;
        try {
            FXMLLoader loader = new FXMLLoader(
                    StageController.class.getResource(window.getPath()));
            Parent root = loader.load();
            InitialisableWithParameters controller = loader.getController();
            scene = new Scene(root);
            controller.initialiseWithParameters(args, scene, stage);
            setNewScene(scene, window.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
            showError(ErrorMessage.FXML_NOT_FOUND, Title.ERROR, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
