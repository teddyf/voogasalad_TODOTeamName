package menu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
     * Adds a button to the main menu
     *
     * @param xPos  is the x position of the button
     * @param yPos  is the y position of the button
     * @param path  is the path to the button's image
     * @param width is the width of the button
     * @return the button
     */
    private Node addButton(int xPos, int yPos, String path, int width) {
        return myBuilder.addNewImageView(myRoot, new ComponentProperties(xPos, yPos)
                .path(path)
                .preserveRatio(true)
                .width(width));
    }

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void setButtons() {
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        // create build button
        int xPos = Integer.parseInt(myResources.getString("buildButtonX"));
        int yPos = Integer.parseInt(myResources.getString("buildButtonY"));
        String path = myResources.getString("buildButtonPath");
        int width = Integer.parseInt(myResources.getString("buttonWidth"));
        Node buildButton = addButton(xPos, yPos, path, width);
        buildButton.setOnMouseClicked(e -> myLauncher.launchEditor());
        buildButton.setOnMouseEntered(e -> buildButton.setEffect(hoverOpacity));
        buildButton.setOnMouseExited(e -> buildButton.setEffect(null));
        // create play button
        xPos = Integer.parseInt(myResources.getString("playButtonX"));
        yPos = Integer.parseInt(myResources.getString("playButtonY"));
        path = myResources.getString("playButtonPath");
        Node playButton = addButton(xPos, yPos, path, width);
        playButton.setOnMouseClicked(e -> myLauncher.launchEngine());
        playButton.setOnMouseEntered(e -> playButton.setEffect(hoverOpacity));
        playButton.setOnMouseExited(e -> playButton.setEffect(null));
        // create exit button
        xPos = Integer.parseInt(myResources.getString("exitButtonX"));
        yPos = Integer.parseInt(myResources.getString("exitButtonY"));
        path = myResources.getString("exitButtonPath");
        Node exitButton = addButton(xPos, yPos, path, width);
        exitButton.setOnMouseClicked(e -> myStage.hide());
        exitButton.setOnMouseEntered(e -> exitButton.setEffect(hoverOpacity));
        exitButton.setOnMouseExited(e -> exitButton.setEffect(null));
    }

    /**
     * Sets the text of the main menu
     */
    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        // create title
        int xPos = Integer.parseInt(myResources.getString("titleXPos"));
        int yPos = Integer.parseInt(myResources.getString("titleYPos"));
        String text = myResources.getString("titleText");
        String font = myResources.getString("font");
        int size = Integer.parseInt(myResources.getString("titleSize"));
        myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
                .text(text)
                .font(font)
                .size(size));
        // create subtitle
        xPos = Integer.parseInt(myResources.getString("subtitleXPos"));
        yPos = Integer.parseInt(myResources.getString("subtitleYPos"));
        text = myResources.getString("subtitleText");
        size = Integer.parseInt(myResources.getString("subtitleSize"));
        myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
                .text(text)
                .font(font)
                .size(size));
    }

    /**
     * Sets the background of the main menu
     */
    private void setBackground() {
        String path = myResources.getString("backgroundPath");
        int width = Integer.parseInt(myResources.getString("backgroundWidth"));
        myBuilder.addNewImageView(myRoot, new ComponentProperties(0, 0)
                .path(path)
                .preserveRatio(true)
                .width(width));
    }

    /**
     * Initializes the main menu window
     */
    private void initMenu() {
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();
        setBackground();
        setText();
        setButtons();
    }
}
