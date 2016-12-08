package battle;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 * Created by Bill Xiong on 12/7/16. abstract class for views, such as itemView,
 * enemyview, etc.
 * 
 * @author Bill Xiong
 * 
 */
public abstract class ItemView {

	private Label itemHP;
	private Label name;
	// TODO change to ImageView
	private Rectangle itemView;
	private int hp;

	public ItemView(String name, int hp, int x, int y) {
		itemHP = new Label("HP: " + hp);
		itemView = new Rectangle(BattleView.RECTANGLE_WIDTH, BattleView.RECTANGLE_HEIGHT);
		itemView.setLayoutX(x);
		itemView.setLayoutY(y);

		this.name = new Label(name);
		this.name.setLayoutX(x + BattleView.OFFSET);
		this.name.setLayoutY(y + BattleView.OFFSET_Y);


		itemHP.setLayoutX(x + BattleView.OFFSET);
		itemHP.setLayoutY(y);
		this.hp = hp;
	}
	
	protected void addToGroup(Group root) {
		root.getChildren().add(itemHP);
		root.getChildren().add(itemView);
        root.getChildren().add(name);
	}

	protected void updateLoc(int x, int y) {
		itemView.setLayoutX(x);
		itemView.setLayoutY(y);
	}

	protected int getHP() {
		return hp;
	}

	protected Label getItemHP() {
		return itemHP;
	}

	protected Rectangle getView() {
		return itemView;
	}

	protected void setHP(int hp) {
		this.hp = hp;
		itemHP.setText("HP: " + hp);
	}

}
