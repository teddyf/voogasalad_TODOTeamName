package battle;

import editor.backend.Battle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 * Created by Bill Xiong on 12/7/16.
 * abstract class for views, such as itemView, enemyview, etc.
 * @author Bill Xiong
 * 
 */
public abstract class ItemView {
    private Label itemHP;
    //TODO change to ImageView
    private Rectangle itemView;

    public ItemView(int hp, int x, int y){
        itemHP = new Label("HP: " + hp);
        itemView = new Rectangle(BattleView.RECTANGLE_WIDTH, BattleView.RECTANGLE_HEIGHT);
        itemView.setLayoutX(x);
        itemView.setLayoutY(y);
        itemHP.setLayoutX(x + BattleView.OFFSET);
        itemHP.setLayoutY(y);
    }
    protected void updateLoc(int x, int y){
        itemView.setLayoutX(x);
        itemView.setLayoutY(y);
    }
    protected void addItems(Group group){
        group.getChildren().add(itemView);
        group.getChildren().add(itemHP);
    }
    protected Label getHP(){
        return itemHP;
    }
    protected Rectangle getView(){
        return itemView;
    }
    protected void setHP(int hp){
        itemHP.setText("HP: " + hp);
    }

}
