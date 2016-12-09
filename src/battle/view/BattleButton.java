package battle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

/**
 * Created by Bill Xiong 12/9/16.
 * @author Bill Xiong
 */
public class BattleButton {
    private Button button;
    private EventHandler<ActionEvent> event;
    public BattleButton(String text, int x, int y){
        button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutX(y);
    }
    public void addHandler(EventHandler<ActionEvent> event){
        this.event = event;
    }
    public void addToGroup(Group group){
        group.getChildren().add(button);
    }

}
