package battle.view;

import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * Created by Bill Xiong on 12/8/16.
 * 
 * @author Bill Xiong
 */
public class WinConditionView {
	private Label label;

	public WinConditionView(String condition) {
		label = new Label(condition);
	}

	protected void addToGroup(Group group) {
		group.getChildren().clear();
		group.getChildren().add(label);
	}
}
