package ui.scenes.editor;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class initializes tab-based UI used to choose objects to place
 *         on the overworld grid editor.
 */
public class ItemMenuUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public ItemMenuUI(Parent root, UIBuilder builder, ResourceBundle resources) {
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
    }

    /**
     * Creates the tab-based menu that will hold the objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    EditorSidePanel initItemMenu() {
        PropertiesUtilities util = new PropertiesUtilities();
        int itemMenuXPos = util.getIntProperty(myResources, "itemMenuXPos");
        int itemMenuYPos = util.getIntProperty(myResources, "itemMenuYPos");
        Pane itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        myBuilder.addComponent(myRoot, itemMenuRegion);
        return new EditorSidePanel(itemMenuRegion);
    }
}