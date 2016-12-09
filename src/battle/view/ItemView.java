package battle.view;

import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Created by Bill Xiong on 12/7/16. abstract class for views, such as itemView,
 * enemyview, etc.
 * 
 * @author Bill Xiong
 * 
 */
public abstract class ItemView {

	protected Group root;
	private Label itemHP;
	private Label name;
	// TODO change to ImageView
	private ImageView itemView;
	private ImageView shadowImageView;
	private int hp;
	private final int size = 150;
	public ItemView(String name, int hp, int x, int y, String filePath) {
		//itemHP = new Label("HP: " + hp);
		//set image
		root = new Group();
		setImageView(filePath, size,x,y);
		/*
		//name
		this.name = new Label(name);
		this.name.setLayoutX(x + BattleView.OFFSET);
		this.name.setLayoutY(y + BattleView.OFFSET_Y);

		//temHP
		itemHP.setLayoutX(x + BattleView.OFFSET);
		itemHP.setLayoutY(y);*/
		this.hp = hp;
	}
	
	protected void setImageView(String filePath, int size,int x, int y) {
		Image image = new Image(filePath);
        itemView = new ImageView();
        itemView.setImage(image);
        itemView.setFitWidth(size);
        itemView.setFitHeight(size);
        setLocation(itemView,x,y);
        
        Image shadowImage = new Image("resources/images/battles/shadow.png");
        shadowImageView = new ImageView();
        shadowImageView.setImage(shadowImage);
        shadowImageView.setFitWidth(size*2);
        shadowImageView.setFitHeight(size*2);
        setLocation(shadowImageView,x-size/2,y); 
        
        root.getChildren().addAll(itemView,shadowImageView);
	}

	protected void setLocation(ImageView imageView,int x, int y) {
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
	}
	
	public Group getGroup() {
		return root;
	}

	protected int getHP() {
		return hp;
	}

	protected Label getItemHP() {
		return itemHP;
	}

	protected ImageView getView() {
		return itemView;
	}
	
	public void updateHealthView() {
		
	}

	protected void setHP(int hp) {
		this.hp = hp;
		//itemHP.setText("HP: " + hp);
		//System.out.println("hp changed!");
	}

}
