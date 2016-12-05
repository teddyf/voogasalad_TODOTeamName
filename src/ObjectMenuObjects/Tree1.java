package ObjectMenuObjects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Tree1 extends GameObjects {

    private final String PATH = "resources/images/tiles/obstacles/tree-1";

    public Tree1() {
        super();
        Image image = new Image(PATH + ".png");
        this.imageView.setImage(image);
        path = PATH;
        blockType = BlockType.OBSTACLE;
    }

    @Override
    public void populateList() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                String name = reName(PATH, count);
                GridPaneNode tempNode = new GridPaneNode(i, j, name);
                list.add(tempNode);
                count++;
            }
        }

    }

}
