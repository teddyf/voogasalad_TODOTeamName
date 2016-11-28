package ui.scenes.editor;

import editor.Screen;
import editor.SidePanel;
import grid.GridPane;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;
import ui.UILauncher;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class GridUI {

    private static final String EDITOR_RESOURCES = "resources/properties/gameeditor";
    private ResourceBundle myResources;
    private GridPane myGridPane;
    private UIBuilder myBuilder;
    private Parent myRoot;
    private Group mySideMenuRegion;
    private SidePanel sideMenu;

    GridUI(Parent root) {
        myRoot = root;
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myBuilder = new UIBuilder();
    }

    private void initRegions () {
        mySideMenuRegion = myBuilder.addRegion(800, 0);
        myBuilder.addComponent(myRoot, mySideMenuRegion);
    }

    private void initSideMenu () {
        sideMenu = new SidePanel(mySideMenuRegion);
    }

    /**
     * Sets Grid Control
     */
    private void setGridControl() {
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
        swapButton.setOnMouseClicked(e -> {
            myGridPane.swap(sideMenu.getHandler().getSelected().getList());
            System.out.println(myGridPane.getClicked());
        });
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
    }

    public void initGrid() {
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(
                util.getIntProperty(myResources, "gridCellsWide"),
                util.getIntProperty(myResources, "gridCellsHeight"),
                util.getIntProperty(myResources, "gridWidth"),
                util.getIntProperty(myResources, "gridHeight"),
                util.getIntProperty(myResources, "gridX"),
                util.getIntProperty(myResources, "gridY"));
        myBuilder.addComponent(myRoot,
                new Screen(Integer.parseInt(myResources.getString("screenWidth")),
                        Integer.parseInt(myResources.getString("screenHeight")))
                        .getRoot());
        setGridControl();
        initRegions();
        initSideMenu();
    }

}
