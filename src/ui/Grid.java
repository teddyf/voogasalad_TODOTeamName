package ui;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Harshil Garg
 */
public class Grid extends Observable {

    protected int gridWidth;
    protected int gridHeight;
    protected int renderWidth;
    protected int renderHeight;

    protected Group group;

    protected List<GridPaneNode> blockList;
    protected List<GridPaneNode> clicked;

    public Grid(int gridWidth, int gridHeight, int renderWidth, int renderHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;

        group = new Group();
        blockList = new ArrayList<GridPaneNode>();
        clicked = new ArrayList<GridPaneNode>();
    }

}
