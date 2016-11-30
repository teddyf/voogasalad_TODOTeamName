package ui.scenes.engine;

import javafx.scene.Group;
import grid.GridPaneRunnable;
import ui.GridPane;
import ui.GridPaneNode;
import ui.builder.UIBuilder;

public class GridDisplayer {
	private GridPaneRunnable myGridPaneRunnable;
//	private Group myParent;
//	private UIBuilder myUIBuilder;
	private Group myGroupGrid;
	
	public GridDisplayer(GridPane gridPane) {
		myGroupGrid = new Group();
		myGridPaneRunnable = new GridPaneRunnable(gridPane);
	}
	
	public void updateDisplay(int charPositionX, int charPositionY){
		GridPaneNode[][] displayGrid = myGridPaneRunnable.displayGrid(charPositionX, charPositionY);
		for (int i=0; i < displayGrid.length; i++) {
			Group rowGroup = new Group();
			for (int j=0; j<displayGrid[0].length; j++) {
				rowGroup.getChildren().add(displayGrid[i][j].getImage());
				System.out.println(displayGrid[i][j].getImageNum());
			}
			myGroupGrid.getChildren().add(rowGroup);
		}
	}
	
	public Group getGroupGrid(){
		return myGroupGrid;
	}
}
