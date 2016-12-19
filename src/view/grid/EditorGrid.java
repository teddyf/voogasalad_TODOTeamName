package view.grid;

import java.util.*;
import model.block.blocktypes.BlockType;
import utilities.builder.UIBuilder;
import utilities.builder.ComponentProperties;
import utilities.builder.DialogBuilder;
import view.scenes.editor.objects.GameObject;
import view.scenes.editor.sidemenu.GameSideMenu;
import view.scenes.editor.sidemenu.ItemSideMenu;
import view.scenes.editor.sidemenu.PlayerSideMenu;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import controller.editor.EditorController;


/**
 * This is my masterpiece Teddy Franceschi
 * This class, albeit is a little long, contains the core functionality of the front end and
 * includes interfaces and observable/observer pattern which I believe shows off proper use of
 * design.  The function of this class is to construct the front end grid and help link it with
 * the backend however, it contains no backend elements.  In addition, it allows users to interact
 * with the grid on the side menus which are not contained by the grid, thus removing any 
 * dependencies that don't belong.  
 * 
 * @author Teddy Franceschi, Harshil Garg
 */
public class EditorGrid extends Grid implements Observer {
    private static final int CELL_PIXELS = 30;
    private static ColorAdjust hoverOpacity;
    private static GridPaneNode def;
    private String clickType;

    /**
     * Constructor for Editor Grid
     * 
     * @param gridWidth
     * @param gridHeight
     * @param renderWidth
     * @param renderHeight
     */
    public EditorGrid (int gridWidth, int gridHeight, int renderWidth, int renderHeight) {
        super(gridWidth, gridHeight, renderWidth, renderHeight, CELL_PIXELS);

        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);

        this.clickType = "";
        def = new GridPaneNode(0, 0, defaultText());

