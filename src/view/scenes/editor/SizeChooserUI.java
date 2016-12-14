package view.scenes.editor;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utilities.PropertiesUtilities;
import utilities.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class provides the functionality for allowing a user to choose the
 *         initial model.grid size of the controller.editor.
 */
public class SizeChooserUI extends Scene {

    static final String SIZE_CHOOSER_RESOURCES = "resources/properties/size-chooser";
    private static final String CSS_FILE_NAME = "resources/styles/size-chooser.css";
    private static Parent myRoot;
    private static UIBuilder myBuilder;
    private static ResourceBundle myResources;
    private static PropertiesUtilities myUtil;
    private static EditorView myEditor;

    SizeChooserUI(EditorView editor, Parent root) {
        super(root, Color.web("#0585B2"));
        myRoot = root;
        myEditor = editor;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(SIZE_CHOOSER_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        root.getStylesheets().add(CSS_FILE_NAME);
        init();
    }

    /**
     * Prompts the user to choose a custom size for the initial overworld
     */
    private void setCustomSize() {
        DimensionPrompt dimPrompt = new DimensionPrompt(myRoot,myResources);
        Dimension result = dimPrompt.promptForDimensions(myUtil.getIntProperty("maxDim"));
        if (result != null) {
            myEditor.launchEditor(result.width(), result.height());
            System.out.println(result.width() + " is width " + result.height() + " is height");
        }
    }

    /**
     * Creates the buttons
     */
    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        // create small button
        int xPos = myUtil.getIntProperty("smallButtonX");
        int yPos = myUtil.getIntProperty( "smallButtonY");
        String path = myResources.getString("smallButtonPath");
        int width = myUtil.getIntProperty("buttonWidth");
        Node smallButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int smallSize = myUtil.getIntProperty("smallSize");
        smallButton.setOnMouseClicked(e -> myEditor.launchEditor(smallSize, smallSize));
//        smallButton.setOnMouseClicked(e -> setChosenDimension(smallSize,smallSize));
        // create medium button
        xPos = myUtil.getIntProperty("medButtonX");
        yPos = myUtil.getIntProperty( "medButtonY");
        path = myResources.getString("medButtonPath");
        Node medButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int medSize = myUtil.getIntProperty("medSize");
        medButton.setOnMouseClicked(e -> myEditor.launchEditor(medSize, medSize));
//        medButton.setOnMouseClicked(e -> setChosenDimension(medSize,medSize));
        // create large button
        xPos = myUtil.getIntProperty("largeButtonX");
        yPos = myUtil.getIntProperty( "largeButtonY");
        path = myResources.getString("largeButtonPath");
        Node largeButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int largeSize = myUtil.getIntProperty( "largeSize");
        largeButton.setOnMouseClicked(e -> myEditor.launchEditor(largeSize, largeSize));
//        largeButton.setOnMouseClicked(e -> setChosenDimension(largeSize,largeSize));
        // create custom button
        xPos = myUtil.getIntProperty("customButtonX");
        yPos = myUtil.getIntProperty( "customButtonY");
        path = myResources.getString("customButtonPath");
        Node customButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        customButton.setOnMouseClicked(e -> setCustomSize());
    }

    /**
     * Sets the text in the window
     */
    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        int xPos = myUtil.getIntProperty( "promptXPos");
        int yPos = myUtil.getIntProperty("promptYPos");
        String text = myResources.getString("promptText");
        String font = myResources.getString("font");
        int size = myUtil.getIntProperty("promptSize");
        Label s = (Label) myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, null, size);
    }

    /**
     * Creates a window that prompts the user to choose an initial overworld size
     */
    private void init() {
        setButtons();
        setText();
    }
}

