package ui.scenes.editor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import player.PlayerAttribute;
import editor.EditorController;
import resources.properties.PropertiesUtilities;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import ui.scenes.editor.objects.GameObject;

public class PlayerMenuUI implements Observer {
	private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private PlayerImageChooserUI myPlayerImageChooserUI;
    private PlayerAttributeUI myPlayerAttributeUI;
    private DraggableTabPane myPlayerPanel;
    private EditorController myEditorController;

    PlayerMenuUI(Parent root, UIBuilder builder, ResourceBundle resources,EditorController editorController) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        myEditorController = editorController;
        myPlayerImageChooserUI = new PlayerImageChooserUI(this);
        myPlayerAttributeUI = new PlayerAttributeUI(this);
    }
    
    private void addTabs(DraggableTabPane playerPanel) {
    	Tab playerImageTab = new Tab();
    	ScrollPane playerImagePane = createScrollPane();
    	//set content here
    	playerImagePane.setContent(myPlayerImageChooserUI.getGroup());
    	playerImageTab.setText("Player Image");
    	playerImageTab.setContent(playerImagePane);
    	
    	Tab playerAttributesTab = new Tab();
    	ScrollPane playerAttributesPane = createScrollPane();
    	//set content here
    	playerAttributesPane.setContent(myPlayerAttributeUI.getGroup());
    	playerAttributesTab.setText("Player Attributes");
    	playerAttributesTab.setContent(playerAttributesPane);
    	
    	playerPanel.getTabs().addAll(playerImageTab,playerAttributesTab);
    }
    
    public DraggableTabPane getPlayerPanel() {
        return myPlayerPanel;
    }
    
    public void init() {
    	 myPlayerPanel = createPlayerPanel();
         addTabs(myPlayerPanel);
    }
    
    private DraggableTabPane createPlayerPanel() {
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        DraggableTabPane playerPanel = new DraggableTabPane();
        playerPanel.setLayoutX(util.getIntProperty( "itemMenuX"));
        playerPanel.setLayoutY(util.getIntProperty( "itemMenuY"));
        playerPanel.setMinWidth(util.getIntProperty( "itemMenuWidth"));
        playerPanel.setMinHeight(util.getIntProperty( "itemMenuHeight"));
        return playerPanel;
    }
    
    private ScrollPane createScrollPane() {
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        FlowPane itemPane = new FlowPane();
        int padding = util.getIntProperty("contentPadding");
        itemPane.setHgap(padding);
        itemPane.setVgap(padding);
        itemPane.setPadding(new Insets(padding, padding, padding, padding));
        return new ScrollPane(itemPane);
    }

	@Override
	public void update(Observable o, Object arg) {
		myEditorController.addPlayer(myPlayerImageChooserUI.getPlayerFilePath(),0,0);
		myEditorController.addPlayerAttribute(myPlayerAttributeUI.getName(), myPlayerAttributeUI.getAmount(), myPlayerAttributeUI.getIncrement(), myPlayerAttributeUI.getDecrement());
	}

}
