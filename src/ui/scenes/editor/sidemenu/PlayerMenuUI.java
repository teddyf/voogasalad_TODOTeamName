package ui.scenes.editor.sidemenu;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import editor.EditorController;
import resources.properties.PropertiesUtilities;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import ui.builder.UIBuilder;

@Deprecated
public class PlayerMenuUI implements Observer {

	private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PlayerImageChooserUI myPlayerImageChooserUI;
    private PlayerAttributeUI myPlayerAttributeUI;
    private DraggableTabPane myPlayerPanel;
    private EditorController myEditorController;

    public PlayerMenuUI(Parent root, UIBuilder builder, ResourceBundle resources,EditorController editorController) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        myEditorController = editorController;
        myPlayerImageChooserUI = new PlayerImageChooserUI(this);
        myPlayerAttributeUI = new PlayerAttributeUI(this);
        init();
    }
    
    private void addTabs(DraggableTabPane playerPanel) {
    	Tab playerImageTab = new Tab();
//    	ScrollPane playerImagePane = createScrollPane();
    	//set content here
//    	playerImagePane.setContent(myPlayerImageChooserUI.getGroup());
    	playerImageTab.setText("Player Image");
//    	playerImageTab.setContent(playerImagePane);
    	
    	Tab playerAttributesTab = new Tab();
//    	ScrollPane playerAttributesPane = createScrollPane();
    	//set content here
//    	playerAttributesPane.setContent(myPlayerAttributeUI.getGroup());
    	playerAttributesTab.setText("Player Attributes");
//    	playerAttributesTab.setContent(playerAttributesPane);
    	
    	playerPanel.getTabs().addAll(playerImageTab,playerAttributesTab);
    }
    
    public DraggableTabPane getPlayerPanel() {
        return myPlayerPanel;
    }

//    private ScrollPane createScrollPane() {
//        PropertiesUtilities util = new PropertiesUtilities(myResources);
//        ColorAdjust hoverOpacity = new ColorAdjust();
//        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
//        FlowPane itemPane = new FlowPane();
//        int padding = util.getIntProperty("contentPadding");
//        itemPane.setHgap(padding);
//        itemPane.setVgap(padding);
//        itemPane.setPadding(new Insets(padding, padding, padding, padding));
//        return new ScrollPane(itemPane);
//    }
    
    private DraggableTabPane createPlayerPanel() {
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        DraggableTabPane playerPanel = new DraggableTabPane();
        playerPanel.setLayoutX(util.getIntProperty("sidePanelX"));
        playerPanel.setLayoutY(util.getIntProperty("sidePanelY"));
        playerPanel.setMinWidth(util.getIntProperty("sidePanelWidth"));
        playerPanel.setMinHeight(util.getIntProperty("sidePanelHeight"));
        return playerPanel;
    }
    


	@Override
	public void update(Observable o, Object arg) {
		myEditorController.addPlayer(myPlayerImageChooserUI.getPlayerFilePath(),0,0);
		myEditorController.addPlayerAttribute(myPlayerAttributeUI.getName(), myPlayerAttributeUI.getAmount(), myPlayerAttributeUI.getIncrement(), myPlayerAttributeUI.getDecrement());
	}

    public void init() {
        myPlayerPanel = createPlayerPanel();
        addTabs(myPlayerPanel);
    }

}
