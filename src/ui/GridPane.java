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

    private String DEFAULT = "resources/images/tiles/decorations/grass-";

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
    
    private String defaultText(){
        int suffix = randomNumber(1,4);
        return DEFAULT+suffix+".png";
    }
    
    private int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
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
            node.setImageSize(renderWidth / gridWidth, renderHeight / gridHeight);
            node.setImageCoord(x, y);
            makeClickable(node);
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
    
    public void resize(){
        grid = new GridPaneNode[(int)gridHeight][(int)gridWidth];
        System.out.println(blockList.size());
        for(int i = 0; i < blockList.size(); i++){
            GridPaneNode temp = blockList.get(i);
            temp.setImageSize(renderWidth/gridWidth, renderHeight/gridHeight);
            //System.out.println(getXRender(temp.getCol()) + "," + temp.getRow());
            temp.setImageCoord(getXRender(temp.getCol()), getYRender(temp.getRow()));
            blockList.set(i, temp);
            grid[temp.getRow()][temp.getCol()] = temp;
        }
        group = new Group();
        for(int i = 0; i < blockList.size(); i++){
            group.getChildren().add(blockList.get(i).getImage());
        }
    }

    private void resizeResetLess(double x, double y){
        int count = 0;
        System.out.println(blockList);
        for(int i = 0; i < blockList.size(); i++){
            GridPaneNode temp = blockList.get(i);
            //System.out.println(temp);
            if(temp.getCol() >= x || temp.getRow() >= y){
                //System.out.println("removed");
                System.out.println(temp.getCol() + "," + temp.getRow());
                blockList.remove(i);
                i--;
            }
            count++;
        }
        //System.out.println(count);
        //System.out.println("ney");
        //System.out.println(blockList.size());
        gridWidth = x;
        gridHeight = y;
        System.out.println(blockList);
        resize();
    }
    
    private void resizeResetMore (double x, double y) {
        int count = 0;
        System.out.println(x-gridWidth);
        System.out.println(x-gridHeight);
        for(int i = (int) gridWidth; i < x; i++){
            for(int j = 0; j < y; j++){
                count++;
                GridPaneNode node = new GridPaneNode(i, j, defaultText());
                makeClickable(node);
                blockList.add(node);
                
            }
        }
        
        for(int i = 0; i < x; i++){
            for(int j = (int)gridHeight; j < y; j++){
                count++;
                GridPaneNode node = new GridPaneNode(i, j, defaultText());
                makeClickable(node);
                blockList.add(node);
                //System.out.println(count);
            }
        }
        
        gridWidth = x;
        gridHeight = y;

        resize();
    }
    
    public void resizeReset(double x, double y){
        if(gridHeight-y<0 || gridWidth-x<0){
            resizeResetMore(x,y);
        }
        else if(gridHeight-y>0||gridWidth-x>0){
            resizeResetLess(x,y);
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
    
    public void loadReset(double height, double width){
        
        this.gridWidth = width;
        this.gridHeight = height;
        
        this.group = new Group();
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        grid = new GridPaneNode[(int)height][(int)width];
    }

    public List<GridPaneNode> swap (GameObject obj, EditorController control) {
        List<GridPaneNode> list = obj.getImageTiles();
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
    
    private void setPlayer(GridPaneNode temp, GameObject gameObject, EditorController control) {
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
    
    public boolean buildLink(GridPaneNode node1, GridPaneNode node2, EditorController controller){
        return controller.linkBlocks(node1.getRow(), node1.getCol(), node2.getRow(), node2.getCol(),0,0);
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
    
    public void makeClickable(GridPaneNode node){
        node.getImage().setOnMouseExited(e -> {if(!clicked.contains(node))node.getImage().setEffect(null);});
        node.getImage().setOnMouseEntered(e -> {node.getImage().setEffect(hoverOpacity);});           
        node.getImage().setOnMouseClicked(e -> {
            node.getImage().setEffect(hoverOpacity);
            click(node);
        });
    }

}
