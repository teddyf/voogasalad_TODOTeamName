package ui.scenes.editor;

import editor.EditorController;
import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseButton;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;
import ui.scenes.editor.sidemenu.EditorControls;
import ui.scenes.editor.sidemenu.GameSideMenu;
import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerSideMenu;

import java.util.*;

/**
 * @author Teddy Franceschi, Robert Steilberg, Harshil Garg
 *         <p>
 *         This class initializes the grid-based UI used to create the overworld.
 */
public class GridUI extends Observable implements Observer {
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
    private PlayerSideMenu playerMenu;
    private EditorControls sideControls;
    private GameSideMenu gameSideMenu;
    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";

    public GridUI(Parent root, EditorController controller, EditorControls sideMenu, int width, int height) {
        myRoot = root;
        myItemMenu = sideMenu.getMyItemMenu();
        playerMenu = sideMenu.getPlayerMenu();
        myController = controller;
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
        sideControls = sideMenu;
        myBuilder = new UIBuilder();
        hoverOpacity = new ColorAdjust();
        gameSideMenu = sideMenu.getGridSideMenu();
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
                myUtil.getIntProperty("windowHeight"));
        myController.addGrid(height, width);
        myController.changeGrid(0);
        initGridControl();
        setGridClickable();
        scrollAnimation =
                new ScrollAnimation(myGridPane.getGroup(), myGridPane.getXMin(),
                        myGridPane.getYMin());
        gsb = new GridScrollButton(myRoot, scrollAnimation);
        setupObservable();
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
        int colMax = myController.getNumCols();
        int rowMax = myController.getNumRows();
        myGridPane.loadReset(rowMax, colMax);
        myBuilder.removeComponent(myRoot, myGridPane.getGroup());
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                myGridPane.blockToGridPane(i, j, myController.getBlock(i, j));
            }
        }
        myGridPane.shiftAll();
        myGridPane.setRenderMap();
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
    }

    public GridPane getMyGridPane() {
        return myGridPane;
    }

    private void setGridClickable() {
        List<GridPaneNode> blockList = myGridPane.getNodeList();
        System.out.println(myGridPane.getWidth() + " is the width of the gridpane");
        System.out.println(myGridPane.getHeight() + " is the hieght of ght egridpa");
        for (GridPaneNode node : blockList) {
            node.getImage().setOnMouseClicked(e -> {
                int WRAP = myGridPane.getWrap();
                if (node.getCol() >= WRAP / 2 && node.getCol() < myGridPane.getWidth() + WRAP / 2 && node.getRow() >= WRAP / 2 && node.getRow() < myGridPane.getHeight() + WRAP  / 2) {
                    System.out.println("row =" + node.getRow() + " col = " + node.getCol());
                    myGridPane.click(node);
                    if (e.getButton() == MouseButton.SECONDARY) {
                        myGridPane.delete(myController);
                    } else {
                        System.out.println("disease");
                        myGridPane.nodeClick(myItemMenu.getSelected(),
                                myController, playerMenu.getImagePaths());
                    }
                }
            });
        }
    }

    public GridScrollButton getScrollMechanism() {
        return gsb;
    }

    private void setupObservable() {
        gameSideMenu.addObserver(myGridPane);
        playerMenu.addObserver(myGridPane);
        myItemMenu.addObserver(myGridPane);
    }

    public void update(Observable editorIO, Object controller) {
        if (editorIO instanceof EditorIO) {
            myController = (EditorController) controller;
        }
    }
}