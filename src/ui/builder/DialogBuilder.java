package ui.builder;


import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

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
	
	public FadeTransition fadeNode(Node node) {
		FadeTransition fade = new FadeTransition(Duration.millis(100), node);
		System.out.println("FADE");
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        return fade;
	}
	
}
