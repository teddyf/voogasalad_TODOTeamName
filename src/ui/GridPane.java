package ui;

import java.util.*;
import block.BlockType;
import grid.GridSizeDirection;
import ui.builder.UIBuilder;
import ui.builder.ComponentProperties;
import ui.builder.DialogBuilder;
import ui.scenes.editor.objects.GameObject;
import ui.scenes.editor.sidemenu.GridSideMenu;
import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerSideMenu;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import editor.EditorController;


/**
 * @author Teddy Franceschi, Harshil Garg
 */
public class GridPane extends Observable implements Observer {

    private final int WRAP = 20;
    private final int CELL_PIXELS = 30;

    private Group group;
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private GridPaneNode[][] grid;

    private double gridWidth;
    private double gridHeight;
    private double renderWidth;
    private double renderHeight;
    private int renderTopLeftX;
    private int renderTopLeftY;

    private ColorAdjust hoverOpacity;
    private GridObjectMap gridMap;
    private UIBuilder builder;
    private GridPaneResizer gridResizer;
    //private GridPaneNode def;

    private GridPaneNode def;

    private String DEFAULT = "resources/images/tiles/ground/grass-";
    private String clickType;

    private String borderPath = "resources/images/tiles/obstacle/tree-4.png";

    public GridPane (int gridWidth,
                     int gridHeight,
                     int renderWidth,
                     int renderHeight,
                     int renderTopLeftX,
                     int renderTopLeftY) {

        group = new Group();
        blockList = new ArrayList<GridPaneNode>();
        clicked = new ArrayList<GridPaneNode>();

        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);
        builder = new UIBuilder();

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.renderTopLeftX = renderTopLeftX;
        this.renderTopLeftY = renderTopLeftY;
        this.clickType = "";
        this.gridResizer = new GridPaneResizer();
        def = new GridPaneNode(0, 0, defaultText());
        initializeGrid();
        setRenderMap();
    }

    private String defaultText () {
        int suffix = randomNumber(1, 4);
        return DEFAULT + suffix + ".png";
    }

    private int randomNumber (int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private double getXRender (int column) {
        double offset = -0.5 * CELL_PIXELS * (gridWidth + WRAP - renderWidth / CELL_PIXELS);
        return column * CELL_PIXELS + renderTopLeftX + offset;
    }

    private double getYRender (int row) {
        double offset = -0.5 * CELL_PIXELS * (gridHeight + WRAP - renderHeight / CELL_PIXELS);
        return row * CELL_PIXELS + renderTopLeftY + offset;
    }

    private void initializeGrid () {
        int columns = (int) gridWidth + WRAP;
        int rows = (int) gridHeight + WRAP;
        gridMap = new GridObjectMap(columns, rows);
        grid = new GridPaneNode[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                if (i >= WRAP / 2 && i < gridWidth + WRAP / 2 &&
                    j >= WRAP / 2 && j < gridHeight + WRAP / 2) {
                    GridPaneNode node = new GridPaneNode(i, j, defaultText());
                    blockList.add(node);
                    grid[j][i] = node;
                }
                else {
                    GridPaneNode node = new GridPaneNode(i, j, borderPath);
                    blockList.add(node);
                    grid[j][i] = node;
                }
            }
        }
    }

    public void resize(int amount, GridSizeDirection dir){

        gridResizer.resize(dir, amount, gridMap, this);
    }

    public void setRenderMap () {
        group = new Group();
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode node = blockList.get(i);
            double x = getXRender(node.getCol());
            double y = getYRender(node.getRow());
            node.setImageSize(CELL_PIXELS, CELL_PIXELS);
            node.setImageCoord(x, y);
            if (node.getCol() >= WRAP / 2 && node.getCol() < gridWidth + WRAP / 2 &&
                node.getRow() >= WRAP / 2 && node.getRow() < gridHeight + WRAP / 2)
                makeClickable(node);
            group.getChildren().add(node.getImage());
            grid[node.getCol()][node.getRow()] = node;
        }
    }

    private void setEmptyToDefault (GridPaneNode node) {
        if (gridMap.available(node.getCol(), node.getRow())) {
            node.swap(def, node.getImageNum());
        }
    }

    public void click (GridPaneNode node) {
        if (clicked.contains(node)) {
            node.getImage().setEffect(null);
            clicked.remove(node);
        } else {
            clicked.add(node);
        }
    }

    public void loadReset (double height, double width) {

        this.gridWidth = width;
        this.gridHeight = height;

        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        grid = new GridPaneNode[(int) height][(int) width];
    }

    public void nodeClick (GameObject obj,
                           EditorController control,
                           String name,
                           List<String> imagePaths) {
        setChanged();
        notifyObservers();
        if (clicked.size() == 1) {
            if (clickType.equals("SWAP")) {
                swap(obj, control);
            }
            else if (clickType.equals("PLAYER") && imagePaths.size() > 0) {
                buildPlayer(control, name, imagePaths);
            }
        }
        else if (clicked.size() == 2 && clickType.equals("LINK")) {
            if(buildLink(clicked.get(0), clicked.get(1), control)){
                successMessage("Link forged!", "Successfully built a link between selected objects!");
            }
        }
        for (int i = 0; i < clicked.size(); i++) {
            clicked.get(i).getImage().setEffect(null);
        }
    }

    public void buildPlayer (EditorController control, String name, List<String> imagePaths) {
        int col = clicked.get(0).getCol();
        int row = clicked.get(0).getRow();
        int bCol = getBackendRow(clicked.get(0));
        int bRow = getBackendCol(clicked.get(0));

        if (control.addPlayer(imagePaths, name, getBackendRow(clicked.get(0)),
                getBackendCol(clicked.get(0)))) {
            GridPaneNode temp = grid[col][row];
            GridPaneNode gpn = new GridPaneNode(row, col, imagePaths.get(0));
            gpn.setImageSize(CELL_PIXELS, CELL_PIXELS);
            gpn.setImageCoord(getXRender(col), getYRender(row));
            group.getChildren().add(gpn.getImage());

            ArrayList<GridPaneNode> list = new ArrayList<GridPaneNode>();
            list.add(temp);
            gridMap.storeObject(list);
            clicked = new ArrayList<GridPaneNode>();
        }

    }

    public List<GridPaneNode> swap (GameObject obj, EditorController control) {
        List<GridPaneNode> copy = new ArrayList<GridPaneNode>();
        if (obj == null) {
            return copy;
        }
        List<GridPaneNode> list = obj.getImageTiles();
        getObjectNeighbors(list);
        for (int i = 0; i < clicked.size(); i++) {
            if (addObjToMap(list, clicked.get(i))) {
                for (int j = 0; j < list.size(); j++) {
                    int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                    int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                    GridPaneNode temp = grid[xPos][yPos];
                    // TODO add dimension checker
                    
                    if (obj.getBlockType().equals(BlockType.COMMUNICATOR)) {
                        String message = setCommMessage();
                        if(!message.isEmpty()){
                            System.out.println("heyo");
                            temp.swap(list.get(j), list.get(j).getImageNum());
                            
                            control.addBlock(temp.getName(), obj.getBlockType(), getBackendRow(temp),
                                             getBackendCol(temp));
                            control.addMessage(message, getBackendRow(temp), getBackendCol(temp));
                        }
                    }
                    else if(obj.getBlockType().equals(BlockType.GATE)){
                        temp.swap(list.get(j), list.get(j).getImageNum());
                        gateTransition(temp, control);
                        control.addBlock(temp.getName(), obj.getBlockType(), getBackendRow(temp),
                                         getBackendCol(temp));
                    }
                    else{
                        temp.swap(list.get(j), list.get(j).getImageNum());
                        control.addBlock(temp.getName(), obj.getBlockType(), getBackendRow(temp),
                                         getBackendCol(temp));
                    }
                }
            }
            clicked.get(i).getImage().setEffect(null);
            copy = clicked;
        }
        clicked = new ArrayList<GridPaneNode>();
        return copy;
    }

    private void gateTransition(GridPaneNode node, EditorController control){
        String path = node.getName();
        if(path.indexOf("open")<0){
            control.setGateStatus(getBackendCol(node), getBackendRow(node), false);
        }
        else{
            control.setGateStatus(getBackendCol(node), getBackendRow(node), true);
        }
    }
    
    private void successMessage(String header, String content){
        builder.addCustomAlert(new ComponentProperties().header(header).content(content));
    }
    
    private String setCommMessage () {
        DialogBuilder db = new DialogBuilder(new ComponentProperties()
                .header("Set the dialog for the communicator block.")
                .content("Dialog for the communicator block:"));
        Optional<String> response = db.getResponse();
        return response.orElse(new String());
    }

    private void communicateMessage () {
        // builder.add
    }

    private void resetClicked () {
        clicked = new ArrayList<GridPaneNode>();
    }

    private boolean addObjToMap (List<GridPaneNode> list, GridPaneNode objRoot) {
        int xPos = objRoot.getCol();
        int yPos = objRoot.getRow();
        List<GridPaneNode> temp = new ArrayList<GridPaneNode>();
        for (int i = 0; i < list.size(); i++) {
            int xRef = xPos + list.get(i).getCol();
            int yRef = yPos + list.get(i).getRow();
            if (!gridMap.available(xRef, yRef)) {
                return false;
            }
            temp.add(grid[xRef][yRef]);
        }
        gridMap.storeObject(temp);
        return true;
        // TODO add dimension checker
    }

    /*
     * wtf is this
     * private void setPlayer (GridPaneNode temp, GameObject gameObject, EditorController control) {
     * if (gameObject instanceof Player1) {
     * //control.addPlayer(temp.getName(), "name", temp.getBackendRow(), temp.getBackendCol());
     * control.addBlock("resources/Default.png", BlockType.DECORATION, temp.getBackendRow(),
     * temp.getBackendCol());
     * }
     * }
     */

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

    public void delete () {
        ArrayList<Integer> deleted = new ArrayList<Integer>();
        for (int i = 0; i < clicked.size(); i++) {
            GridPaneNode temp = clicked.get(i);
            deleted.addAll(gridMap.sharesObjWith(temp.getCol(), temp.getRow()));
            gridMap.collisionRemoval(temp.getRow(), temp.getCol());
        }

        if (!deleted.isEmpty()) {
            for (int i = 0; i < deleted.size(); i += 2) {
                GridPaneNode node = grid[deleted.get(i)][deleted.get(i + 1)];
                node.swap(def, node.getImageNum());
            }
        }
        clicked = new ArrayList<GridPaneNode>();
        // gridMap.visObjectMap();
    }

    boolean buildLink (GridPaneNode node1, GridPaneNode node2, EditorController controller) {
        clicked.clear();
        return controller.linkBlocks(getBackendRow(node1), getBackendCol(node1), 0,
                getBackendRow(node2), getBackendCol(node2), 0);
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

    /**
     * Converts backend block to front end grid
     *
     * @param row
     * @param col
     * @param name
     */
    public void blockToGridPane (int row, int col, String name) {
        GridPaneNode temp = new GridPaneNode(row, col, name);
        blockList.add(temp);
    }

    public List<GridPaneNode> getNodeList () {
        return blockList;
    }

    public void setNodes (List<GridPaneNode> list) {
        this.blockList = list;
    }

    public void setGridArray(GridPaneNode[][] gridArray) {
        this.grid = gridArray;
    }

    public void setGridHeight(double height) {
        this.gridHeight = height;
    }

    public void setGridWidth(double width) {
        this.gridWidth = width;
    }

    public Group getGroup () {
        return group;
    }

    public List<GridPaneNode> getClicked () {
        return clicked;
    }

    public double getBlockSize () {
        return renderWidth / gridWidth;
    }

    public double getWidth () {
        return gridWidth;
    }

    public double getHeight () {
        return gridHeight;
    }

    public void makeClickable(GridPaneNode node) {
        node.getImage().setOnMouseExited(e -> {
            if (!clicked.contains(node))
                node.getImage().setEffect(null);
        });
        node.getImage().setOnMouseEntered(e -> {
            node.getImage().setEffect(hoverOpacity);
        });
        node.getImage().setOnMouseClicked(e -> {
            // node.getImage().setEffect(hoverOpacity);
            click(node);
        });
    }

    public double getXMin () {
        return -0.5 * CELL_PIXELS * (gridWidth + WRAP - renderWidth / CELL_PIXELS);
    }

    public double getYMin () {
        return -0.5 * CELL_PIXELS * (gridHeight + WRAP - renderHeight / CELL_PIXELS);
    }

    public void setClickType (String str) {
        clickType = str;
    }

    @Override
    public void update (Observable o, Object arg) {
        // System.out.println("here");
        if (o instanceof PlayerSideMenu) {
            clickType = "PLAYER";
            System.out.println(((PlayerSideMenu) o).getImagePaths());
        }
        else if (o instanceof GridSideMenu) {
            clickType = "LINK";

        } else if (o instanceof ItemSideMenu) {
            clickType = "SWAP";
        }
        else {
            System.out.println("fuck off Robert");
        }
    }

    private int getBackendRow(GridPaneNode gpn) {
        return gpn.getRow() - WRAP / 2;
    }

    private int getBackendCol(GridPaneNode gpn) {
        return gpn.getCol() - WRAP / 2;
    }

    public int getWrap () {
        return WRAP;
    }
}