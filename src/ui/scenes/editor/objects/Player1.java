package ui.scenes.editor.objects;

import javafx.scene.image.Image;
import ui.GridPaneNode;
import block.BlockType;

public class Player1 extends GameObject {

    private final String PATH = "resources/images/tiles/decorations/grass-1";

    public Player1() {
        super();
        Image image = new Image(PATH + ".png");
        myImageView.setImage(image);
        myIconPath = PATH;
        myBlockType = BlockType.DECORATION;
    }

    @Override
    public void populateList() {
        String name = PATH + ".png";
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        myImageTiles.add(tempNode);
    }
}
