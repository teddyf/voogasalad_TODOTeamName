package ui.builder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class UIBuilder {
	
	private ComponentBuilder buttonBuilder;
    private ComponentBuilder imageViewBuilder;
    private ComponentBuilder labelBuilder;
	
	public UIBuilder() {
		buttonBuilder = new ButtonBuilder();
        imageViewBuilder = new ImageViewBuilder();
        labelBuilder = new LabelBuilder();
	}
	
	public Node addComponent(Parent layout, Node component) {
		if (layout instanceof Pane) {
			Pane pane = (Pane) layout;
			pane.getChildren().add(component);
		} else if (layout instanceof Group) {
			Group pane = (Group) layout;
			pane.getChildren().add(component);
		} else {
			return null;
		}
		return component;
	}

	private Node removeComponent(Parent layout, Node component) {
		if (layout instanceof Pane) {
			Pane pane = (Pane) layout;
			pane.getChildren().remove(component);
		} else if (layout instanceof Group) {
			Group pane = (Group) layout;
			pane.getChildren().remove(component);
		} else {
			return null;
		}
		return component;
	}
	
	public Node addNewButton(Parent layout, ComponentProperties properties) {
		return addComponent(layout, buttonBuilder.createComponent(properties));
	}

	public Node addNewImageView(Parent layout, ComponentProperties properties) {
		return addComponent(layout, imageViewBuilder.createComponent(properties));
	}

    public Node addNewLabel(Parent layout, ComponentProperties properties) {
        return addComponent(layout, labelBuilder.createComponent(properties));
    }

	public void initWindow(Stage currStage, String propertiesFilePath) {
        ResourceBundle resources = ResourceBundle.getBundle(propertiesFilePath);
        currStage.setTitle(resources.getString("windowTitle"));
        currStage.setHeight(Integer.parseInt(resources.getString("windowHeight")));
        currStage.setWidth(Integer.parseInt(resources.getString("windowWidth")));
        currStage.centerOnScreen();
        currStage.show();
    }

}
