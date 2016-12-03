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
	
	public void updateDisplay(Group gridRegion, PlayerDirection direction) {
		switch (direction) {
			case NORTH:
				gridRegion.setTranslateY(-CELL_SIZE);
				break;
			case EAST:
				gridRegion.setTranslateX(-CELL_SIZE);
				break;
			case WEST:
				gridRegion.setTranslateX(CELL_SIZE);
				break;
			case SOUTH:
				gridRegion.setTranslateY(CELL_SIZE);
				break;
		}
	}
	
	public Group getGroupGrid(){
		return myGroupGrid;
	}
}
