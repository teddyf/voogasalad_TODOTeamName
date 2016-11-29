package ui.builder;

import java.util.ResourceBundle;

import sun.security.tools.policytool.Resources;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class SidePanelBuilder {
    private static final String SIDEPANEL_RESOURCES = "resources/sidepanel";
    private Group myRegion;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    
	public SidePanelBuilder(Group region, String resourcesFileName) {
		myRegion = region;
		myResources = Resources.getBundle(resourcesFileName);
		myBuilder = new UIBuilder();
	}
	
    public Tab createTab (String label, ScrollPane scrollPane) {
        Tab tab = new Tab();
        tab.setText(myResources.getString(label));
        tab.setContent(scrollPane);
        tab.setClosable(false);
        return tab;
    }
}
