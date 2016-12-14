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

import java.util.ResourceBundle;


/**
 * @author Robert Steilberg, Harshil Garg
 *         <p>
 *         This class handles launching the main menu and transitioning into the
 *         game controller.engine or game controller.editor.
 *         <p>
 *         Dependencies: UILauncher, UIBuilder
 */
public class MainMenuView extends Scene {

    private static final String MAINMENU_RESOURCES = "resources/properties/main-menu";
    private static final String CSS_FILE_NAME = "resources/styles/main-menu.css";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PropertiesUtilities myUtil;

    private final String[] buttons = {"buildButton", "playButton", "exitButton"};
    private final String[] titles = {"title", "subtitle"};

    public MainMenuView(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myResources = ResourceBundle.getBundle(MAINMENU_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        root.getStylesheets().add(CSS_FILE_NAME);
        myStage.setOnCloseRequest(e -> myStage.hide());
        init();
    }

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        int width = myUtil.getIntProperty("buttonWidth");
        for (String button : buttons) {
            int xPos = myUtil.getIntProperty(button + "X");
            int yPos = myUtil.getIntProperty(button + "Y");
            String path = myResources.getString(button + "Path");
            Node newButton = myBuilder.addNewImageView(myRoot, new ComponentProperties(xPos, yPos)
                    .path(path)
                    .width(width)
                    .id(buttonCSSid)
                    .preserveRatio(true));
            switch (button) {
                case "buildButton":
                    newButton.setOnMouseClicked(e -> myLauncher.launchEditor());
                    break;
                case "playButton":
                    newButton.setOnMouseClicked(e -> myLauncher.launchEngine());
                    break;
                case "exitButton":
                    newButton.setOnMouseClicked(e -> myStage.hide());
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
            int xPos = myUtil.getIntProperty(title + "XPos");
            int yPos = myUtil.getIntProperty(title + "YPos");
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
        myBuilder.initWindow(myStage, MAINMENU_RESOURCES);
        setBackground();
        setText();
        setButtons();
    }
}
