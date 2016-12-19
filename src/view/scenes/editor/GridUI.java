package view.scenes.editor;

import controller.editor.EditorController;
import view.grid.EditorGrid;
import view.grid.GridPaneNode;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseButton;
import utilities.PropertiesUtilities;
import utilities.builder.UIBuilder;
import view.scenes.editor.sidemenu.EditorControls;
import view.scenes.editor.sidemenu.GameSideMenu;
import view.scenes.editor.sidemenu.ItemSideMenu;
import view.scenes.editor.sidemenu.PlayerSideMenu;

import java.util.*;

/**
 * @author Teddy Franceschi, Harshil Garg
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
    private EditorGrid myGridPane;
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
     * Creates a model.grid of specified width and height, and then adds
     * functionality to the model.grid.
     */
    private void initGrid(int width, int height) {
        myGridPane = new EditorGrid(width,
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
     * Configures model.grid event handlers that allow the user to add and remove
     * ui.scenes.controller.editor.objects from it.
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
        myGridPane.setEditorRenderMap();
        setGridClickable();
        myBuilder.addComponent(myRoot, myGridPane.getGroup());
    }

    public EditorGrid getMyGridPane() {
        return myGridPane;
    }

    private void setGridClickable() {
        List<GridPaneNode> blockList = myGridPane.getNodeList();
        for (GridPaneNode node : blockList) {
            node.getImage().setOnMouseClicked(e -> {
                if (!myGridPane.isWrapBlock(node.getRow(), node.getCol())) {
                    myGridPane.click(node);
                    if (e.getButton() == MouseButton.SECONDARY)
                        myGridPane.delete(myController);
                    else
                        myGridPane.nodeClick(myItemMenu.getSelected(), myController, playerMenu.getImagePaths());
                }
            });
        }
    }

    GridScrollButton getScrollMechanism() {
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