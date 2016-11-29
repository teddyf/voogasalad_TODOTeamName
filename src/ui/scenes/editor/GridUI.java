package ui.scenes.editor;

import editor.SidePanel;
import grid.GridPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Teddy Franceschi, Robert Steilberg
 *         <p>
 *         This class initializes the grid-based UI used to create the overworld.
 */
public class GridUI {

    private ResourceBundle myResources;
    private GridPane myGridPane;
    private UIBuilder myBuilder;
    private Parent myRoot;
    private SidePanel myItemMenu;

    GridUI(Parent root, SidePanel itemMenu, String resourcesPath) {
        myRoot = root;
        myItemMenu = itemMenu;
        myResources = ResourceBundle.getBundle(resourcesPath);
        myBuilder = new UIBuilder();
    }

    /**
     * Configures grid event handlers that allow the user to add and remove
     * objects from it.
     */
    private void initGridControl() {
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
        PropertiesUtilities util = new PropertiesUtilities();
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(util.getDoubleProperty(myResources, "buttonHoverOpacity"));
        int updateX = util.getIntProperty(myResources, "updateX");
        int updateY = util.getIntProperty(myResources, "updateY");
        int updateWidth = util.getIntProperty(myResources, "updateWidth");
        int widthInputX = util.getIntProperty(myResources, "inputWidthX");
        int widthInputY = util.getIntProperty(myResources, "inputWidthY");
        int widthInputWidth = util.getIntProperty(myResources, "inputWidthWidth");
        String widthInputText = myResources.getString("inputWidthText");
        int heightInputX = util.getIntProperty(myResources, "inputHeightX");
        int heightInputY = util.getIntProperty(myResources, "inputHeightY");
        int heightInputWidth = util.getIntProperty(myResources, "inputHeightWidth");
        String heightInputText = myResources.getString("inputHeightText");
        int swapX = util.getIntProperty(myResources, "swapX");
        int swapY = util.getIntProperty(myResources, "swapY");
        int swapWidth = util.getIntProperty(myResources, "swapWidth");
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
            myBuilder.removeComponent(myRoot, myGridPane.getGroup());
            TextField xText = (TextField) widthInputField;
            TextField yText = (TextField) heightInputField;
            int xInput = Integer.parseInt(xText.getText());
            int yInput = Integer.parseInt(yText.getText());
            myGridPane.resizeReset(xInput, yInput);
            myBuilder.addComponent(myRoot, myGridPane.getGroup());
        });
        updateButton.setOnMouseEntered(e -> updateButton.setEffect(hoverOpacity));
        updateButton.setOnMouseExited(e -> updateButton.setEffect(null));
        Node swapButton = myBuilder.addCustomButton(myRoot, swapPath, swapX, swapY, swapWidth);
        swapButton.setOnMouseClicked(e -> myGridPane.swap(myItemMenu.getHandler().getSelected().getList()));
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
    }

    /**
     * Creates the grid and then calls a method to add functionality.
     */
    public void initGrid(int gridWidth, int gridHeight) {
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(
                gridWidth,
                gridHeight,
                util.getIntProperty(myResources, "gridWidth"),
                util.getIntProperty(myResources, "gridHeight"),
                util.getIntProperty(myResources, "gridX"),
                util.getIntProperty(myResources, "gridY"));
        initGridControl();
    }
    
    /**
     * Creates the grid and then calls a method to add functionality.
     */
    public void initGrid() {
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(
                util.getIntProperty(myResources, "gridCellsWide"),
                util.getIntProperty(myResources, "gridCellsHeight"),
                util.getIntProperty(myResources, "gridWidth"),
                util.getIntProperty(myResources, "gridHeight"),
                util.getIntProperty(myResources, "gridX"),
                util.getIntProperty(myResources, "gridY"));
        initGridControl();
    }

}
