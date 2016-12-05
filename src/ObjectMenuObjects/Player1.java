package ObjectMenuObjects;

import javafx.scene.image.Image;
import ui.GridPaneNode;
import block.BlockType;

public class Player1 extends GameObjects {

    private final String PATH = "resources/images/tiles/decorations/grass-1";

    public Player1() {
        super();
        Image image = new Image(PATH + ".png");
        imageView.setImage(image);
        path = PATH;
        blockType = BlockType.DECORATION;
    }

    @Override
    public void populateList() {
        String name = PATH + ".png";
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        list.add(tempNode);
    }
}
