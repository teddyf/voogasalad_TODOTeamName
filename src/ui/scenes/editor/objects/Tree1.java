package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Tree1 extends GameObject {

    private final String PATH = "resources/images/tiles/obstacle/tree-1";

    public Tree1() {
        super();
        Image image = new Image(PATH + ".png");
        this.myImageView.setImage(image);
        myIconPath = PATH;
        myBlockType = BlockType.OBSTACLE;
    }

    @Override
    public void populateList() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                String name = rename(PATH, count);
                GridPaneNode tempNode = new GridPaneNode(i, j, name);
                myImageTiles.add(tempNode);
                count++;
            }
        }

    }

}
