package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Bridge1 extends GameObject {

    private final String PATH = "resources/images/tiles/decoration/bridge-1";

    public Bridge1() {
        super();
        Image image = new Image(PATH + ".png");
        this.myImageView.setImage(image);
        myIconPath = PATH;
        myBlockType = BlockType.OBSTACLE;
    }

    @Override
    public void populateList() {
        int count = 1;
        for (int i = 0; i < 2; i++) {
            String name = rename(PATH, count);
            GridPaneNode tempNode = new GridPaneNode(0, i, name);
            myImageTiles.add(tempNode);
            count++;
        }
    }

}
