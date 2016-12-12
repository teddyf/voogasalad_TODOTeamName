package grid;

import ui.EditorGrid;
import ui.GridPaneNode;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GridPaneRunnable {
	private final int GRID_WIDTH = 15;
    private final int GRID_HEIGHT = 15;
    private EditorGrid myGrid;
    
    public GridPaneRunnable(EditorGrid grid) {
    	myGrid = grid;
    }
    
//    throw error if outta bounds?
//	although I got rid of the imageView mouse effects, there should be cleaner way to do this
    public GridPaneNode[][] displayGrid(int charX, int charY){
    	GridPaneNode[][] gridDisplay = new GridPaneNode[GRID_HEIGHT][GRID_WIDTH];
    	for (int i=0; i <GRID_HEIGHT; i++) {
    		for (int j=0; j<GRID_WIDTH; j++) {
    			for (GridPaneNode currNode: myGrid.getNodeList()) {
    				if (currNode.getRow() == charX - GRID_HEIGHT/2 + i && currNode.getCol() == charY - GRID_WIDTH/2 +j) {
    					currNode.getImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
    			            @Override
    			            public void handle(MouseEvent event) {
    			                currNode.getImage().setEffect(null);
    			            }
						});
    					currNode.getImage().setOnMouseEntered(new EventHandler<MouseEvent>() {
        			            @Override
        			            public void handle(MouseEvent event) {
        			                currNode.getImage().setEffect(null);
        			            }
						});
    					gridDisplay[i][j] = currNode;
    				}
    			}
    		}
    	}
    	return gridDisplay;
    }
    
    
}
