package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Tree2 extends GameObject {
    private final String PATH = "resources/images/Sprites/Obstacle/Tree/tree1";

    public Tree2() {
        super();
        Image image = new Image(PATH + ".png");
        this.myImageView.setImage(image);
        myIconPath = "resources/images/Sprites/Obstacle/Tree/tree2";
        myBlockType = BlockType.OBSTACLE;
    }

    @Override
    public void populateList() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String name = rename(PATH, count);
                GridPaneNode tempNode = new GridPaneNode(i, j, name);
                myImageTiles.add(tempNode);
                count++;
            }
        }

    }

}