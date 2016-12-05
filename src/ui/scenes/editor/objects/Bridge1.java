package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Bridge1 extends GameObjects {

    private final String PATH = "resources/images/tiles/decorations/bridge-1";

    public Bridge1() {
        super();
        Image image = new Image(PATH + ".png");
        this.imageView.setImage(image);
        path = PATH;
        blockType = BlockType.OBSTACLE;
    }

    @Override
    public void populateList() {
        int count = 1;
        for (int i = 0; i < 2; i++) {
            String name = reName(PATH, count);
            GridPaneNode tempNode = new GridPaneNode(0, i, name);
            list.add(tempNode);
            count++;
        }
    }

}
