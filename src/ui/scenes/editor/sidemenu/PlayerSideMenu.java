package ui.scenes.editor.sidemenu;

import block.BlockType;
import editor.EditorController;
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
    private EditorController myController;
    private String selectedPlayerImagePath = "";
    private List<String> mySelectedPaths;
    private ItemSideMenu myItemMenu;

    PlayerSideMenu(Parent root, ResourceBundle resources, EditorController controller, ItemSideMenu itemMenu) {
        super(root, resources);
        myResources = resources;
        myController = controller;
        mySelectedPaths = new ArrayList<>();
        myItemMenu = itemMenu;
        init();
    }

    private ScrollPane addEnemies() {
        return myItemMenu.createScrollPane(BlockType.ENEMY);
    }

    /**
     * Adds each sprite image representation to the menu
     *
     * @return the FlowPane containing the sprites
     */
    private ScrollPane addSprites() {
        UIBuilder builder = new UIBuilder();
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        FlowPane sprites = createFlowPane();

        File file = new File(myResources.getString("rawSpritePath"));
        String[] images = file.list();
        if (images != null) {
            for (String image : images) {

                StringBuilder s = new StringBuilder();
                for (char c : image.toCharArray()) {
                    if (c == '-') break;
                    s.append(c);
                }
                int id = Integer.parseInt(s.toString());


                if (image.contains("down")) {
                    String imagePath = myResources.getString("spritePath") + image;
                    Node sprite = builder.addNewImageView(myRoot, new ComponentProperties()
                            .path(imagePath)
                            .width(util.getIntProperty("spriteWidth"))
                            .preserveRatio(true)
                            .id(myResources.getString("spriteCSSid")));
                    sprite.setOnMouseClicked(e -> {
                        
                        for (Node otherSprite : sprites.getChildren()) {
                            resetHoverEffect(otherSprite);
                        }
                        if (selectedPlayerImagePath.equals(imagePath)) {
                            // deselect
                            selectedPlayerImagePath = "";
                        } else {
                            sprite.setStyle(myResources.getString("selectedEffect"));
                            sprite.setOnMouseExited(f -> sprite.setStyle(myResources.getString("selectedEffect")));
                            mySelectedPaths.clear();

                            for (String i : images) {
                                StringBuilder sb = new StringBuilder();
                                for (char c : i.toCharArray()) {
                                    if (c == '-') break;
                                    sb.append(c);
                                }
                                if (id == Integer.parseInt(sb.toString())) {
                                    mySelectedPaths.add(myResources.getString("spritePath") + i);
                                }

                            }


                        }
                        setChanged();
                        notifyObservers(mySelectedPaths);
                    });
                    sprites.getChildren().add(sprite);
                }
            }
        }
        return new ScrollPane(sprites);
    }
    /**
     * Adds the tabs to the player side menu
     */
    protected void addTabs() {
        // sprite tab
        Tab spriteTab = createTab(myResources.getString("spriteTab"), addSprites());
//        FlowPane sprites = addSprites();
//        ScrollPane spritePane = new ScrollPane(sprites);
//        spriteTab.setContent(spritePane);
        Tab enemyTab = createTab(myResources.getString("enemyTab"), addEnemies());

        myPanel.getTabs().addAll(spriteTab, enemyTab);
    }
    
    public List<String> getImagePaths(){
        System.out.println("robertsuxdick" + mySelectedPaths);
        return mySelectedPaths;
    }
    
}