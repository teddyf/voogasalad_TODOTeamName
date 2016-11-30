package ui;

import java.util.*;
import ObjectMenuObjects.GameObjects;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import ui.GridPaneNode;
import block.*;
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
    private Map<Double, Map<Double, GridPaneNode>> renderMap;

    private double gridWidth;
    private double gridHeight;
    private double renderWidth;
    private double renderHeight;
    private int renderTopLeftX;
    private int renderTopLeftY;
    private ColorAdjust hoverOpacity;
    private ColorAdjust highlight;

    private String DEFAULT = "DEFAULT.1.2.3";

    public GridPane (int gridWidth,
                     int gridHeight,
                     int renderWidth,
                     int renderHeight,
                     int renderTopLeftX,
                     int renderTopLeftY) {
        this.group = new Group();
        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.1);
        highlight = new ColorAdjust();
        highlight.setSaturation(-.3);
        this.renderTopLeftX = renderTopLeftX;
        this.renderTopLeftY = renderTopLeftY;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        this.blockList = new ArrayList<GridPaneNode>();
        this.clicked = new ArrayList<GridPaneNode>();
        this.renderMap = new HashMap<Double, Map<Double, GridPaneNode>>();
        initializeGrid();
        setRenderMap();
    }

    private double getXRender (int a) {
        double cellWidth = 0.0 + renderWidth / gridWidth;
        int sol = a;
        return sol * cellWidth + renderTopLeftX;
    }

    private double getYRender (int a) {
        double cellHeight = 0.0 + renderHeight / gridHeight;
        int sol = a;
        return sol * cellHeight + renderTopLeftY;
    }

    private void initializeGrid () {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                GridPaneNode node = new GridPaneNode(i, j, DEFAULT);
                blockList.add(node);
            }
        }
    }

    public void setRenderMap () {
        for (int i = 0; i < blockList.size(); i++) {

            GridPaneNode node = blockList.get(i);
            double x = getXRender(node.getCol());
            double y = getYRender(node.getRow());
            node.setImageSize(0.0 + renderWidth / gridWidth, 0.0 + renderHeight / gridHeight);
            node.setImageCoord(x, y);
            
            node.getImage().setOnMouseExited(e -> {if(!clicked.contains(node))node.getImage().setEffect(null);});
            node.getImage().setOnMouseEntered(e -> {node.getImage().setEffect(hoverOpacity);});
            
            node.getImage().setOnMouseClicked(e -> {
                node.getImage().setEffect(hoverOpacity);
                click(node);
            });


            

            group.getChildren().add(node.getImage());
            if (renderMap.containsKey(x)) {
                renderMap.get(x).put(y, node);
            }
            else {
                renderMap.put(x, new HashMap<Double, GridPaneNode>());
                renderMap.get(x).put(y, node);
            }
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
        this.renderMap = new HashMap<Double, Map<Double, GridPaneNode>>();
        initializeGrid();
        setRenderMap();
    }

    public void swap (GameObjects obj, EditorController control) {
        List<GridPaneNode> list = obj.getList();
        getObjectNeighbors(list);
        for (int i = 0; i < clicked.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int xPos = clicked.get(i).getCol() + list.get(j).getCol();
                int yPos = clicked.get(i).getRow() + list.get(j).getRow();
                GridPaneNode temp = renderMap.get(getXRender(xPos)).get(getYRender(yPos));
                temp.swap(list.get(j), list.get(j).getImageNum());
                control.addBlock(temp.getName(), obj.getBlockType(), temp.getRow(), temp.getCol());
            }
            clicked.get(i).getImage().setEffect(null);
        }
        clicked = new ArrayList<GridPaneNode>();
        
    }

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

    public List<GridPaneNode> getNodeList () {
        return blockList;
    }

    public void setNodes (List<GridPaneNode> list) {
        this.blockList = list;
    }

    public Map<Double, Map<Double, GridPaneNode>> getRenderMap () {
        return renderMap;
    }

    public Group getGroup () {
        return group;
    }

    public List<GridPaneNode> getClicked () {
        return clicked;
    }

}
