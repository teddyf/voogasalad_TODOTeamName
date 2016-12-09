package ui.scenes.editor.sidemenu;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class PlayerSideMenu extends SideMenu {

    public PlayerSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        init();
    }


    /**
     * Creates and adds tabs for each object type to the Player Menu
     */
    public void addTabs() {
        List<Tab> tabs = new ArrayList<>();
        // sprite tab
        Tab spriteTab = createTab("Sprite", new ScrollPane(createFlowPane()));
        FlowPane sprites = createFlowPane();

        String path1 = "resources/images/tiles/sprites/player-1-west-facing.png";
        Button button1 = new Button();
        button1.setGraphic(new ImageView(new Image(path1)));
        sprites.getChildren().add(button1);


        ScrollPane spritePane = new ScrollPane(sprites);
//        spritePane.setContent(new PlayerImageChooserUI(this).getGroup());
        spriteTab.setContent(spritePane);
        tabs.add(spriteTab);
        // attributes tab
        Tab attributesTab = createTab("Attributes", new ScrollPane(createFlowPane()));
        ScrollPane attributesPane = new ScrollPane(createFlowPane());
//        attributesPane.setContent(new PlayerAttributeUI(this).getGroup());
        attributesTab.setContent(attributesPane);
        tabs.add(attributesTab);
        myPanel.getTabs().addAll(tabs);
    }

}
