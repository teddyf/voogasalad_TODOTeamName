package ui.scenes.editor.sidemenu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class PlayerSideMenu extends SideMenu {

    private ResourceBundle myResources;

    public PlayerSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        myResources = resources;
        init();
    }

    private FlowPane addSprites() {

//        String directory = "resources/images/sprites/";
//        File file = new File(directory);
//        String [] contents = file.list();


        FlowPane sprites = createFlowPane();
        UIBuilder builder = new UIBuilder();
        String path1 = "resources/images/sprites/1-down.png";
        Node n = builder.addNewImageView(myRoot, new ComponentProperties()
                .path(path1)
                .preserveRatio(true)
                .id("sprite-item"));
        sprites.getChildren().add(n);
        String path2 = "resources/images/sprites/2-down.png";
        Node n2 = builder.addNewImageView(myRoot, new ComponentProperties()
                .path(path2)
                .preserveRatio(true)
                .id("sprite-item"));
        sprites.getChildren().add(n2);

        String path3 = "resources/images/sprites/3-down.png";
        Node n3 = builder.addNewImageView(myRoot, new ComponentProperties()
                .path(path3)
                .preserveRatio(true)
                .id("sprite-item"));
        sprites.getChildren().add(n3);
        return sprites;
    }

    protected void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        // sprite tab
        Tab spriteTab = createTab(myResources.getString("spriteTab"), new ScrollPane(createFlowPane()));
        FlowPane sprites = addSprites();
        ScrollPane spritePane = new ScrollPane(sprites);
        spriteTab.setContent(spritePane);
        tabs.add(spriteTab);
        // attributes tab
//       s
        myPanel.getTabs().addAll(tabs);
    }

}
