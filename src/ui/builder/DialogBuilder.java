package ui.builder;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.text.TextAlignment;

public class DialogBuilder extends ComponentBuilder {
	private static final String imagePath = "/resources/images/dialog/";
	public DialogBuilder() {
		super();
	}

	@Override
	public Node createComponent(ComponentProperties properties) {
		Group dialogBubble = new Group();
		dialogBubble.setId("effme");
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
		dialogBubble.getChildren().addAll(dialogImage, textArea);
		textArea.setLayoutX(properties.width/10);
		return dialogBubble;
	}
	
}
