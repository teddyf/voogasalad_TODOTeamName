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
 *         This class provides functionality for the model.player side menu, with which users
 *         can create their sprite or other NPCs in the game.
 */
public class PlayerSideMenu extends SideMenu {

    private ResourceBundle myResources;
    private ItemSideMenu myItemMenu;
    private List<String> selectedSpriteImages;
    private String[] filesInDirectory;

    PlayerSideMenu(Parent root, ResourceBundle resources, ItemSideMenu itemMenu) {
        super(root, resources);
        myResources = resources;
        selectedSpriteImages = new ArrayList<>();
        myItemMenu = itemMenu;
        init();
    }

    /**
     * @return a list containing four image paths, each of which represents a
     * sprite's facing direction
     */
    public List<String> getImagePaths() {
        return selectedSpriteImages;
    }

    /**
     * Adds NPC blocks with which the user can speak
     *
     * @return a ScrollPane displaying the NPC blocks
     */
    private ScrollPane addNPCs() {
        return myItemMenu.createScrollPane(BlockType.NPC);
    }

    /**
     * Adds communicator blocks that talk to the user
     *
     * @return a ScrollPane displaying the communicator blocks
     */
    private ScrollPane addCommunicators() {
        return myItemMenu.createScrollPane(BlockType.COMMUNICATOR);
    }

    /**
     * Adds enemy blocks that trigger battles
     *
     * @return a ScrollPane displaying the enemy blocks
     */
    private ScrollPane addEnemies() {
        return myItemMenu.createScrollPane(BlockType.ENEMY);
    }

    /**
     * Gets all image paths corresponding the various directions of a single
     * sprite
     *
     * @param id the id of the sprite
     */
    private void setSpriteDirections(int id) {
        selectedSpriteImages.clear();
        for (String file : filesInDirectory) {
            StringBuilder sb = new StringBuilder();
            for (char c : file.toCharArray()) {
                if (c == '-') break;
                sb.append(c);
            }
            if (id == Integer.parseInt(sb.toString())) {
                selectedSpriteImages.add(myResources.getString("spritePath") + file);
            }
        }
    }

    /**
     * Adds an event handler to each sprite icon that selects the sprite and displays
     * a visual effect to the user confirming the selection
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
            setSpriteDirections(id);
            setChanged();
            notifyObservers(selectedSpriteImages);
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
     * Adds each sprite image representation to the menu
     *
     * @return the FlowPane containing the sprites
     */
    private ScrollPane addSprites() {
        FlowPane sprites = createFlowPane();
        filesInDirectory = getFilePaths(myResources.getString("rawSpritePath"));
        for (String image : filesInDirectory) {
            int id = getId(image); // number representing the image
            if (image.contains(myResources.getString("spriteDisplayDirection"))) {
                Node sprite = addSpriteIcon(image);
                addEventHandler(sprite, id, sprites.getChildren());
                myBuilder.addComponent(sprites, sprite);
            }
        }
        return new ScrollPane(sprites);
    }

    /**
     * Adds the tabs to the model.player side menu
     */
    protected void addTabs() {
        Tab spriteTab = createTab(myResources.getString("spriteTab"), addSprites());
        Tab npcTab = createTab(myResources.getString("npcTab"), addNPCs());
        Tab enemyTab = createTab(myResources.getString("enemyTab"), addEnemies());
        Tab communicatorTab = createTab(myResources.getString("communicatorTab"), addCommunicators());
        myPanel.getTabs().addAll(spriteTab, npcTab, enemyTab, communicatorTab);
    }
}