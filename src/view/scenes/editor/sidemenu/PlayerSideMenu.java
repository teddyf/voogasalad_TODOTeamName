// This entire file is part of my masterpiece.
// Robert Steilberg
/**
 * This class provides the basic functionality for the player side menu. It specifically
 * extends the SideMenu abstract superclass and implements the required addTabs() method.
 * The most important function of this class is to create the sprite that will represent
 * the user, i.e., the sprite that the user will control throughout the gameplay. The user
 * can also create other non-player characters to interact with in addition to enemies that
 * can be battled and non-player "communicator" blocks that display a static message to the
 * player.
 * <p>
 * I chose this class as my masterpiece because it not only follows good coding conventions
 * and is an integral class to the game editor but also incorporates advanced Java topics
 * that we learned in class. This class extends SideMenu, which extends Observable, and I
 * make use of the observable/observer interaction by setting up a setChanged() and
 * notifyDirections() method whenever the player chooses a new sprite to represent them
 * in the game. This automatically triggers the grid to expect a player to be placed on
 * the grid, which will then prompt the user for the player's name and call a special
 * method unique to player creation. This also triggers a reaction in the controller that
 * the player has been created and the game is now playable.
 * <p>
 * This class also follows a good inheritance hierarchy by extending the SideMenu and making
 * use of shared functionality through the createTab(), createDraggableFlowPane(), getFilePaths(),
 * and resetHoverEffect(). This class has private instance variables with getters for protection.
 * This class also makes use of homegrown utilities, such as UIBuilder for easy JavaFX node
 * building and PropertiesUtilities for simplified access to properties files.
 */

package view.scenes.editor.sidemenu;

import model.block.blocktypes.BlockType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import utilities.PropertiesUtilities;
import utilities.builder.ComponentProperties;
import utilities.builder.UIBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class provides functionality for the player side menu that allows
 *         users to manipulate the different players that can be in a game. Users
 *         can use this control panel to create their own sprite representation,
 *         non-player-characters, enemies that prompt battles, or simple communicators
 *         with which the player can speak.
 *         <p>
 *         Dependencies: ItemSideMenu (for redirection of player blocks)
 */
public class PlayerSideMenu extends SideMenu {

    private ResourceBundle myResources;
    private ItemSideMenu myItemBlocks;
    private List<String> mySelectedSpriteImages;
    private String[] myDirectoryFiles;

    PlayerSideMenu(Parent root, ResourceBundle resources, ItemSideMenu itemMenu) {
        super(root, resources);
        myResources = resources;
        myItemBlocks = itemMenu;
        mySelectedSpriteImages = new ArrayList<>();
        init();
    }

    /**
     * @return a list containing four image paths, each of which represents a
     * different direction that a sprite is facing
     */
    public List<String> getImagePaths() {
        return mySelectedSpriteImages;
    }

    /**
     * Adds NPC blocks for which the user can set messages and speak with; NPC
     * blocks are redirected out of the item menu and into the player side menu
     * because they deal with player-to-player interactions
     *
     * @return a ScrollPane holding the NPC blocks
     */
    private ScrollPane addNPCs() {
        return myItemBlocks.createBlockScrollPane(BlockType.NPC);
    }

    /**
     * Adds enemy blocks that trigger battles; enemy blocks are redirected out of
     * the item menu and into the player side menu because they deal with player-
     * to-player interactions
     *
     * @return a ScrollPane holding the enemy blocks
     */
    private ScrollPane addEnemies() {
        return myItemBlocks.createBlockScrollPane(BlockType.ENEMY);
    }

    /**
     * Gets all image paths corresponding the four directions a sprite may face
     *
     * @param id the id of the sprite
     */
    private void setSpriteDirections(int id) {
        mySelectedSpriteImages.clear();
        for (String file : myDirectoryFiles) {
            StringBuilder spriteId = new StringBuilder();
            for (char c : file.toCharArray()) {
                if (c == '-') break;
                spriteId.append(c);
            }
            if (id == Integer.parseInt(spriteId.toString())) { // i.e. if this matches the chosen sprite
                mySelectedSpriteImages.add(myResources.getString("spritePath") + file);
            }
        }
    }

    /**
     * Adds an event handler to each sprite icon that selects the sprite, displays
     * a visual effect to the user confirming the selection, and notifies the editor
     * controller via an observable that a particular sprite has been chosen to
     * represent the main player
     *
     * @param sprite     the Node representing the sprite
     * @param id         the sprite's id
     * @param allSprites the Nodes of all other sprites
     */
    private void addEventHandler(Node sprite, int id, List<Node> allSprites) {
        sprite.setOnMouseClicked(e -> {
            for (Node otherSprite : allSprites) {
                resetHoverEffect(otherSprite);
            }
            sprite.setStyle(myResources.getString("selectedEffect"));
            sprite.setOnMouseExited(f -> sprite.setStyle(myResources.getString("selectedEffect")));
            setSpriteDirections(id); // pass back images of the sprite facing each direction
            setChanged();
            notifyObservers(mySelectedSpriteImages);
        });
    }

    /**
     * Creates an icon representing a given image path that can be
     * added to a side control panel
     *
     * @param imagePath is the path to the icon representing the sprite
     * @return the newly created JavaFX Node representing the sprite
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
     * Gets the integer ID associated with each unique sprite, where
     * each sprite has an image name formatted as "<id>-<direction>"
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
     * Adds each sprite image representation to the menu that the user
     * can choose to represent their player
     *
     * @return the ScrollPane containing the sprites
     */
    private ScrollPane addSprites() {
        FlowPane sprites = createDraggableFlowPane();
        myDirectoryFiles = getFilePaths(myResources.getString("rawSpritePath"));
        for (String image : myDirectoryFiles) {
            int id = getId(image); // number representing the sprite
            if (image.contains(myResources.getString("spriteDisplayDirection"))) {
                Node sprite = addSpriteIcon(image);
                addEventHandler(sprite, id, sprites.getChildren());
                myBuilder.addComponent(sprites, sprite);
            }
        }
        return new ScrollPane(sprites);
    }

    /**
     * Adds the sub-tabs to the player side menu that represent the player
     * sprite panel, the NPC panel, the enemy block panel, and the communicator
     * panel
     */
    protected void addTabs() {
        Tab spriteTab = createTab(myResources.getString("spriteTab"), addSprites());
        Tab npcTab = createTab(myResources.getString("npcTab"), addNPCs());
        Tab enemyTab = createTab(myResources.getString("enemyTab"), addEnemies());
        myPanel.getTabs().addAll(spriteTab, npcTab, enemyTab);
    }
}