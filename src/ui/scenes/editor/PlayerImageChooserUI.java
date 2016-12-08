package ui.scenes.editor;

import java.util.Observable;

import editor.EditorController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class PlayerImageChooserUI extends Observable {
	private Group group;
	private String filePath;
	private FlowPane flowPane;
	
	PlayerImageChooserUI(PlayerMenuUI playerMenuUI) {
		group = new Group();
		flowPane = new FlowPane();
		customizeFlowPane();
		addPlayerOptions();
		this.addObserver(playerMenuUI);
	}
	
	private void customizeFlowPane() {
		flowPane.setPrefWrapLength(300);
		flowPane.setHgap(20);
		flowPane.setVgap(20);
		flowPane.setPadding(new Insets(20, 20, 20, 20));
	}
	
	private void addPlayerOptions() {
		
		//add player option 1
		String path1 = "resources/images/tiles/sprites/player-1-west-facing.png";
		Button button1 = new Button();
		button1.setGraphic(new ImageView(new Image(path1)));
		button1.setOnMouseClicked(e -> setPlayerImageFilePath(path1));
		
		//add player option 2
		String path2 = "resources/images/tiles/sprites/player-1-west-facing.png";
		Button button2 = new Button();
		button2.setGraphic(new ImageView(new Image(path2)));
		button2.setOnMouseClicked(e -> setPlayerImageFilePath(path2));
		flowPane.getChildren().addAll(button1,button2);
		group.getChildren().add(flowPane);
	}
	
	private void setPlayerImageFilePath(String path) {
		filePath=path;
		setChanged();
    	notifyObservers();
	}
	
	public String getPlayerFilePath() {
		return filePath;
	}
	
	public Group getGroup() {
		return group;
	}

}
