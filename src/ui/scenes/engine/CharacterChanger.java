package ui.scenes.engine;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/*
 * 
 * This class is to create menu for users to change player pic in engine
 * @author pim chuaylua
 * */

public class CharacterChanger {
	
	private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
	private ResourceBundle myResources;
	private Group group;
	private VBox vbox;
	private FlowPane flowPane;
	private Character player;
	
	CharacterChanger(Character player) {
		this.player=player;
		init();
	}
	
	private void init() {
		group = new Group();
		vbox = new VBox();
		myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
		initFlowPane();
		addPlayerOptions();
	}
	
	private void initFlowPane() {
		flowPane = new FlowPane();
		flowPane = new FlowPane();
		flowPane.setPrefWrapLength(300);
		flowPane.setHgap(20);
		flowPane.setVgap(20);
		flowPane.setPadding(new Insets(20, 20, 20, 20));
		vbox.getChildren().add(new Label("Change player"));
		vbox.getChildren().add(flowPane);
		group.getChildren().add(vbox);
	}
	
	private void addPlayerOptions() {
		
		//hard code for testing
		String path1 = "resources/images/tiles/Character/Pokemon/Player1SouthFacing.png";
		Button button1 = new Button();
		button1.setGraphic(new ImageView(new Image(path1)));
		button1.setOnMouseClicked(e -> player.changeCharacterImage(path1));
		
		String path2 = "resources/images/tiles/Character/Pokemon/Player2SouthFacing.png";
		Button button2 = new Button();
		button2.setGraphic(new ImageView(new Image(path2)));
		button2.setOnMouseClicked(e -> player.changeCharacterImage(path2));
		flowPane.getChildren().addAll(button1,button2);
	}

	public Group getGroup() {
		return group;
	}
	
	
	
	

}
