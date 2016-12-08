package ui.scenes.editor;

import java.util.ResourceBundle;

import editor.EditorController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.properties.PropertiesUtilities;
import ui.GridPane;
import ui.scenes.editor.objects.ItemPanelObjects;

public class MultiGridUI {

	private TabPane tabs;
	private Node root;
	private Tab gridTab;
	private int tabNum;
    private PropertiesUtilities myUtil;
	private ResourceBundle myResources;
	
	private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
	
	public MultiGridUI(double width, double height) {
		tabs = new TabPane();
		tabs.setPrefSize(width, height);
		root = tabs;
		tabNum = 1;
		addNewGridTab();
		myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
	}

	public void newGrid(String name, int maxDim, Parent root, ResourceBundle myResources){
		DimensionPrompt myDimensionPrompt = new DimensionPrompt(root, myResources);
		Dimension myGridDimensions = myDimensionPrompt.promptForDimensions(maxDim);
		GridPane myGridPane = new GridPane(myGridDimensions.width(),
                myGridDimensions.height(),
                myUtil.getIntProperty("windowWidth"),
                myUtil.getIntProperty("windowHeight"),
                myUtil.getIntProperty("gridX"),
                myUtil.getIntProperty("gridY"));
		 Tab toAdd = new Tab();
		 
		 toAdd.setContent(myGridPane.getGroup());
		 toAdd.setText(name);
		 toAdd.setClosable(false);
		 tabNum++;
		 addTab(toAdd);
	}
	
	private void addTab(Tab toAdd){
		 tabs.getTabs().add(toAdd);
		 tabs.getSelectionModel().select(toAdd);
		 tabs.setTabMinWidth(20);
	}
	
	private void addNewGridTab(){
		gridTab = new Tab();
		gridTab.setText("New Grid");
		gridTab.setOnSelectionChanged(event -> {
			//TODO: prompt user for new grid name
			newGrid("New Grid", 100, new Group(), myResources);	
        });
		tabs.getTabs().add(gridTab);
	}
	
	public Node getRoot(){
		return root;
	}

}

