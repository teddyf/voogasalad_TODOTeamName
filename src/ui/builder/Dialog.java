package ui.builder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class Dialog extends Group {
	
	private static final String imagePath = "/resources/images/dialog/";
	
	public Dialog(ComponentProperties properties) {
		setId("effme");
		Image rawImage = new Image(imagePath + "EmptyDialog.png");
		ImageView dialogImage = new ImageView(rawImage);
//		dialogImage.setLayoutX(properties.x);
//		dialogImage.setLayoutY(properties.y);
		dialogImage.setFitHeight(properties.height);
		dialogImage.setFitWidth(properties.width);
		Label textArea = new Label(properties.text);
		textArea.setWrapText(true);
		textArea.setTextAlignment(TextAlignment.JUSTIFY);
		textArea.setMaxHeight(properties.height - (properties.height/12));
		textArea.setMaxWidth(properties.width - (properties.width/12));
		getChildren().addAll(dialogImage, textArea);
		textArea.setLayoutX(properties.width/10);
	}

	

}
