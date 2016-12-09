package ui.builder;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

public class DialogBuilder extends ComponentBuilder {
	private static final String imagePath = "/resources/images/dialog/";
	public DialogBuilder() {
		super();
	}

	@Override
	public Node createComponent(ComponentProperties properties) {
		Group dialogBubble = new Group();
		Image rawImage = new Image(imagePath + "EmptyDialog.png");
		ImageView dialogImage = new ImageView(rawImage);
//		dialogImage.setLayoutX(properties.x);
//		dialogImage.setLayoutY(properties.y);
		dialogImage.setFitHeight(properties.height);
		dialogImage.setFitWidth(properties.width);
		TextArea textArea = new TextArea(properties.text);
		textArea.setBackground(Background.EMPTY);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxHeight(properties.height - (properties.height/12));
		textArea.setMaxWidth(properties.width - (properties.width/12));
		dialogBubble.getChildren().addAll(dialogImage, textArea);
		return dialogBubble;
	}
	
}
