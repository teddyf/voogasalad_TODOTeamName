package ui.scenes.editor;

import editor.EditorController;
import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.scenes.editor.sidemenu.ItemSideMenu;

import java.util.*;


/**
 * @author Teddy Franceschi, Robert Steilberg, Harshil Garg
 *         <p>
 *         This class initializes the grid-based UI used to create the overworld.
 */
public class GridUI {

    private Parent myRoot;
    private ItemSideMenu myItemMenu;
    private EditorController myController;

    private ResourceBundle myResources;
    private PropertiesUtilities myUtil;
    private UIBuilder myBuilder;
    private ColorAdjust hoverOpacity;

    private GridPane myGridPane;

    private ScrollAnimation scrollAnimation;

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";

    public GridUI(Parent root, ItemSideMenu itemMenu, EditorController controller) {
        myRoot = root;
        myItemMenu = itemMenu;
        myController = controller;

        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        myBuilder = new UIBuilder();
        hoverOpacity = new ColorAdjust();
    }


    /**
     * Creates a grid of specified width and height, and then adds
     * functionality to the grid.
     */
    public void initGrid(int width, int height) {
        myGridPane = new GridPane(width,
                height,
                myUtil.getIntProperty("windowWidth"),
                myUtil.getIntProperty("windowHeight"),
                myUtil.getIntProperty("gridX"),
                myUtil.getIntProperty("gridY"));
        myController.addGrid(width, height);
        myController.changeGrid(0);
        initGridControl();
        scrollAnimation = new ScrollAnimation(myGridPane.getGroup(), myGridPane.getXMin(), myGridPane.getYMin());

        GridScrollButton gsb = new GridScrollButton(myRoot, scrollAnimation);
    }


    /**
     * Configures grid event handlers that allow the user to add and remove
     * ui.scenes.editor.objects from it.
     */
    private void initGridControl() {
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
        hoverOpacity.setBrightness(myUtil.getDoubleProperty("buttonHoverOpacity"));
        int updateX = myUtil.getIntProperty("updateX");
        int updateY = myUtil.getIntProperty("updateY");
        int updateWidth = myUtil.getIntProperty("updateWidth");
        int widthInputX = myUtil.getIntProperty("inputWidthX");
        int widthInputY = myUtil.getIntProperty("inputWidthY");
        int widthInputWidth = myUtil.getIntProperty("inputWidthWidth");
        String widthInputText = myUtil.getStringProperty("inputWidthText");
        int heightInputX = myUtil.getIntProperty("inputHeightX");
        int heightInputY = myUtil.getIntProperty("inputHeightY");
        int heightInputWidth = myUtil.getIntProperty("inputHeightWidth");
        String heightInputText = myUtil.getStringProperty("inputHeightText");
        int swapX = myUtil.getIntProperty("swapX");
        int swapY = myUtil.getIntProperty("swapY");
        int swapWidth = myUtil.getIntProperty("swapWidth");
        String swapPath = myUtil.getStringProperty("swapPath");
        Node widthInputField =
                myBuilder.addCustomTextField(myRoot, widthInputText, widthInputX, widthInputY,
                        widthInputWidth,20);
        Node heightInputField =
                myBuilder.addCustomTextField(myRoot, heightInputText, heightInputX, heightInputY,
                        heightInputWidth,20);
        String updatePath = myResources.getString("updatePath");
        Node updateButton =
                myBuilder.addCustomImageView(myRoot, updateX, updateY, updatePath, updateWidth, "");
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
        Node swapButton = myBuilder.addCustomImageView(myRoot, swapX, swapY, swapPath, swapWidth, "");
        //TODO add interaction somewhere here as well
        swapButton.setOnMouseClicked(e -> myGridPane.swap(myItemMenu.getSelected(),
                myController));
        swapButton.setOnMouseEntered(e -> swapButton.setEffect(hoverOpacity));
        swapButton.setOnMouseExited(e -> swapButton.setEffect(null));
        Node linkButton = buildButton("linkX", "linkY", "linkWidth", "linkPath");
        linkButton.setOnMouseClicked(e->{
            List<GridPaneNode> selected = myGridPane.getClicked();
            if(selected.size()==2){
                System.out.print("LINK ");
                System.out.println(myGridPane.buildLink(selected.get(0),selected.get(1),myController));
                
            }
        });
        
        Node deleteButton = buildButton("deleteX","deleteY","deleteWidth","deletePath");
        deleteButton.setOnMouseClicked(e->{
            List<GridPaneNode> selected = myGridPane.getClicked();
            for(int i = 0; i <  selected.size(); i++){
                selected.get(i).getImage().setEffect(null);
            }
            myGridPane.delete(myGridPane.getClicked());
        });
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
/*    private void addGridBorder() {
        PropertiesUtilities util = new PropertiesUtilities();
        Rectangle border = new Rectangle();
        border.setLayoutX(util.getIntProperty(myResources, "gridX") - util.getIntProperty(myResources, "borderSize"));
        border.setLayoutY(util.getIntProperty(myResources, "gridY") - util.getIntProperty(myResources, "borderSize"));
        border.setWidth(util.getIntProperty(myResources, "gridWidth") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setHeight(util.getIntProperty(myResources, "gridHeight") + util.getIntProperty(myResources, "borderSize") * 2);
        border.setId("grid-border");
        myBuilder.addComponent(myRoot, border);
    }*/
    
    /**
     * Builds a button from string input
     * @param xPos X-position of button
     * @param yPos Y-Position of button
     * @param width width of button
     * @param path Image myIconPath of button
     * @return
     */
    private Node buildButton(String xPos, String yPos, String width, String path){
        int x = Integer.parseInt(myResources.getString(xPos));
        int y = Integer.parseInt(myResources.getString(yPos));
        int girth = Integer.parseInt(myResources.getString(width));
        String route = myResources.getString(path);
        Node node = myBuilder.addCustomImageView(myRoot, x, y, route, girth, "");
        node.setOnMouseEntered(e->{
            node.setEffect(hoverOpacity);
        });
        node.setOnMouseExited(e->{
            node.setEffect(null);
        });
        return node;
        
    }

    public GridPane getMyGridPane() {
        return myGridPane;
    }

}