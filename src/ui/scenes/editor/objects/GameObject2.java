package ui.scenes.editor.objects;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.GridPaneNode;
import block.*;

/**
 * @author Nisa, Pim, Teddy, Harshil
 */
public abstract class GameObject2 {

    protected String myIconPath;
    protected List<GridPaneNode> myImageTiles;
    protected ImageView myImageView;
    protected BlockType myBlockType;

    public GameObject2(String iconPath, BlockType blockType) {
        myIconPath = iconPath;
        myBlockType = blockType;
        myImageView = new ImageView(new Image(myIconPath));
        myImageTiles = new ArrayList<>();
        populateList();
    }

    public abstract void populateList();

    public List<GridPaneNode> getImageTiles() {
        return myImageTiles;
    }

    public String rename(String a, int count) {
        return a + "." + count + ".png";
    }

    public void setIcon(ImageView imageView) {
        myImageView = imageView;
    }

    public ImageView getIcon() {
        return myImageView;
    }

    public BlockType getBlockType() {
        return myBlockType;
    }

    public String getIconPath() {
        return myIconPath;
    }

}
