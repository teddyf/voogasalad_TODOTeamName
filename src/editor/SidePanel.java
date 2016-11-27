package editor;

import java.util.ResourceBundle;

import sun.security.tools.policytool.Resources;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SidePanel {
	private static final String SIDEPANEL_RESOURCES = "resources/sidepanel";
	private Group myRegion;
	private ResourceBundle myResources;
	
	public SidePanel(Group region) {
		myRegion = region;
		myResources = Resources.getBundle(SIDEPANEL_RESOURCES);
		initSidePanel();
	}
	
	
	private Tab createTab(String label, ScrollPane scrollPane) {
		Tab tab = new Tab();
		tab.setText(myResources.getString(label));
		tab.setContent(scrollPane);
		return tab;
	}
	
	private ScrollPane createScrollPane(Node content) {
		ScrollPane scrollPane = new ScrollPane(content);
		return scrollPane;
	}
	
	private void initSidePanel() {
		TabPane tp = new TabPane();
		VBox vbox=new VBox();
		vbox.getChildren().addAll(new Button("current"));
		Tab decTab = createTab("decoration", createScrollPane(new Decorations().getDecorationsVBox()));
		//Tab decTab = createTab("decoration", createScrollPane(new Rectangle(20, 20, Color.ALICEBLUE)));
		Tab objTab = createTab("obstacle", createScrollPane(new Rectangle(20, 20, Color.ANTIQUEWHITE)));
		Tab switchTab = createTab("switch", createScrollPane(new Rectangle(20, 20, Color.AZURE)));
		Tab npcTab = createTab("NPC", createScrollPane(new Rectangle(20, 20, Color.BISQUE)));
		tp.getTabs().addAll(decTab, objTab, switchTab, npcTab);
		tp.setSide(Side.LEFT);
		myRegion.getChildren().add(tp);
	}
}
