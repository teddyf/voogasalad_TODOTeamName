package ui.scenes;

import player.PlayerDirection;
import javafx.scene.Group;
import ui.GridPane;
import ui.GridPaneNode;

public class GridDisplayer {
	private double CELL_SIZE = 10;
	//private GridPaneRunnable myGridPaneRunnable;
	//private Group myGroupGrid;
	
	public GridDisplayer(GridPane gridPane) {
		//myGroupGrid = gridPane.getGroup();
		//myGridPaneRunnable = new GridPaneRunnable(gridPane);
		CELL_SIZE = gridPane.getBlockSize();
	}
	
	public void updateDisplay(Group gridRegion, PlayerDirection direction) {
		switch (direction) {
		case NORTH:
			gridRegion.setLayoutY(gridRegion.getLayoutY()-CELL_SIZE);
			break;
		case EAST:
			gridRegion.setTranslateX(gridRegion.getTranslateX()-CELL_SIZE);
			break;
		case WEST:
			gridRegion.setTranslateX(gridRegion.getTranslateX()+CELL_SIZE);
			break;
		case SOUTH:
			gridRegion.setLayoutY(gridRegion.getLayoutY()+CELL_SIZE);
			break;
		case NORTHWEST:
			gridRegion.setTranslateY(gridRegion.getTranslateY()-CELL_SIZE);
			gridRegion.setTranslateX(gridRegion.getTranslateX()-CELL_SIZE);
			break;
		case NORTHEAST:
			gridRegion.setTranslateY(gridRegion.getTranslateY()-CELL_SIZE);
			gridRegion.setTranslateX(gridRegion.getTranslateX()-CELL_SIZE);
			break;
		case SOUTHWEST:
			gridRegion.setTranslateY(gridRegion.getTranslateY()+CELL_SIZE);
			gridRegion.setTranslateX(gridRegion.getTranslateX()+CELL_SIZE);
			break;
		case SOUTHEAST:
			gridRegion.setTranslateY(CELL_SIZE);
			gridRegion.setTranslateX(-CELL_SIZE);
			break;
		}
	}
	
//	public Group getGroupGrid(){
//		return myGroupGrid;
//	}
}