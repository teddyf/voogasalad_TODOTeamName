package ui.scenes.engine;

import java.util.ResourceBundle;

import javafx.scene.Group;
import ui.builder.SidePanelBuilder;
import ui.builder.UIBuilder;

public class EngineSidePanel extends SidePanelBuilder {
//	 private static final String SIDEPANEL_RESOURCES = "resources/sidepanel";
	 private Group myRegion;
	 private ResourceBundle myResources;
	 private UIBuilder myBuilder;
	 
	public EngineSidePanel(Group region, String resourceFileName){
		super(region, resourceFileName);
		myBuilder = new UIBuilder();
	}
	
	
}
