package ui.scenes.editor;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import resources.properties.PropertiesUtilities;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.Optional;
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

    private boolean invalidValue(TextField width, TextField height) {
        try {
            int widthVal = Integer.parseInt(width.getText());
            int heightVal = Integer.parseInt(height.getText());
            return width.getText().trim().isEmpty() || height.getText().trim().isEmpty() || widthVal > 100 || heightVal > 100;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private void setCustomSize() {
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setHeaderText("Please specify a custom width and height less than 100.");

        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField width = new TextField();
        width.setPromptText("width");
        TextField height = new TextField();
        height.setPromptText("height");

        grid.add(new Label("Width:"), 0, 0);
        grid.add(width, 1, 0);
        grid.add(new Label("Height:"), 0, 1);
        grid.add(height, 1, 1);

        Node submitButton = dialog.getDialogPane().lookupButton(submitButtonType);
        submitButton.setDisable(true);

        width.textProperty().addListener(e -> submitButton.setDisable(invalidValue(width, height)));
        height.textProperty().addListener(e -> submitButton.setDisable(invalidValue(width, height)));
        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();
        int widthVal = Integer.parseInt(width.getText());
        int heightVal = Integer.parseInt(height.getText());
        myEditor.launchEditor(widthVal, heightVal);


    }

    private void setButtons() {
        String buttonCSSid = myResources.getString("buttonCSSid");
        // create small button
        int xPos = myUtil.getIntProperty(myResources, "smallButtonX");
        int yPos = myUtil.getIntProperty(myResources, "smallButtonY");
        String path = myResources.getString("smallButtonPath");
        int width = myUtil.getIntProperty(myResources, "buttonWidth");
        Node smallButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int smallSize = myUtil.getIntProperty(myResources, "smallSize");
        smallButton.setOnMouseClicked(e -> myEditor.launchEditor(smallSize, smallSize));
        // create medium button
        xPos = myUtil.getIntProperty(myResources, "medButtonX");
        yPos = myUtil.getIntProperty(myResources, "medButtonY");
        path = myResources.getString("medButtonPath");
        Node medButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int medSize = myUtil.getIntProperty(myResources, "smallSize");
        medButton.setOnMouseClicked(e -> myEditor.launchEditor(medSize, medSize));
        // create large button
        xPos = myUtil.getIntProperty(myResources, "largeButtonX");
        yPos = myUtil.getIntProperty(myResources, "largeButtonY");
        path = myResources.getString("largeButtonPath");
        Node largeButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        int largeSize = myUtil.getIntProperty(myResources, "smallSize");
        largeButton.setOnMouseClicked(e -> myEditor.launchEditor(largeSize, largeSize));
        // create custom button
        xPos = myUtil.getIntProperty(myResources, "customButtonX");
        yPos = myUtil.getIntProperty(myResources, "customButtonY");
        path = myResources.getString("customButtonPath");
        Node customButton = myBuilder.addCustomImageView(myRoot, xPos, yPos, path, width, buttonCSSid);
        customButton.setOnMouseClicked(e -> setCustomSize());
    }

    private void setText() {
        Font.loadFont(myResources.getString("externalFont"), 12);
        // create prompt
        int xPos = myUtil.getIntProperty(myResources, "promptXPos");
        int yPos = myUtil.getIntProperty(myResources, "promptYPos");
        String text = myResources.getString("promptText");
        String font = myResources.getString("font");
        int size = myUtil.getIntProperty(myResources, "promptSize");
        myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);

        // create small size text
        String CSSid = myResources.getString("dimCSSid");
        size = myUtil.getIntProperty(myResources, "dimTextSize");
        xPos = myUtil.getIntProperty(myResources, "smallTextX");
        yPos = myUtil.getIntProperty(myResources, "dimTextY");
        String dim = myResources.getString("smallSize");
        text = dim + "X" + dim;
        Node label = myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);
        label.setId(CSSid);

        // create medium size text
        xPos = myUtil.getIntProperty(myResources, "medTextX");
        dim = myResources.getString("medSize");
        text = dim + "X" + dim;
        label = myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);
        label.setId(CSSid);

        // create large size text
        xPos = myUtil.getIntProperty(myResources, "largeTextX");
        dim = myResources.getString("largeSize");
        text = dim + "X" + dim;
        label = myBuilder.addCustomLabel(myRoot, text, xPos, yPos, font, size);
        label.setId(CSSid);

    }

    public void promptUserForSize() {
        myBuilder.initWindow(myStage, SIZE_CHOOSER_RESOURCES);
        setButtons();
        setText();
        myStage.setScene(this);
    }
}

