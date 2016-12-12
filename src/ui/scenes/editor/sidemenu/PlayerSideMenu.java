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
    private String selectedPlayerImagePath = "";
    private List<String> mySelectedPaths;
    private ItemSideMenu myItemMenu;

    PlayerSideMenu(Parent root, ResourceBundle resources, ItemSideMenu itemMenu) {
        super(root, resources);
        myResources = resources;
        mySelectedPaths = new ArrayList<>();
        myItemMenu = itemMenu;
        init();
    }

    /**
     * Adds communicator blocks that represent NPCs with which the user
     * can speak.
     *
     * @return a ScrollPane displaying the communicator blocks
     */
    private ScrollPane addNPCs() {
        return myItemMenu.createScrollPane(BlockType.COMMUNICATOR);
    }

    /**
     * Adds enemy blocks that battles for the user to trigger.
     *
     * @return a ScrollPane displaying the enemy blocks
     */
    private ScrollPane addEnemies() {
        return myItemMenu.createScrollPane(BlockType.ENEMY);
    }

    private void setSpriteDirections(int id, String[] spriteImagePaths) {
        for (String i : spriteImagePaths) {
            StringBuilder sb2 = new StringBuilder();
            for (char c : i.toCharArray()) {
                if (c == '-') break;
                sb2.append(c);
            }
            if (id == Integer.parseInt(sb2.toString())) {
                mySelectedPaths.add(myResources.getString("spritePath") + i);
            }

        }
    }

    private void addEventHandler(Node sprite, int id, String imagePath, String[] spriteImagePaths, List<Node> otherSprites) {
        sprite.setOnMouseClicked(e -> {
            for (Node otherSprite : otherSprites) {
                resetHoverEffect(otherSprite);
            }
            if (selectedPlayerImagePath.equals(imagePath)) {
                // deselect
                selectedPlayerImagePath = "";
                mySelectedPaths.clear();
            } else {
                sprite.setStyle(myResources.getString("selectedEffect"));
                sprite.setOnMouseExited(f -> sprite.setStyle(myResources.getString("selectedEffect")));
                selectedPlayerImagePath = imagePath;
                mySelectedPaths.clear();
                setSpriteDirections(id, spriteImagePaths);
            }
            setChanged();
            notifyObservers(mySelectedPaths);
        });
    }

    /**
     * Adds an icon representing a sprite to the sprite control panel
     *
     * @param imagePath is the path to the icon representing the sprite
     * @return the newly created Node
     */
    private Node addSpriteIcon(String imagePath) {
        UIBuilder builder = new UIBuilder();
        PropertiesUtilities util = new PropertiesUtilities(myResources);
        String rawPath = myResources.getString("spritePath") + imagePath;
        return builder.addNewImageView(myRoot, new ComponentProperties()
                .path(rawPath)
                .width(util.getIntProperty("spriteWidth"))
                .preserveRatio(true)
                .id(myResources.getString("spriteCSSid")));
    }

    /**
     * Gets the integer ID associated with each sprite image
     *
     * @param imagePath the image path of the sprite
     * @return the number representing the sprite's ID
     */
    private int getId(String imagePath) {
        StringBuilder sb = new StringBuilder();
        for (char c : imagePath.toCharArray()) {
            if (c == '-') break;
            sb.append(c);
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Get all sprite image paths from a given directory
     *
     * @return a String array holding the image paths
     */
    private String[] getSpriteImagePaths(String directory) {
        File file = new File(directory);
        return file.list();
    }

    /**
     * Adds each sprite image representation to the menu
     *
     * @return the FlowPane containing the sprites
     */
    private ScrollPane addSprites() {
        FlowPane sprites = createFlowPane();
        String[] spriteImagePaths = getSpriteImagePaths(myResources.getString("rawSpritePath"));

        for (String image : spriteImagePaths) {
            int id = getId(image); // number representing the image

            if (image.contains(myResources.getString("spriteDisplayDirection"))) {
                String imagePath = myResources.getString("spritePath") + image;
                Node sprite = addSpriteIcon(image);
                addEventHandler(sprite, id, imagePath, spriteImagePaths, sprites.getChildren());
                sprites.getChildren().add(sprite);
            }

        }
        return new ScrollPane(sprites);
    }

    /**
     * Adds the tabs to the player side menu
     */
    protected void addTabs() {
        Tab spriteTab = createTab(myResources.getString("spriteTab"), addSprites());
        Tab enemyTab = createTab(myResources.getString("enemyTab"), addEnemies());
        Tab npcTab = createTab(myResources.getString("npcTab"), addNPCs());
        myPanel.getTabs().addAll(spriteTab, enemyTab, npcTab);
    }

    /**
     * @return a list containing all of the image paths representing the different directions
     * that a player sprite faces
     */
    public List<String> getImagePaths() {
        return mySelectedPaths;
    }
}