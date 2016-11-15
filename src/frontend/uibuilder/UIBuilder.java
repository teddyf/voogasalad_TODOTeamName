package frontend.uibuilder;

import javafx.scene.Parent;

public class UIBuilder {
	
	private Parent layout;
	
	private ComponentBuilder buttonBuilder;
	
	public UIBuilder(Parent layout) {
		this.layout = layout;
	}
	
	public void addNewButton(ComponentProperties properties) {
		if (buttonBuilder == null)
			buttonBuilder = new ButtonBuilder(layout);
		buttonBuilder.createComponent(properties);
	}
	

}
