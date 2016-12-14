package view.scenes.editor;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utilities.PropertiesUtilities;
import utilities.builder.ComponentProperties;
import utilities.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class provides the functionality for allowing a user to choose the
 *         initial grid size of the editor.
 */
class SizeChooserUI extends Scene {

    static final String SIZE_CHOOSER_RESOURCES = "resources/properties/size-chooser";
    private static final String CSS_FILE_NAME = "resources/styles/size-chooser.css";
    private static Parent myRoot;
    private static UIBuilder myBuilder;
    private static ResourceBundle myResources;
    private static PropertiesUtilities myUtil;
    private static EditorView myEditor;

    private String[] buttons = {"smallButton", "medButton", "largeButton", "customButton"};


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
        DimensionPrompt dimPrompt = new DimensionPrompt(myRoot, myResources);
        Dimension result = dimPrompt.promptForDimensions(myUtil.getIntProperty("maxDim"));
        if (result != null) {
            myEditor.launchEditor(result.width(), result.height());
        }
    }

    /**
     * Creates the buttons
     */
    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        for (String button : buttons) {
            int xPos = myUtil.getIntProperty(button + "X");
            int yPos = myUtil.getIntProperty(button + "Y");
            Node newButton = myBuilder.addNewImageView(myRoot, new ComponentProperties(xPos, yPos)
                    .path(myResources.getString(button + "Path"))
                    .width(myUtil.getIntProperty("buttonWidth"))
                    .id(buttonCSSid)
                    .preserveRatio(true));
            if (button.equals("customButton")) {
                newButton.setOnMouseClicked(e -> setCustomSize());
            } else {
                int size = myUtil.getIntProperty(button + "GridSize");
                newButton.setOnMouseClicked(e -> myEditor.launchEditor(size, size));
            }
        }
    }

    /**
     * Sets the text in the window
     */
    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        int xPos = myUtil.getIntProperty("promptXPos");
        int yPos = myUtil.getIntProperty("promptYPos");
        String text = myResources.getString("promptText");
        String font = myResources.getString("font");
        int size = myUtil.getIntProperty("promptSize");
        myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
                .text(text)
                .font(font)
                .size(size));
    }

    /**
     * Creates a window that prompts the user to choose an initial overworld size
     */
    private void init() {
        setButtons();
        setText();
    }
}