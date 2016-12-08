package ui.scenes.engine;

import java.util.*;
import ui.scenes.editor.objects.GameObject;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import ui.GridPaneNode;
import editor.EditorController;

/**
 * Will replace this class with a better grid for engine
 * @author Teddy Franceschi
 *
 */
public class GridForEngine {
    // name & identifier & row/column

    private Group group;
    private List<GridPaneNode> blockList;
    private List<GridPaneNode> clicked;
    private GridPaneNode[][] grid;
    private Map<Double, Map<Double, GridPaneNode>> renderMap;

    private double gridWidth;
    private double gridHeight;
    private double renderWidth;
    private double renderHeight;
    private int renderTopLeftX;
    private int renderTopLeftY;
    private ColorAdjust hoverOpacity;
    private ColorAdjust highlight;

    private String DEFAULT = "resources/Default.png";

    public GridForEngine (int gridWidth,
                     int gridHeight,
                     int renderWidth,
                     int renderHeight,
                     int renderTopLeftX,
                     int renderTopLeftY) {
        this.group = new Group();
        
        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);
        this.renderTopLeftX = renderTopLeftX;
        this.renderTopLeftY = renderTopLeftY;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        initializeGrid();
        setRenderMap();
    }

    private double getXRender (int a) {
        double cellWidth = renderWidth / gridWidth;
        int sol = a;
        return sol * cellWidth + renderTopLeftX;
    }

    private double getYRender (int a) {
        double cellHeight = 0.0 + renderHeight / gridHeight;
        int sol = a;
        return sol * cellHeight + renderTopLeftY;
    }

    private void initializeGrid () {
        grid = new GridPaneNode[(int)gridHeight][(int)gridWidth];
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                GridPaneNode node = new GridPaneNode(i, j, DEFAULT);
                blockList.add(node);
                grid[j][i] = node;
            }
        }
    }

    public void setRenderMap () {
        group = new Group();
        ArrayList<Double> data = new ArrayList<Double>();
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode node = blockList.get(i);
            double x = getXRender(node.getCol());
            double y = getYRender(node.getRow());
            node.setImageSize(renderWidth / gridWidth, renderHeight / gridHeight);
            node.setImageCoord(x, y);
            group.getChildren().add(node.getImage());
            grid[node.getCol()][node.getRow()] = node;
            data.add(x);
            data.add(y);
        }
    }

    public void resizeReset (double x, double y) {
        this.gridWidth = x;
        this.gridHeight = y;
        reset();

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
    
    public void loadReset(int height, int width){
        
        this.gridWidth = width;
        this.gridHeight = height;
        
        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        this.grid = new GridPaneNode[height][width];
    }

    public List<GridPaneNode> swap (GameObject obj, EditorController control) {
        List<GridPaneNode> list = obj.getImageTiles();
        List<GridPaneNode> copy = new ArrayList<GridPaneNode>();
        getObjectNeighbors(list);
        System.out.println(blockList);
        for (int i = 0; i < clicked.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                GridPaneNode temp = grid[xPos][yPos];
                temp.swap(list.get(j), list.get(j).getImageNum());
                control.addBlock(temp.getName(), obj.getBlockType(), temp.getRow(), temp.getCol());
            }
            clicked.get(i).getImage().setEffect(null);
            copy = clicked;
        }
        clicked = new ArrayList<GridPaneNode>();
        System.out.println(copy);
        return copy;     
    }
    
    /**
     * Gets neighbors if object is placed
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
     * Removes neighbors in clicked if object would contain both
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
     * @param row
     * @param col
     * @param name
     */
    public void blockToGridPane(int row, int col,String name){
        GridPaneNode temp = new GridPaneNode(row,col,name);
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
    
    public double getGridHeight(){
        return gridHeight;
    }
    
    public double getGridWidth(){
        return gridWidth;
    }
    
    public double getBlockSize() {
        return renderWidth/gridWidth;
    }
    
    public double getWidth() {
        return gridWidth;
    }
    
    public double getHeight() {
        return gridHeight;
    }
    
    public void setTopX(int topX) {
    	renderTopLeftX=topX;
    }
    
    public void setTopY(int topY) {
    	renderTopLeftY=topY;
    }

}