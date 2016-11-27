package editor;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Decorations {
	private VBox decorationsVBox = new VBox();
	private ArrayList<String> decorationsList = new ArrayList<String>();
	
	public Decorations() {
		initDecorationsList();
		initDecorationsVBox();
	}
	
	private void initDecorationsList() {
		decorationsList.add("resources/flower.png");
		decorationsList.add("resources/dirt.png");
	}
	
	private void initDecorationsVBox() {
		decorationsVBox.setPadding(new Insets(20, 20, 20, 20));
		decorationsVBox.setSpacing(30);
		
		for (String each:decorationsList) {
			Image eachImage = new Image(each);
			ImageView eachView = new ImageView();
			eachView.setImage(eachImage);
			eachView.setFitHeight(100);
			eachView.setFitWidth(100);
			Button eachButton = new Button();
			eachButton.setGraphic(eachView);
			eachButton.setOnAction((event) -> {
			    System.out.println(each);
			    //when clicked should change the selected block to this image
			    //add action here

			});
			decorationsVBox.getChildren().addAll(eachButton);
		}
	}
	
	//get vbox and just add this to scrollpane on the sidepanel
	public VBox getDecorationsVBox() {
		return decorationsVBox;
	}

}
