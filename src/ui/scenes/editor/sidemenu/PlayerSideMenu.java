package ui.scenes.editor.sidemenu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import resources.properties.PropertiesUtilities;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class defines the functionality for the player side menu from which users
 *         can add their sprite representation to the game.
 */
public class PlayerSideMenu extends SideMenu {

    private ResourceBundle myResources;

    public PlayerSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        myResources = resources;
        init();
    }

    /**
     * Adds each sprite image representation to the menu
     *
     * @return the FlowPane containing the sprites
     */
    private FlowPane addSprites() {
        UIBuilder builder = new UIBuilder();
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        FlowPane sprites = createFlowPane();
        File file = new File(myResources.getString("rawSpritePath"));
        String[] images = file.list();
        for (String image : images) {
            if (image.contains("down")) {
                String imagePath = myResources.getString("spritePath") + image;
                Node sprite = builder.addNewImageView(myRoot, new ComponentProperties()
                        .path(imagePath)
                        .width(util.getIntProperty("spriteWidth"))
                        .preserveRatio(true)
                        .id(myResources.getString("spriteCSSid")));
                sprites.getChildren().add(sprite);
            }
        }
        return sprites;
    }

    /**
     * Adds the tabs to the player side menu
     */
    protected void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        // sprite tab
        Tab spriteTab = createTab(myResources.getString("spriteTab"), new ScrollPane(createFlowPane()));
        FlowPane sprites = addSprites();
        ScrollPane spritePane = new ScrollPane(sprites);
        spriteTab.setContent(spritePane);
        tabs.add(spriteTab);
        // TODO add more panes here, possibly refactor
        myPanel.getTabs().addAll(tabs);
    }
}
