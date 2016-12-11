package ui.builder;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DialogBuilder implements ComponentBuilder {
	private static final String imagePath = "/resources/images/dialog/";
	
	public DialogBuilder() {
	}
	

	public Node createComponent(ComponentProperties properties) {
		Group dialogBubble = new Group();
		Image rawImage = new Image(imagePath + "EmptyDialog.png");
		ImageView dialogImage = new ImageView(rawImage);
		dialogImage.setFitHeight(properties.height);
		dialogImage.setFitWidth(properties.width);
		Label textArea = new Label(properties.text);
		textArea.setWrapText(true);
		textArea.setMaxHeight(properties.height - (properties.height/12));
		textArea.setMaxWidth(properties.width - (properties.width/10));
		dialogBubble.getChildren().addAll(dialogImage, textArea);
		textArea.setLayoutX(properties.width/20);
		textArea.setLayoutY(properties.height/10);
		return dialogBubble;
	}
	
}
