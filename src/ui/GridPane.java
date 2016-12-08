package ui;
import java.util.*;
import block.BlockType;
import ui.scenes.editor.objects.GameObject;
import ui.scenes.editor.objects.Player1;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import editor.EditorController;

/**
 * 
 * @author Teddy Franceschi, Harshil Garg
 *
 */
public class GridPane {

    private final int WRAP = 10;
    private final int CELL_PIXELS = 50;

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
    private GridPaneNode def;

    private String DEFAULT = "resources/images/tiles/ground/grass-";

    public GridPane (int gridWidth, int gridHeight, int renderWidth,
                     int renderHeight, int renderTopLeftX, int renderTopLeftY) {

        group = new Group();
        blockList = new ArrayList<GridPaneNode>();
        clicked = new ArrayList<GridPaneNode>();

        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.renderTopLeftX = renderTopLeftX;
        this.renderTopLeftY = renderTopLeftY;

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
        //return rand.nextInt((max - min) + 1) + min;
        return 1;
    }

    private double getXRender (int column) {
        double offset = -0.5 * CELL_PIXELS * (gridWidth + WRAP  - renderWidth/CELL_PIXELS);
        return column * CELL_PIXELS + renderTopLeftX + offset;
    }

    private double getYRender (int row) {
        double offset = -0.5 * CELL_PIXELS * (gridHeight + WRAP  - renderHeight/CELL_PIXELS);
        return row * CELL_PIXELS + renderTopLeftY + offset;
    }

