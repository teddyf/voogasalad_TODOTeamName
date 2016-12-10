package ui.scenes;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.properties.PropertiesUtilities;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;


/**
 * @author Robert Steilberg, Harshil Garg
 *         <p>
 *         This class handles launching the main menu and transitioning into the
 *         game engine or game editor.
 *         <p>
 *         Dependencies: UILauncher, UIBuilder
 */
public class MainMenu extends Scene {

    private static final String MAINMENU_RESOURCES = "resources/properties/main-menu";
    private static final String CSS_FILE_NAME = "resources/styles/main-menu.css";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PropertiesUtilities myUtil;

    public MainMenu(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myResources = ResourceBundle.getBundle(MAINMENU_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        root.getStylesheets().add(CSS_FILE_NAME);
        myStage.setOnCloseRequest(e -> myStage.hide());
        initMenu();
    }

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void setButtons() {
        // create build button
        String buttonCSSid = myResources.getString("buttonCSSid");
        int xPos = myUtil.getIntProperty("buildButtonX");
        int yPos = myUtil.getIntProperty("buildButtonY");
        String path = myResources.getString("buildButtonPath");
        int width = myUtil.getIntProperty("buttonWidth");
        Node buildButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        buildButton.setOnMouseClicked(e -> myLauncher.launchEditor());
        
        // create play button
        xPos = myUtil.getIntProperty("playButtonX");
        yPos = myUtil.getIntProperty("playButtonY");
        path = myResources.getString("playButtonPath");
        Node playButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        playButton.setOnMouseClicked(e -> myLauncher.launchEngine());
        // create exit button
        xPos = myUtil.getIntProperty("exitButtonX");
        yPos = myUtil.getIntProperty("exitButtonY");
        path = myResources.getString("exitButtonPath");
        Node exitButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        exitButton.setOnMouseClicked(e -> myStage.hide());
    }

    /**
     * Sets the text of the main menu
     */
    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        // create title
        int xPos = myUtil.getIntProperty("titleXPos");
        int yPos = myUtil.getIntProperty("titleYPos");
        String text = myResources.getString("titleText");
        String font = myResources.getString("font");
        int size = myUtil.getIntProperty("titleSize");
        myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);
        // create subtitle
        xPos = myUtil.getIntProperty("subtitleXPos");
        yPos = myUtil.getIntProperty("subtitleYPos");
        text = myResources.getString("subtitleText");
        size = myUtil.getIntProperty("subtitleSize");
        myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);
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
    private void initMenu() {
        myBuilder.initWindow(myStage, MAINMENU_RESOURCES);
        setBackground();
        setText();
        setButtons();
    }
    
    public String getPath(){
        return MAINMENU_RESOURCES;
    }
}
