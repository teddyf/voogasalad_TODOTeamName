package battle;


import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthDisplay {
	
	private Group root;
	private ItemView itemView;
	private Button playerChart;
	private int x;
	private int y;
	private Label score;
	
	HealthDisplay(int x, int y) {
		initHealthBar(x,y);
		this.x=x;
		this.y=y;
	}
	
	private void initHealthBar(int x,int y) {
		root = new Group();
		Image image = new Image("resources/images/battles/square.png");
        ImageView squareView = new ImageView();
        squareView.setFitWidth(200);
        squareView.setFitHeight(40);
        squareView.setImage(image);
        squareView.setLayoutX(x);
        squareView.setLayoutY(y);
        
        playerChart = new Button();
        playerChart.getStyleClass().add("playerChart");
        playerChart.setPrefSize(100,5);
        playerChart.setLayoutX(x+10);
        playerChart.setLayoutY(y+10);
        
        score = new Label("HP: "+100);
        score.setLayoutX(x+150);
		score.setLayoutY(y+10);
        
        root.getChildren().addAll(squareView,playerChart,score);
	}

	public Group getGroup() {
		return root;
	}
	
	public void update(ItemView itemView) {
		playerChart.setPrefSize(itemView.getHP(),5);
		score.setText("HP: "+itemView.getHP());
	}
	
}
