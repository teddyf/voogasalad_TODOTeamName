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
 *         <p>
 *         This class handles launching the main menu and transitioning into the
 *         game engine or game editor.
 *         <p>
 *         Dependencies: UILauncher, UIBuilder
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

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void initButtons() {
        // create game editor button
        int xPos = Integer.parseInt(myResources.getString("editorButtonXPos"));
        int yPos = Integer.parseInt(myResources.getString("editorButtonYPos"));
        String buttonText = myResources.getString("editorButtonText");
        Node editorButton = myBuilder.addNewButton(myRoot, new ComponentProperties(xPos, yPos).message(buttonText));
        editorButton.setOnMouseClicked(e -> myLauncher.launchEditor());
        // create game engine button
        xPos = Integer.parseInt(myResources.getString("engineButtonXPos"));
        yPos = Integer.parseInt(myResources.getString("engineButtonYPos"));
        buttonText = myResources.getString("engineButtonText");
        Node engineButton = myBuilder.addNewButton(myRoot, new ComponentProperties(xPos, yPos).message(buttonText));
        engineButton.setOnMouseClicked(e -> myLauncher.launchEngine());
        // create exit button
        xPos = Integer.parseInt(myResources.getString("exitButtonXPos"));
        yPos = Integer.parseInt(myResources.getString("exitButtonYPos"));
        buttonText = myResources.getString("exitButtonText");
        Node exitButton = myBuilder.addNewButton(myRoot, new ComponentProperties(xPos, yPos).message(buttonText));
        exitButton.setOnMouseClicked(e -> myStage.hide());

    }

    /**
     * Initializes the main menu window
     */
    private void initMenu() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();
        initButtons();
    }
}
