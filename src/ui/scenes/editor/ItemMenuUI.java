package ui.scenes.editor;

import editor.SidePanel;
import javafx.scene.Group;
import javafx.scene.Parent;
import resources.properties.PropertiesUtilities;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class initializes menu-based UI used to choose objects to place
 *         on the overworld grid editor.
 */
public class ItemMenuUI {

    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    ItemMenuUI(Parent root, UIBuilder builder, String resourcesPath) {
        myRoot = root;
        myBuilder = builder;
        myResources = ResourceBundle.getBundle(resourcesPath);
    }

    /**
     * Creates the tab-based menu that will hold the objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    public SidePanel initItemMenu() {
        PropertiesUtilities util = new PropertiesUtilities();
        int itemMenuXPos = util.getIntProperty(myResources, "itemMenuXPos");
        int itemMenuYPos = util.getIntProperty(myResources, "itemMenuYPos");
        Group itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        myBuilder.addComponent(myRoot, itemMenuRegion);
        SidePanel sideMenu = new SidePanel(itemMenuRegion);
        return sideMenu;
    }
}