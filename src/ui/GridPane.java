package ui;

import java.util.*;

import block.BlockType;
import ui.scenes.editor.objects.GameObjects;
import ui.scenes.editor.objects.Player1;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import editor.EditorController;

/**
 * 
 * @author Teddy Franceschi
 *
 */
public class GridPane {
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

    public GridPane (int gridWidth,
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
        for (int i = 0; i < blockList.size(); i++) {
            GridPaneNode node = blockList.get(i);
            double x = getXRender(node.getCol());
            double y = getYRender(node.getRow());
            node.setImageSize(renderWidth / gridWidth, renderHeight / gridHeight);
            node.setImageCoord(x, y);
            node.getImage().setOnMouseExited(e -> {if(!clicked.contains(node))node.getImage().setEffect(null);});
            node.getImage().setOnMouseEntered(e -> {node.getImage().setEffect(hoverOpacity);});           
            node.getImage().setOnMouseClicked(e -> {
                node.getImage().setEffect(hoverOpacity);
                click(node);
            });
            //System.out.println("col: " + node.getCol());
            //System.out.println("row: " + node.getRow());
            //System.out.println("length: " + grid.length);
            group.getChildren().add(node.getImage());
            grid[node.getCol()][node.getRow()] = node;
        }
        //System.out.println("grid status");
        /*
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.println(grid[i][j]);
            }
        }
        */
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
    
    public void loadReset(double height, double width){
        
        this.gridWidth = width;
        this.gridHeight = height;
        
        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        grid = new GridPaneNode[(int)height][(int)width];
    }

    public List<GridPaneNode> swap (GameObjects obj, EditorController control) {
        List<GridPaneNode> list = obj.getList();
        List<GridPaneNode> copy = new ArrayList<GridPaneNode>();
        getObjectNeighbors(list);
        for (int i = 0; i < clicked.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                GridPaneNode temp = grid[xPos][yPos];
                System.out.println(temp);
                temp.swap(list.get(j), list.get(j).getImageNum());
                control.addBlock(temp.getName(), obj.getBlockType(), temp.getRow(), temp.getCol());
                setPlayer(temp,obj,control);
            }
            clicked.get(i).getImage().setEffect(null);
            copy = clicked;
        }
        
        clicked = new ArrayList<GridPaneNode>();
        return copy;     
    }
    
    private void setPlayer(GridPaneNode temp,GameObjects gameObject, EditorController control) {
    	if (gameObject instanceof Player1) {
        	control.addPlayer(temp.getName(),temp.getRow(), temp.getCol());
        	control.addBlock("resources/Default.png", BlockType.DECORATION, temp.getRow(), temp.getCol());   
    	}
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
    
    public double getBlockSize() {
    	return renderWidth/gridWidth;
    }
    
    public double getWidth() {
    	return gridWidth;
    }
    
    public double getHeight() {
    	return gridHeight;
    }
    
    public void debug(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.println(grid[i][j]);
            }
        }
    }

}
