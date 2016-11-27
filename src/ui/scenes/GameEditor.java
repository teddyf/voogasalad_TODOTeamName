package ui.scenes;

import grid.*;
import editor.Screen;
import editor.SidePanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import java.util.ResourceBundle;


/**
 * @author Robert Steilberg
 *
 *         This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/properties/gameeditor";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private Group mySideMenuRegion;
    private ResourceBundle myResources;
    private GridPane gridPane;
    private SidePanel sideMenu;

    public GameEditor (Stage stage, UILauncher launcher, Parent root) {
        super(root);
        myStage = stage;
        myLauncher = launcher;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        gridPane =
                new GridPane(Integer.parseInt(myResources.getString("gridCellsWide")),
                             Integer.parseInt(myResources.getString("gridCellsHeight")),
                             Integer.parseInt(myResources.getString("gridWidth")),
                             Integer.parseInt(myResources.getString("gridHeight")),
                             Integer.parseInt(myResources.getString("gridX")),
                             Integer.parseInt(myResources.getString("gridY")));
        initEditor();
        initRegions();
        initSideMenu();
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
    }
    /**
     * Initializes the game editor window
     */
    private void initEditor () {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        
        myBuilder.addComponent(myRoot,
                               new Screen(Integer.parseInt(myResources.getString("screenWidth")),
                                          Integer.parseInt(myResources.getString("screenHeight")))
                                                  .getRoot());
                                                  
        setGridControl();
    }

    private void initRegions () {
        mySideMenuRegion = createRegion(800, 0);
        myBuilder.addComponent(myRoot, mySideMenuRegion);
    }

    private Group createRegion (int layoutX, int layoutY) {
        Group region = new Group();
        region.setLayoutX(layoutX);
        region.setLayoutY(layoutY);
        return region;
    }

    private void initSideMenu () {
        sideMenu = new SidePanel(mySideMenuRegion);
    }

    /**
     * Sets Grid Control
     */
    private void setGridControl () {
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        int updateX = Integer.parseInt(myResources.getString("updateX"));
        int updateY = Integer.parseInt(myResources.getString("updateY"));
        int updateWidth = Integer.parseInt(myResources.getString("updateWidth"));
        int widthInputX = Integer.parseInt(myResources.getString("inputWidthX"));
        int widthInputY = Integer.parseInt(myResources.getString("inputWidthY"));
        int widthInputWidth = Integer.parseInt(myResources.getString("inputWidthWidth"));
        String widthInputText = myResources.getString("inputWidthText");
        int heightInputX = Integer.parseInt(myResources.getString("inputHeightX"));
        int heightInputY = Integer.parseInt(myResources.getString("inputHeightY"));
        int heightInputWidth = Integer.parseInt(myResources.getString("inputHeightWidth"));
        String heightInputText = myResources.getString("inputHeightText");
        int swapX = Integer.parseInt(myResources.getString("swapX"));
        int swapY = Integer.parseInt(myResources.getString("swapY"));
        int swapWidth = Integer.parseInt(myResources.getString("swapWidth"));
        String swapPath = myResources.getString("swapPath");
        Node widthInputField =
                myBuilder.addCustomTextField(myRoot, widthInputText, widthInputX, widthInputY,
                                             widthInputWidth);
        Node heightInputField =
                myBuilder.addCustomTextField(myRoot, heightInputText, heightInputX, heightInputY,
                                             heightInputWidth);
        String updatePath = myResources.getString("updatePath");
        Node updateButton =
                myBuilder.addCustomButton(myRoot, updatePath, updateX, updateY, updateWidth);
        updateButton.setOnMouseClicked(e -> {
            myBuilder.removeComponent(myRoot, gridPane.getGroup());
            TextField xText = (TextField) widthInputField;
            TextField yText = (TextField) heightInputField;
            int xInput = Integer.parseInt(xText.getText());
            int yInput = Integer.parseInt(yText.getText());
            gridPane.resizeReset(xInput, yInput);
            myBuilder.addComponent(myRoot, gridPane.getGroup());
        });
        updateButton.setOnMouseEntered(e -> updateButton.setEffect(hoverOpacity));
        updateButton.setOnMouseExited(e -> updateButton.setEffect(null));
        Node swapButton = myBuilder.addCustomButton(myRoot, swapPath, swapX, swapY, swapWidth);
        swapButton.setOnMouseClicked(e->{gridPane.swap(sideMenu.getHandler().getSelected().getList());
        System.out.println(gridPane.getClicked());});
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
    }

}
