package editor;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Screen {
	private Pane root = new Pane();
	private int screenWidth;
	private int screenHeight;
	
	public Screen(int width,int height){	
		this.screenWidth = width;
		this.screenHeight = height;
		makeScreen();
	}	
	
	public Pane getRoot(){
		return root;
	}

	private void makeScreen(){				
		
		BackgroundFill backgroundColor = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundColor);
		
		root.setBackground(background);
		root.setPrefSize(screenWidth,screenHeight);
		root.setLayoutX(20);
		root.setLayoutY(20);	
	}

}
