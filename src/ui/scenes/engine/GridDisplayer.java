package ui.scenes.engine;

import player.PlayerDirection;
import javafx.scene.Group;
import grid.GridPaneRunnable;
import ui.GridPane;
import ui.GridPaneNode;

public class GridDisplayer {
	private final int CELL_SIZE = 10;
	private GridPaneRunnable myGridPaneRunnable;
	private Group myGroupGrid;
	
	public GridDisplayer(GridPane gridPane) {
		myGroupGrid = new Group();
		myGridPaneRunnable = new GridPaneRunnable(gridPane);
	}
	
//	public Group updateDisplay(int charPositionX, int charPositionY){
//		Group displayGroup = new Group();
//		GridPaneNode[][] displayGrid = myGridPaneRunnable.displayGrid(charPositionX, charPositionY);
//		for (int i=0; i < displayGrid.length; i++) {
//			Group rowGroup = new Group();
//			for (int j=0; j<displayGrid[0].length; j++) {
//				rowGroup.getChildren().add(displayGrid[i][j].getImage());
//				System.out.println(displayGrid[i][j].getImageNum());
//			}
//			displayGroup.getChildren().add(rowGroup);
//		}
//		return displayGroup;
//	}
	
	public void updateDisplay(Group gridRegion, PlayerDirection direction) {
		switch (direction) {
		case NORTH:
			gridRegion.setTranslateX(CELL_SIZE);
		case EAST:
		
		case WEST:
		
		case SOUTH:
		
		case NORTHWEST:
			
		case NORTHEAST:
			
		case SOUTHWEST:
			
		case SOUTHEAST:
			
		}
	}
	
	public Group getGroupGrid(){
		return myGroupGrid;
	}
}
