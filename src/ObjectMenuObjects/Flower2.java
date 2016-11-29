package ObjectMenuObjects;

import ui.GridPaneNode;
import block.BlockType;
import javafx.scene.image.Image;

public class Flower2 extends GameObjects{

    private final String PATH = "resources/images/Sprites/Declaration/Flower/flower2";
    public Flower2 () {
        super();
        Image image = new Image(PATH+".png");
        this.imageView.setImage(image);
        type = "flower";
        path = "resources/images/Sprites/Declaration/Flower/flower2";
        blockType = BlockType.DECORATION;
    }

    @Override
    public void populateList () {
        String name = reName(PATH,1);
        GridPaneNode tempNode = new GridPaneNode(0,0,name);
        list.add(tempNode);
    }
}
