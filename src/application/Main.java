package application;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UILauncher;

/**
 * Initializes the program and launches the main manu.
 *
 * Dependencies:
 */
public class Main extends Application {

    /**
     * Main call that launches the JavaFX program.
     *
     * @param args are the arguments passed from the command line
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launches the VoogaSalad menu.
     *
     * @param stage is the current JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        UILauncher GUI = new UILauncher(stage);
        GUI.init();
    }
}