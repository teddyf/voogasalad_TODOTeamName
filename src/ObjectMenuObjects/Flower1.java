package ObjectMenuObjects;

import ui.GridPaneNode;
import block.BlockType;
import javafx.scene.image.Image;

public class Flower1 extends GameObjects{

    private final String PATH = "resources/images/Sprites/Declaration/Flower/flower1";
    public Flower1 () {
        super();
        Image image = new Image(PATH+".png");
        imageView.setImage(image);
        type = "flower";
        path = "resources/images/Sprites/Declaration/Flower/flower1";
        blockType = BlockType.DECORATION;
    }

    @Override
    public void populateList () {
        String name = reName(PATH,1);
        GridPaneNode tempNode = new GridPaneNode(0,0,name);
        list.add(tempNode);
    }
}
