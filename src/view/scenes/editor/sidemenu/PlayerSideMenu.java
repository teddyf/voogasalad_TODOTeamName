// This entire file is part of my masterpiece.
// Robert Steilberg
/**
 * This class provides the basic functionality for the player side menu. It specifically
 * extends the SideMenu abstract superclass and implements the required addTabs() method.
 * The most important function of this class is to create the sprite that will represent
 * the user, i.e., the sprite that the user will control throughout the gameplay. The user
 * can also create other non-player characters to interact with in addition to enemies that
 * can be battled.
 * <p>
 * I chose this class as my masterpiece because it not only follows good coding conventions
 * and is an integral class to the game editor but also incorporates advanced Java topics
 * that we learned in class. This class extends SideMenu, which extends Observable, and I
 * make use of the observable/observer interaction by setting up a setChanged() and
 * notifyObservers() method whenever the player chooses a new sprite to represent them
 * in the game. This automatically triggers the grid to expect a player to be placed on
 * the grid, which will then prompt the user to specify the player's name and call a special grid
 * method unique to player sprite creation. This also triggers a reaction in the controller
 * specifying that the player has been created and the game is now playable. A game cannot
 * be played before a player sprite is chosen.
 * <p>
 * This class also follows a good inheritance hierarchy by extending the SideMenu and making
 * use of shared functionality through the createTab(), createDraggableFlowPane(), getFilePaths(),
 * and resetHoverEffect() methods. This class has private instance variables with getters for
 * protection. This class also makes use of homegrown utilities, such as UIBuilder for
 * easy JavaFX node building and PropertiesUtilities for simplified access to properties files.
 */

package view.scenes.editor.sidemenu;

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
 *         non-player-characters, and enemies that prompt battles.
 *         <p>
 *         Dependencies: SideMenu, Observable, UIBuilder, PropertiesUtilities
 */
public class PlayerSideMenu extends SideMenu {

    private ResourceBundle myResources;
    private List<String> myChosenSpriteImages;
    private String[] myDirectoryFiles;

    PlayerSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        myResources = resources;
        myChosenSpriteImages = new ArrayList<>();
        init();
    }

    /**
     * @return a list containing four image paths, each of which represents a
     * different direction that a unique sprite is facing
     */
    public List<String> getImagePaths() {
        return myChosenSpriteImages;
    }

    /**
     * Gets all image paths corresponding to the four directions that a sprite,
     * specified by a given id, may face
     *
     * @param id the unique id of the sprite
     */
    private void setChosenSpriteDirections(int id) {
        myChosenSpriteImages.clear();
        for (String file : myDirectoryFiles) {
            StringBuilder spriteId = new StringBuilder();
            for (char pathChar : file.toCharArray()) {
                if (pathChar == '-') break;
                spriteId.append(pathChar);
            }
            if (id == Integer.parseInt(spriteId.toString())) {
                // i.e. if this image matches the chosen sprite
                myChosenSpriteImages.add(myResources.getString("spritePath") + file);
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
     * @param id         the sprite's unique id
     * @param allSprites a list of Nodes that collectively represent all other sprites
     */
    private void addChosenEventHandler(Node sprite, int id, List<Node> allSprites) {
        sprite.setOnMouseClicked(e -> {
            for (Node otherSprite : allSprites) {
                resetHoverEffect(otherSprite);
            }
            // chosen sprite is always shown via styling as "selected"
            sprite.setStyle(myResources.getString("selectedEffect"));
            sprite.setOnMouseExited(f -> sprite.setStyle(myResources.getString("selectedEffect")));
            // designate the set of images representing the sprite facing each direction
            setChosenSpriteDirections(id);
            setChanged();
            notifyObservers(myChosenSpriteImages);
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
     * each sprite has an image file name formatted as "<id>-<direction>"
     *
     * @param imagePath the image path of the sprite
     * @return the number representing the sprite's ID
     */
    private int getId(String imagePath) {
        StringBuilder sb = new StringBuilder();
        for (char pathChar : imagePath.toCharArray()) {
            if (pathChar == '-') break;
            sb.append(pathChar);
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Adds each sprite image icon to the menu that the user
     * can choose to represent their player
     *
     * @return the ScrollPane containing the sprites
     */
    private ScrollPane addSprites() {
        FlowPane spritePane = createDraggableFlowPane();
        myDirectoryFiles = getFilePaths(myResources.getString("rawSpritePath"));
        for (String image : myDirectoryFiles) {
            int id = getId(image); // number representing the unique sprite
            if (image.contains(myResources.getString("spriteDisplayDirection"))) {
                Node newSprite = addSpriteIcon(image);
                addChosenEventHandler(newSprite, id, spritePane.getChildren());
                myBuilder.addComponent(spritePane, newSprite);
            }
        }
        return new ScrollPane(spritePane);
    }

    /**
     * Adds the sub-tab to the side control panel for holding the
     * icons that represent player sprites, NPCs, and enemies
     */
    protected void addTabs() {
        Tab spriteTab = createTab(myResources.getString("spriteTab"), addSprites());
        myPanel.getTabs().add(spriteTab);
    }
}