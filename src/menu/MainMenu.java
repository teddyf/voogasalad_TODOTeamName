package menu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg
 */
public class MainMenu extends Scene {

    private static final String MAINMENU_RESOURCES = "resources/mainmenu";
    private static final String CSS_FILE_NAME = "resources/UIStyle.css";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public MainMenu(Stage stage, UILauncher launcher, Parent root) {
        super(root);
        myStage = stage;
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myRoot = root;
        myResources = ResourceBundle.getBundle(MAINMENU_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
        initMenu();
    }

    private void initMenu() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();

        Node editorButton = myBuilder.addNewButton(myRoot, new ComponentProperties(500, 20).message("Editor"));
        editorButton.setOnMouseClicked(e -> myLauncher.launchEditor());

        Node gameButton = myBuilder.addNewButton(myRoot, new ComponentProperties(200, 20).message("Engine"));
        gameButton.setOnMouseClicked(e -> myLauncher.launchEngine());

        Node exitButton = myBuilder.addNewButton(myRoot, new ComponentProperties(100, 20).message("Exit"));
        exitButton.setOnMouseClicked(e -> myStage.hide());
    }
}
