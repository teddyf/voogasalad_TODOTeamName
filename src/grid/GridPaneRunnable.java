package grid;

import java.util.*;

import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Group;

public class GridPaneRunnable {
	private final int GRID_WIDTH = 15;
    private final int GRID_HEIGHT = 15;
    private Group group;
    private GridPane myGrid;
    
    public GridPaneRunnable(GridPane grid) {
    	myGrid = grid;
//    	for (GridPaneNode g : grid.getNodeList()) {
//    		System.out.println(g.getImageNum());
//    	}
    }
    
//    throw error if outta bounds?
    public GridPaneNode[][] displayGrid(int charX, int charY){
    	GridPaneNode[][] gridDisplay = new GridPaneNode[GRID_HEIGHT][GRID_WIDTH];
    	for (int i=0; i <GRID_HEIGHT; i++) {
    		for (int j=0; j<GRID_WIDTH; j++) {
    			for (GridPaneNode currNode: myGrid.getNodeList()) {
    				if (currNode.getRow() == charX - GRID_HEIGHT/2 + i && currNode.getCol() == charY - GRID_WIDTH/2 +j) {
//    					System.out.println("Row"+currNode.getRow());
//    					System.out.println("COL" + currNode.getCol());
    					gridDisplay[i][j] = currNode;
    				}
    			}
    		}
    	}
    	return gridDisplay;
    }
    
    
}
