package grid;

import java.util.*;
import javafx.scene.Group;

public class GridPaneRunnable {
	private Group group;
	private double myGridWidth;
    private double myGridHeight;
    private List<GridPaneNode> myBlockList;
    
    public GridPaneRunnable(int gridHeight, int gridWidth, List<GridPaneNode> blockList) {
    	myGridHeight = gridHeight;
    	myGridWidth = gridWidth;
    	myBlockList = blockList;
    }
    
    public GridPaneNode[][] displayGrid(int charX, int charY){
    	for (int i=-1*(myGridHeight-(myGridHeight%2))/2; i <myGridHeight/2; i++) {
    		for (int j=0; j<myGridWidth; j++) {
    			int posX = charX
    		}
    	}
    }
    
    
}
