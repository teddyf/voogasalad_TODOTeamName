package editor;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/gameeditor";
    private Stage myStage;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private Group mySideMenuRegion;
    private ResourceBundle myResources;

    public GameEditor(Stage stage, Parent root) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        initEditor();
        initRegions();
        initSideMenu();
    }

    private void initEditor() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();
    }
    
    private void initRegions() {
    	mySideMenuRegion = createRegion(800, 0);
    	myBuilder.addComponent(myRoot, mySideMenuRegion);
    }
    
	private Group createRegion(int layoutX, int layoutY ) {
		Group region = new Group();
		region.setLayoutX(layoutX);
		region.setLayoutY(layoutY);
		return region;
	}

    private void initSideMenu() {
    	SidePanel sideMenu = new SidePanel(mySideMenuRegion);
    }

}
