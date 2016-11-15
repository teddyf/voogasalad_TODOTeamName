package frontend.uibuilder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public abstract class ComponentBuilder {
	
	protected Parent layout;
	
	public ComponentBuilder(Parent layout) {
		this.layout = layout;
	}
	
	public abstract Node createComponent();
	
	public abstract Node createComponent(ComponentProperties properties);
	
	public Node addComponent(Node component) {
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
	
	
	public Node removeComponent(Node component) {
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

}
