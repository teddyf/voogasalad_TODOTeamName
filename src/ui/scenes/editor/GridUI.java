package ui.scenes.editor;

import editor.EditorController;
import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseButton;
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
    private GridScrollButton gsb;

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";

    public GridUI(Parent root, EditorController controller, ItemSideMenu itemMenu, int width, int height) {
        myRoot = root;
        myItemMenu = itemMenu;
        myController = controller;

        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        myBuilder = new UIBuilder();
        hoverOpacity = new ColorAdjust();
        initGrid(width, height);
    }

    /**
     * Creates a grid of specified width and height, and then adds
     * functionality to the grid.
     */
    private void initGrid(int width, int height) {
        myGridPane = new GridPane(width,
                height,
                myUtil.getIntProperty("windowWidth"),
                myUtil.getIntProperty("windowHeight"),
                myUtil.getIntProperty("gridX"),
                myUtil.getIntProperty("gridY"));
        myController.addGrid(width, height);
        myController.changeGrid(0);

        initGridControl();
        setGridClickable();

        scrollAnimation =
                new ScrollAnimation(myGridPane.getGroup(), myGridPane.getXMin(),
                        myGridPane.getYMin());
        gsb = new GridScrollButton(myRoot, scrollAnimation);
    }

    /**
     * Configures grid event handlers that allow the user to add and remove
     * ui.scenes.editor.objects from it.
     */
    private void initGridControl() {
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
        myGridPane.getGroup().toBack();
        hoverOpacity.setBrightness(myUtil.getDoubleProperty("buttonHoverOpacity"));
    }

    void loadGrid() {
        int colMax = myController.getPlayerCol();
        int rowMax = myController.getPlayerRow();
        myGridPane.loadReset(rowMax, colMax);
        myBuilder.removeComponent(myRoot, myGridPane.getGroup());
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                myGridPane.blockToGridPane(i, j, myController.getBlock(i, j));
            }
        }
        myGridPane.setRenderMap();
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
    }

    public GridPane getMyGridPane() {
        return myGridPane;
    }

    private void setGridClickable() {
        List<GridPaneNode> blockList = myGridPane.getNodeList();
        for (GridPaneNode node : blockList) {
            node.getImage().setOnMouseClicked(e -> {
                myGridPane.click(node);
                if (e.getButton() == MouseButton.SECONDARY) {
                    myGridPane.delete();
                } else {

                    myGridPane.swap(myItemMenu.getSelected(),
                            myController);
                }
            });
        }
    }

    public GridScrollButton getScrollMechanism() {
        return gsb;
    }

}