package ui.scenes.engine;

import java.util.*;

import block.BlockType;
import ui.GridObjectMap;
import ui.GridPaneNode;
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
public class GridForEngine {

    private final int WRAP = 20;
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
    private static final String wall= "resources/images/tiles/obstacle/tree-4.png";

    public GridForEngine (int gridWidth, int gridHeight, int renderWidth,
                     int renderHeight, int renderTopLeftX, int renderTopLeftY) {

        group = new Group();
        blockList = new ArrayList<GridPaneNode>();
        clicked = new ArrayList<GridPaneNode>();

        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.3);

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
        //offset = 0;
        return column * CELL_PIXELS + renderTopLeftX + offset;
    }

    private double getYRender (int row) {
        double offset = -0.5 * CELL_PIXELS * (gridHeight + WRAP  - renderHeight/CELL_PIXELS);
        //offset = 0;
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
            /*if (node.getCol() >= WRAP / 2
                    && node.getCol() < gridWidth + WRAP / 2
                    && node.getRow() >= WRAP / 2
                    && node.getRow() < gridHeight + WRAP / 2) {
//                makeClickable(node);
            }
            else {
                node.getImage().setEffect(hoverOpacity);
                group.getChildren().add(node.getImage());
                grid[node.getCol()][node.getRow()] = node;
            }*/

            group.getChildren().add(node.getImage());
            grid[node.getRow()][node.getCol()] = node;
        }
    }

    public void loadReset (double height, double width) {

        this.gridWidth = width+WRAP;
        this.gridHeight = height+WRAP;

        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        grid = new GridPaneNode[(int) gridHeight][(int) gridWidth];
    }

    /**
     * Converts backend block to front end grid
     *
     * @param row
     * @param col
     * @param name
     */
    public void blockToGridPane (int row, int col, String name) {
        GridPaneNode temp = new GridPaneNode(row+WRAP/2, col+WRAP/2, name);
        blockList.add(temp);
    }
    
    public void populateBorder(){
        for(int i = 0; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){
                if((i<WRAP/2 || j<WRAP/2) || (i>=(gridWidth-WRAP/2) || j>=(gridHeight-WRAP/2))){
                    GridPaneNode temp = new GridPaneNode(j,i,wall);
                    blockList.add(temp);
                }
            }
        }
    }

    public Group getGroup () {
        return group;
    }

    public double getBlockSize () {
        return CELL_PIXELS;
    }

    public double getWidth () {
        return gridWidth;
    }

    public double getHeight () {
        return gridHeight;
    }

    public boolean reRender(int col, int row, String newPath) {
        GridPaneNode newGPN = new GridPaneNode(row, col, newPath);
        GridPaneNode temp = grid[row + WRAP/2][col + WRAP/2];
                temp.swap(newGPN, 0);
        blockList.set(row*col+row, newGPN);
        ArrayList<GridPaneNode> list = new ArrayList<GridPaneNode>();
        list.add(temp);
        gridMap.storeObject(list);
        clicked = new ArrayList<GridPaneNode>();
        return true;
    }

}