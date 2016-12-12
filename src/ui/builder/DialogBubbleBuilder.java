package ui.builder;


import java.util.*;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DialogBubbleBuilder implements ComponentBuilder {
	private static final String imagePath = "/resources/images/dialog/";
	
	public DialogBubbleBuilder() {
	}
	

	public Node createComponent(ComponentProperties properties) {
		Group dialogBubble = new Group();
		Image rawImage = new Image(imagePath + "EmptyDialog.png");
		ImageView dialogImage = new ImageView(rawImage);
		dialogImage.setFitHeight(properties.height);
		dialogImage.setFitWidth(properties.width);
		Label textArea = new Label(properties.text);
		textArea.setWrapText(true);
		textArea.setMaxHeight(properties.height - (properties.height/6));
		textArea.setMaxWidth(properties.width - (properties.width/10));
		textArea.setLayoutX(properties.width/20);
		textArea.setLayoutY(properties.height/12);
		dialogBubble.getChildren().addAll(dialogImage, textArea);
		return dialogBubble;
	}
	
	public List<String> processText(String message) {
		List<String> messageList = new ArrayList<String>();
		String splitMessage = message;
		while (splitMessage.length() > 235) {
			messageList.add(splitMessage.substring(0, 235));
			splitMessage = splitMessage.substring(235);
		}
		messageList.add(splitMessage);
		return messageList;
	}
	
}
