package ui.scenes.editor;

import editor.EditorController;
import javafx.scene.shape.Rectangle;
import ui.GridPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.scenes.editor.objects.ItemPanelObjects;

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
    private ItemPanelObjects myEditorObjects;
    private EditorController myController;
    private ColorAdjust hoverOpacity;

    GridUI(Parent root, ItemPanelObjects editorObjects, EditorController controller, ResourceBundle resources) {
        myRoot = root;
        myEditorObjects = editorObjects;
        myResources = resources;
        myBuilder = new UIBuilder();
        myController = controller;
        hoverOpacity = new ColorAdjust();
    }

    /**
     * Configures grid event handlers that allow the user to add and remove
     * ui.scenes.editor.objects from it.
     */
    private void initGridControl() {
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
        PropertiesUtilities util = new PropertiesUtilities();
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
            TextField xText = (TextField) widthInputField;
            TextField yText = (TextField) heightInputField;
            try {
                int xInput = Integer.parseInt(xText.getText());
                int yInput = Integer.parseInt(yText.getText());
                myBuilder.removeComponent(myRoot, myGridPane.getGroup());
                myGridPane.resizeReset(xInput, yInput);
                myBuilder.addComponent(myRoot, myGridPane.getGroup());
            } catch (Exception exc) {
                myBuilder.addNewAlert("Invalid Resize", "Please enter an inter value for row and column count");
            }

        });

        updateButton.setOnMouseEntered(e -> updateButton.setEffect(hoverOpacity));
        updateButton.setOnMouseExited(e -> updateButton.setEffect(null));
        Node swapButton = myBuilder.addCustomButton(myRoot, swapPath, swapX, swapY, swapWidth);
        //TODO add interaction somewhere here as well
        swapButton.setOnMouseClicked(e -> myGridPane.swap(myEditorObjects.getSelected(),
                myController));
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
        //Node linkButton = buildButton("linkX", "linkY", "linkWidth", "linkPath");
    }

    public void loadGrid() {
        int colMax = myController.getCol();
        int rowMax = myController.getRow();
        myGridPane.loadReset(rowMax, colMax);
        System.out.println(rowMax);
        System.out.println(colMax);
        myBuilder.removeComponent(myRoot, myGridPane.getGroup());
        for(int i = 0; i < rowMax; i++){
            for(int j = 0; j < colMax; j++){
                myGridPane.blockToGridPane(i, j, myController.getBlock(i, j));
            }
        }
        myGridPane.setRenderMap();
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
    }

    /**
     * Adds a border to the grid based on the grid's position and size
     * by creating a Rectangle behind it
     */
    private void addGridBorder() {
        PropertiesUtilities util = new PropertiesUtilities();
        Rectangle border = new Rectangle();
        border.setLayoutX(util.getIntProperty(myResources, "gridX") - util.getIntProperty(myResources, "borderSize"));
        border.setLayoutY(util.getIntProperty(myResources, "gridY") - util.getIntProperty(myResources, "borderSize"));
        border.setWidth(util.getIntProperty(myResources, "gridWidth") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setHeight(util.getIntProperty(myResources, "gridHeight") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setId("grid-border");
        myBuilder.addComponent(myRoot, border);
    }
    
    /**
     * Builds a button from string input
     * @param xPos X-position of button
     * @param yPos Y-Position of button
     * @param width width of button
     * @param path Image path of button
     * @return
     */
    private Node buildButton(String xPos, String yPos, String width, String path){
        int x = Integer.parseInt(myResources.getString(xPos));
        int y = Integer.parseInt(myResources.getString(yPos));
        int girth = Integer.parseInt(myResources.getString(width));
        String route = myResources.getString(path);
        Node node = myBuilder.addCustomButton(myRoot, route, x, y, girth);
        node.setOnMouseEntered(e->{
            node.setEffect(hoverOpacity);
        });
        node.setOnMouseExited(e->{
            node.setEffect(null);
        });
        return node;
        
    }

    /**
     * Creates a grid of specified width and height, and then adds
     * functionality to the grid
     */
    public void initGrid(int gridWidth, int gridHeight) {
        addGridBorder();
        PropertiesUtilities util = new PropertiesUtilities();
        myGridPane = new GridPane(gridWidth,
                gridHeight,
                util.getIntProperty(myResources, "gridWidth"),
                util.getIntProperty(myResources, "gridHeight"),
                util.getIntProperty(myResources, "gridX"),
                util.getIntProperty(myResources, "gridY"));
        myController.addGrid(gridHeight, gridWidth);
        initGridControl();
    }

//    /**
//     * Creates a grid and then adds functionality to it
//     */
//    public void initGrid () {
//        addGridBackground();
//        PropertiesUtilities util = new PropertiesUtilities();
//        myGridPane = new GridPane(
//                                  util.getIntProperty(myResources, "gridCellsWide"),
//                                  util.getIntProperty(myResources, "gridCellsHeight"),
//                                  util.getIntProperty(myResources, "gridWidth"),
//                                  util.getIntProperty(myResources, "gridHeight"),
//                                  util.getIntProperty(myResources, "gridX"),
//                                  util.getIntProperty(myResources, "gridY"));
//        initGridControl();
//    }

}