        for (GridPaneNode node : blockList) {
            if (!isWrapBlock(node.getRow(), node.getCol())) {
                makeClickable(node);
            }
        }
    }

    /**
     * Selects a node on click and adds it to list
     * 
     * @param node
     */
    public void click (GridPaneNode node) {
        if (clicked.contains(node)) {
            node.getImage().setEffect(null);
            clicked.remove(node);
        }
        else {
            clicked.add(node);
        }
    }

    private void makeClickable (GridPaneNode node) {
        node.getImage().setOnMouseExited(e -> {
            if (!clicked.contains(node))
                node.getImage().setEffect(null);
        });
        node.getImage().setOnMouseEntered(e -> {
            node.getImage().setEffect(hoverOpacity);
        });
        node.getImage().setOnMouseClicked(e -> {
            click(node);
        });
    }

    /**
     * @param obj
     * @param control
     * @param imagePaths
     */
    public void nodeClick (GameObject obj,
                           EditorController control,
                           List<String> imagePaths) {
        setChanged();
        notifyObservers();
        if (clicked.size() == 1) {
            if (clickType.equals("SWAP")) {
                swap(obj, control);
            }
            else if (clickType.equals("PLAYER") && imagePaths.size() > 0) {
                String name = setDialogue("Name for Player", "Set the name for your player");
                buildPlayer(control, name, imagePaths);
            }
        }
        else if (clicked.size() == 2 && clickType.equals("LINK")) {
            if (buildLink(clicked.get(0), clicked.get(1), control)) {
                successMessage("Link forged!",
                               "Successfully built a link between selected objects!");
            }
        }
        for (int i = 0; i < clicked.size(); i++) {
            clicked.get(i).getImage().setEffect(null);
        }
    }

    /**
     * Builds player
     * 
     * @param control
     * @param name
     * @param imagePaths
     */
    void buildPlayer (EditorController control, String name, List<String> imagePaths) {
        GridPaneNode node = clicked.get(0);
        int col = node.getCol();
        int row = node.getRow();
        int bCol = getBackendAssociatedColumn(node);
        int bRow = getBackendAssociatedRow(node);
        if (control.addPlayer(imagePaths, name, bRow, bCol)) {
            GridPaneNode temp = grid[col][row];
            GridPaneNode player = new GridPaneNode(row, col, imagePaths.get(0));
            player.setImageSize(CELL_PIXELS, CELL_PIXELS);
            player.setImageCoord(getXRender(col), getYRender(row));
            group.getChildren().add(player.getImage());

            ArrayList<GridPaneNode> list = new ArrayList<GridPaneNode>();
            list.add(temp);
            gridMap.storeObject(list);
            clicked.clear();
        }

    }

    /**
     * Handles user interaction with grid
     * 
     * @param obj GameObject selected
     * @param control
     */
    void swap (GameObject obj, EditorController control) {
        if (obj == null) {
            return;
        }
        List<GridPaneNode> list = obj.getImageTiles();
        getObjectNeighbors(list);
        for (int i = 0; i < clicked.size(); i++) {
            if (addObjToMap(list, clicked.get(i))) {
                for (int j = 0; j < list.size(); j++) {
                    int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                    int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                    GridPaneNode temp = grid[yPos][xPos];
                    selectClickMode(temp, list, obj, j, control);
                }
            }
            clicked.get(i).getImage().setEffect(null);
        }
        clicked.clear();
    }

    /**
     * Handles deletion of objects on the grid.
     * 
     * @param control
     */
    public void delete (EditorController control) {
        ArrayList<Integer> deleted = new ArrayList<Integer>();
        for (int i = 0; i < clicked.size(); i++) {
            GridPaneNode temp = clicked.get(i);
            deleted.addAll(gridMap.sharesObjWith(temp.getRow(), temp.getCol()));
            gridMap.collisionRemoval(temp.getRow(), temp.getCol());
        }
        if (!deleted.isEmpty()) {
            for (int i = 0; i < deleted.size(); i += 2) {

                GridPaneNode node = grid[deleted.get(i)][deleted.get(i + 1)];
                control.addBlock(defaultText(), BlockType.GROUND, getBackendAssociatedRow(node),
                                 getBackendAssociatedColumn(node));
                node.swap(def);

            }
        }
        clicked.clear();
    }

    @Override
    /**
     * Update method that is called whenever observable classes linked with this observer are
     * changed
     */
    public void update (Observable o, Object arg) {
        if (o instanceof PlayerSideMenu) {
            clickType = "PLAYER";
            clicked.clear();
        }
        else if (o instanceof GameSideMenu) {
            clickType = "LINK";

        }
        else if (o instanceof ItemSideMenu) {
            clickType = "SWAP";
            clicked.clear();
        }
    }

    /**
     * Converts Controller input into a grid to allow reopening a previous editor file
     * 
     * @param row Integer row value for position
     * @param col Integer col value for position
     * @param name Name of node/image path
     */
    public void blockToGridPane (int row, int col, String name) {
        GridPaneNode temp = new GridPaneNode(row, col, name);
        makeClickable(temp);
        blockList.add(temp);
    }

    private void selectClickMode (GridPaneNode temp,
                                  List<GridPaneNode> list,
                                  GameObject obj,
                                  int j,
                                  EditorController control) {
        if (obj.getBlockType().equals(BlockType.COMMUNICATOR)) {
            String message =
                    setDialogue("Set the dialogue for the communicator block.",
                                "Dialog for the communicator block:");
            if (!message.isEmpty()) {
                addBlockToGrid(temp, list, obj, j, control);
                control.addMessage(message, getBackendAssociatedRow(temp),
                                   getBackendAssociatedColumn(temp));
            }
        }
        else if (obj.getBlockType().equals(BlockType.GATE)) {
            addBlockToGrid(temp, list, obj, j, control);
            gateTransition(temp, control);
        }
        else if (obj.getBlockType().equals(BlockType.NPC)) {
            String message =
                    setDialogue("Set the dialogue for the NPC block", "Dialogue for the NPC block");
            if (!message.isEmpty()) {
                addBlockToGrid(temp, list, obj, j, control);
                control.addMessage(message, getBackendAssociatedRow(temp),
                                   getBackendAssociatedColumn(temp));
            }
        }
        else {
            addBlockToGrid(temp, list, obj, j, control);
        }
    }

    private void addBlockToGrid (GridPaneNode temp,
                                 List<GridPaneNode> list,
                                 GameObject obj,
                                 int j,
                                 EditorController control) {
        temp.swap(list.get(j));
        control.addBlock(temp.getName(), obj.getBlockType(), getBackendAssociatedRow(temp),
                         getBackendAssociatedColumn(temp));
    }

    private void gateTransition (GridPaneNode node, EditorController control) {
        String path = node.getName();
        if (path.indexOf("OPEN") < 0) {
            control.setGateStatus(getBackendAssociatedRow(node), getBackendAssociatedColumn(node),
                                  false);
        }
        else {
            control.setGateStatus(getBackendAssociatedRow(node), getBackendAssociatedColumn(node),
                                  true);
        }
    }

    private void successMessage (String header, String content) {
        new UIBuilder().addCustomAlert(new ComponentProperties().header(header).content(content));
    }

    private String setDialogue (String header, String content) {
        DialogBuilder db = new DialogBuilder(new ComponentProperties()
                .header(header)
                .content(content));
        Optional<String> response = db.getResponse();
        return response.orElse(new String());
    }

    private boolean addObjToMap (List<GridPaneNode> list, GridPaneNode objRoot) {
        int xPos = objRoot.getCol();
        int yPos = objRoot.getRow();
        List<GridPaneNode> temp = new ArrayList<GridPaneNode>();
        for (int i = 0; i < list.size(); i++) {
            int xRef = xPos + list.get(i).getCol();
            int yRef = yPos + list.get(i).getRow();
            if (!gridMap.available(yRef, xRef)) {
                return false;
            }
            else if (yRef >= gridHeight + WRAP / 2 || yRef < 0 || xRef >= gridWidth + WRAP / 2 ||
                     xRef < 0) {
                return false;
            }
            temp.add(grid[yRef][xRef]);
        }
        gridMap.storeObject(temp);
        return true;
    }

    /**
     * Gets neighbors if object is placed
     *
     * @param list
     */
    private void getObjectNeighbors (List<GridPaneNode> list) {
        ArrayList<Integer> xPos = new ArrayList<Integer>();
        ArrayList<Integer> yPos = new ArrayList<Integer>();
        for (int i = 0; i < clicked.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                xPos.add(clicked.get(i).getCol() + list.get(j).getCol());
                yPos.add(clicked.get(i).getRow() + list.get(j).getRow());
            }
            checkNeighbors(xPos, yPos, list.size());
        }
    }

    /**
     * Deletes object at node if object is there (Can click on any node
     * that object occupies)
     * 
     * @param control
     */

    private boolean buildLink (GridPaneNode node1,
                               GridPaneNode node2,
                               EditorController controller) {
        clicked.clear();
        return controller.linkBlocks(getBackendAssociatedRow(node1),
                                     getBackendAssociatedColumn(node1), 0,
                                     getBackendAssociatedRow(node2),
                                     getBackendAssociatedColumn(node2), 0);
    }

    /**
     * Removes neighbors in clicked if object would contain both
     *
     * @param xCoords
     * @param yCoords
     * @param objSize
     */
    private void checkNeighbors (List<Integer> xCoords, List<Integer> yCoords, int objSize) {
        for (int i = 0; i < clicked.size(); i++) {
            GridPaneNode temp = clicked.get(i);
            for (int j = 0; j < xCoords.size(); j++) {
                if (temp.getCol() == xCoords.get(j) && temp.getRow() == yCoords.get(j) &&
                    j % objSize != 0) {
                    clicked.remove(i);
                }
            }
        }
    }

    /*
     * Getters and Setters
     */
    public List<GridPaneNode> getNodeList () {
        return blockList;
    }

    public Group getGroup () {
        return group;
    }

    public double getWidth () {
        return gridWidth;
    }

    public double getHeight () {
        return gridHeight;
    }

    private int getBackendAssociatedRow (GridPaneNode node) {
        return node.getRow() - WRAP / 2;
    }

    private int getBackendAssociatedColumn (GridPaneNode node) {
        return node.getCol() - WRAP / 2;
    }

    public int getWrap () {
        return WRAP;
    }

    public double getXMin () {
        return -0.5 * CELL_PIXELS * (gridWidth + WRAP - renderWidth / CELL_PIXELS);
    }

    public double getYMin () {
        return -0.5 * CELL_PIXELS * (gridHeight + WRAP - renderHeight / CELL_PIXELS);
    }
}
