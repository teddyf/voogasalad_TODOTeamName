package view.scenes;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.PropertiesUtilities;
import view.UILauncher;
import utilities.builder.ComponentProperties;
import utilities.builder.UIBuilder;
import view.scenes.engine.EngineView;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles launching the win scene upon winning the game.
 */
public class WinSceneView extends Scene {

    private static final String WINSCENE_RESOURCES = "resources/properties/win-scene";
    private static final String CSS_FILE_NAME = "resources/styles/win-scene.css";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PropertiesUtilities myUtil;
    private EngineView myEngine;

    private final String[] buttons = {"playButton", "replayButton", "exitButton"};
    private final String[] titles = {"title", "subtitle"};

    public WinSceneView(Stage stage, Parent root, UILauncher launcher, EngineView engine) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myEngine = engine;
        myResources = ResourceBundle.getBundle(WINSCENE_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        root.getStylesheets().add(CSS_FILE_NAME);
        myStage.setOnCloseRequest(e -> {
            // go back to menu on close request
            e.consume();
            myLauncher.launchMenu();
        });
        init();
    }

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        int width = myUtil.getIntProperty("buttonWidth");
        for (String button : buttons) {
            int xPos = myUtil.getIntProperty(button + "XPos");
            int yPos = myUtil.getIntProperty(button + "YPos");
            String path = myResources.getString(button + "Path");
            Node newButton = myBuilder.addNewImageView(myRoot, new ComponentProperties(xPos, yPos)
                    .path(path)
                    .width(width)
                    .id(buttonCSSid)
                    .preserveRatio(true));
            switch (button) {
                case "playButton":
                    newButton.setOnMouseClicked(e -> myLauncher.launchEngine());
                    break;
                case "replayButton":
                    newButton.setOnMouseClicked(e -> {
                        myEngine.init(true);
                        myStage.setScene(myEngine);
                    });
                    break;
                case "exitButton":
                    newButton.setOnMouseClicked(e -> myLauncher.launchMenu());
                    break;
            }
        }
    }

    /**
     * Sets the text of the main menu
     */
    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        String font = myResources.getString("font");
        for (String title : titles) {
            int xPos = myUtil.getIntProperty(title + "X");
            int yPos = myUtil.getIntProperty(title + "Y");
            String text = myResources.getString(title + "Text");
            int size = myUtil.getIntProperty(title + "Size");
            myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
                    .text(text)
                    .font(font)
                    .size(size));
        }
    }

    /**
     * Sets the background of the main menu
     */
    private void setBackground() {
        String path = myResources.getString("backgroundPath");
        int width = myUtil.getIntProperty("backgroundWidth");
        myBuilder.addNewImageView(myRoot, new ComponentProperties(0, 0)
                .path(path)
                .preserveRatio(true)
                .width(width));
    }

    /**
     * Initializes the main menu window
     */
    private void init() {
        myBuilder.initWindow(myStage, WINSCENE_RESOURCES);
        setBackground();
        setText();
        setButtons();
    }
}
