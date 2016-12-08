package battle;

import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * Created by Bill Xiong on 12/8/16.
 * @author Bill Xiong
 */
public class WinCondition {
    private Label label;
    public WinCondition(String condition){
        label = new Label(condition);
    }
    protected void addWinCondition(Group group){
        group.getChildren().clear();
        group.getChildren().add(label);
    }
}
