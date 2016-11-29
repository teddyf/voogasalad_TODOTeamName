package grid;

import java.util.*;

import block.Block;
import ui.GridPaneNode;
import javafx.scene.Group;

public class GridPaneRunnable {
	private final int GRID_WIDTH = 15;
    private final int GRID_HEIGHT = 15;
    private Group group;
    private Grid myGrid;
    
    public GridPaneRunnable(Grid grid) {
    	myGrid = grid;
    }
    
//    throw error if outta bounds?
    public GridPaneNode[][] displayGrid(int charX, int charY){
    	GridPaneNode[][] gridDisplay = new GridPaneNode[GRID_HEIGHT][GRID_WIDTH];
    	for (int i=charX-GRID_HEIGHT/2; i <charX+GRID_HEIGHT/2; i++) {
    		for (int j=charY-GRID_WIDTH/2; j<charY+GRID_WIDTH/2; j++) {
    			Block currBlock = myGrid.getBlock(i, j);
    			GridPaneNode currGridNode = new GridPaneNode(i, j, currBlock.getName());
    			gridDisplay[i][j] = currGridNode;
    		}
    	}
    	return gridDisplay;
    }
    
    
}
