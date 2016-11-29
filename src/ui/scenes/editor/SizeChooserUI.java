package ui.scenes.editor;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.properties.PropertiesUtilities;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class provides the functionality for allowing a user to choose the
 *         initial grid size of the editor.
 */
public class SizeChooserUI extends Scene {

    private static final String SIZE_CHOOSER_RESOURCES = "resources/properties/size-chooser";
    private static final String CSS_FILE_NAME = "resources/styles/size-chooser.css";
    private Stage myStage;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PropertiesUtilities myUtil;
    private GameEditor myEditor;

    SizeChooserUI(Stage stage, Parent root, GameEditor editor, UIBuilder builder) {
        super(root, Color.web("#0585B2"));
        myStage = stage;
        myRoot = root;
        myBuilder = builder;
        myResources = ResourceBundle.getBundle(SIZE_CHOOSER_RESOURCES);
        myUtil = new PropertiesUtilities();
        root.getStylesheets().add(CSS_FILE_NAME);
        myEditor = editor;
    }

    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        // create small button
        int xPos = myUtil.getIntProperty(myResources,"smallButtonX");
        int yPos = myUtil.getIntProperty(myResources,"smallButtonY");
        String path = myResources.getString("smallButtonPath");
        int width = myUtil.getIntProperty(myResources,"buttonWidth");
        Node smallButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        smallButton.setOnMouseClicked(e -> myEditor.launchEditor(myUtil.getIntProperty(myResources,"smallSize")));
        // create medium button
        xPos = myUtil.getIntProperty(myResources,"medButtonX");
        yPos = myUtil.getIntProperty(myResources,"medButtonY");
        path = myResources.getString("medButtonPath");
        Node medButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        medButton.setOnMouseClicked(e -> myEditor.launchEditor(myUtil.getIntProperty(myResources,"medSize")));
        // create large button
        xPos = myUtil.getIntProperty(myResources,"largeButtonX");
        yPos = myUtil.getIntProperty(myResources,"largeButtonY");
        path = myResources.getString("largeButtonPath");
        Node largeButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        largeButton.setOnMouseClicked(e -> myEditor.launchEditor(myUtil.getIntProperty(myResources,"largeSize")));
        // create custom button
        xPos = myUtil.getIntProperty(myResources,"customButtonX");
        yPos = myUtil.getIntProperty(myResources,"customButtonY");
        path = myResources.getString("customButtonPath");
        Node customButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
    }

    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        // create prompt
        int xPos = myUtil.getIntProperty(myResources,"promptXPos");
        int yPos = myUtil.getIntProperty(myResources,"promptYPos");
        String text = myResources.getString("promptText");
        String font = myResources.getString("font");
        int size = myUtil.getIntProperty(myResources,"promptSize");
        myBuilder.addCustomLabel(myRoot,text,xPos,yPos,font,size);

        // create small size text
        String CSSid = myResources.getString("dimCSSid");
        size = myUtil.getIntProperty(myResources,"dimTextSize");
        xPos = myUtil.getIntProperty(myResources,"smallTextX");
        yPos = myUtil.getIntProperty(myResources,"dimTextY");
        String dim = myResources.getString("smallSize");
        text = dim + "X" + dim;
        Node label = myBuilder.addCustomLabel(myRoot,text,xPos,yPos,font,size);
        label.setId(CSSid);

        // create medium size text
        xPos = myUtil.getIntProperty(myResources,"medTextX");
        dim = myResources.getString("medSize");
        text = dim + "X" + dim;
        label = myBuilder.addCustomLabel(myRoot,text,xPos,yPos,font,size);
        label.setId(CSSid);

        // create large size text
        xPos = myUtil.getIntProperty(myResources,"largeTextX");
        dim = myResources.getString("largeSize");
        text = dim + "X" + dim;
        label = myBuilder.addCustomLabel(myRoot,text,xPos,yPos,font,size);
        label.setId(CSSid);

    }

    public void promptUserForSize() {
        myBuilder.initWindow(myStage, SIZE_CHOOSER_RESOURCES);
        setButtons();
        setText();
        myStage.setScene(this);
    }
}

