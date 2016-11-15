package frontend.uibuilder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public abstract class ComponentBuilder {

	public ComponentBuilder() {
		
	}
	
	public abstract Node createComponent(ComponentProperties properties);

}