    private void initializeGrid () {
        int columns = (int) gridWidth + WRAP;
        int rows = (int) gridHeight + WRAP;
        gridMap = new GridObjectMap(columns, rows);
        grid = new GridPaneNode[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                GridPaneNode node = new GridPaneNode(i, j, defaultText());
                blockList.add(node);
                grid[j][i] = node;
            }
        }
    }

    public void setRenderMap () {
        group = new Group();
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode node = blockList.get(i);
            double x = getXRender(node.getCol());
            double y = getYRender(node.getRow());
            node.setImageSize(CELL_PIXELS, CELL_PIXELS);
            node.setImageCoord(x, y);
            if (node.getCol() >= WRAP / 2
                    && node.getCol() < gridWidth + WRAP / 2
                    && node.getRow() >= WRAP / 2
                    && node.getRow() < gridHeight + WRAP / 2)
                makeClickable(node);
            else
                node.getImage().setEffect(hoverOpacity);
            // System.out.println("col: " + node.getCol());
            // System.out.println("row: " + node.getRow());
            // System.out.println("length: " + grid.length);
            group.getChildren().add(node.getImage());
            grid[node.getCol()][node.getRow()] = node;
        }
    }

    public void resize () {
        grid = new GridPaneNode[(int) gridHeight][(int) gridWidth];
        System.out.println(blockList.size());
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode temp = blockList.get(i);
            temp.setImageSize(renderWidth / gridWidth, renderHeight / gridHeight);
            temp.setImageCoord(getXRender(temp.getCol()), getYRender(temp.getRow()));
            blockList.set(i, temp);
            grid[temp.getCol()][temp.getRow()] = temp;
        }
        group = new Group();
        for (int i = 0; i < blockList.size(); i++) {
            group.getChildren().add(blockList.get(i).getImage());
        }
    }

    private void resizeResetLess (double x, double y) {
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode temp = blockList.get(i);
            if (temp.getCol() >= x || temp.getRow() >= y) {
                System.out.println(temp.getCol() + "," + temp.getRow());
                blockList.remove(i);
                gridMap.resizeRemove(temp.getRow(), temp.getCol());
                i--;
            }
        }
        for (int i = 0; i < blockList.size(); i++) {
            setEmptyToDefault(blockList.get(i));
        }
        gridWidth = x;
        gridHeight = y;
        resize();
    }

    private void resizeResetMore (double x, double y) {
        int count = 0;
        System.out.println(x - gridWidth);
        System.out.println(x - gridHeight);
        for (int i = (int) gridWidth; i < x; i++) {
            for (int j = 0; j < y; j++) {
                count++;
                GridPaneNode node = new GridPaneNode(i, j, defaultText());
                makeClickable(node);
                blockList.add(node);
                gridMap.resizeAdd(node.getRow(), node.getCol());
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = (int) gridHeight; j < y; j++) {
                count++;
                GridPaneNode node = new GridPaneNode(i, j, defaultText());
                makeClickable(node);
                blockList.add(node);
            }
        }

        gridWidth = x;
        gridHeight = y;

        resize();
    }

    public void resizeReset (double x, double y) {
        if (gridHeight - y < 0 || gridWidth - x < 0) {
            resizeResetMore(x, y);
        }
        else if (gridHeight - y > 0 || gridWidth - x > 0) {
            resizeResetLess(x, y);
        }
    }

    private void setEmptyToDefault (GridPaneNode node) {
        if (gridMap.available(node.getCol(), node.getRow())) {
            node.swap(def, node.getImageNum());
        }
    }

    public void resetKeepSize () {
        reset();
    }

    public void click (GridPaneNode node) {
        if (clicked.contains(node)) {
            node.getImage().setEffect(null);
            clicked.remove(node);
        }
        else {

            clicked.add(node);
        }
    }

    private void reset () {
        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        initializeGrid();
        setRenderMap();
    }

    public void loadReset (double height, double width) {

        this.gridWidth = width;
        this.gridHeight = height;

        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        grid = new GridPaneNode[(int) height][(int) width];
    }


    public List<GridPaneNode> swap (GameObject obj, EditorController control) {
        List<GridPaneNode> list = obj.getImageTiles();
        List<GridPaneNode> copy = new ArrayList<GridPaneNode>();
        getObjectNeighbors(list);
        for (int i = 0; i < clicked.size(); i++) {
            if (addObjToMap(list, clicked.get(i))) {
                for (int j = 0; j < list.size(); j++) {
                    int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                    int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                    GridPaneNode temp = grid[xPos][yPos];
                    // TODO add dimension checker
                    temp.swap(list.get(j), list.get(j).getImageNum());
                    control.addBlock(temp.getName(), obj.getBlockType(), temp.getBackendRow(),
                                  temp.getBackendCol());
                    setPlayer(temp, obj, control);
                }
            }
            clicked.get(i).getImage().setEffect(null);
            copy = clicked;
        }
        //System.out.println(gridMap);
        clicked = new ArrayList<GridPaneNode>();
        return copy;
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

    private void setPlayer (GridPaneNode temp, GameObject gameObject, EditorController control) {
        if (gameObject instanceof Player1) {
            control.addPlayer(temp.getName(), temp.getBackendRow(), temp.getBackendCol());
            control.addBlock("resources/Default.png", BlockType.DECORATION, temp.getBackendRow(),
                             temp.getBackendCol());
        }
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

    public void delete (List<GridPaneNode> list) {
        ArrayList<Integer> deleted = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            GridPaneNode temp = list.get(i);
            deleted.addAll(gridMap.sharesObjWith(temp.getCol(), temp.getRow()));
            System.out.println(deleted);
            gridMap.collisionRemoval(temp.getRow(), temp.getCol());
        }
        System.out.println(deleted.get(0));
        if (!deleted.isEmpty()) {
            for (int i = 0; i < deleted.size(); i+=2) {            
                GridPaneNode node = grid[deleted.get(i)][deleted.get(i+1)];
                node.swap(def, node.getImageNum());
            }        
        }
        clicked = new ArrayList<GridPaneNode>();
        System.out.println(gridMap);
    }

    public boolean buildLink (GridPaneNode node1, GridPaneNode node2, EditorController controller) {
        return controller.linkBlocks(node1.getBackendRow(), node1.getBackendCol(), node2.getBackendRow(), node2.getBackendCol(),
                                     0, 0);
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

    public void makeClickable (GridPaneNode node) {
        node.getImage().setOnMouseExited(e -> {
            if (!clicked.contains(node))
                node.getImage().setEffect(null);
        });
        node.getImage().setOnMouseEntered(e -> {
            node.getImage().setEffect(hoverOpacity);
        });
        node.getImage().setOnMouseClicked(e -> {
            node.getImage().setEffect(hoverOpacity);
            click(node);
        });
    }

    public double getXMin() {
        return -0.5 * CELL_PIXELS * (gridWidth + WRAP  - renderWidth/CELL_PIXELS);
    }

    public double getYMin() {
        return -0.5 * CELL_PIXELS * (gridHeight + WRAP  - renderHeight/CELL_PIXELS);
    }


}