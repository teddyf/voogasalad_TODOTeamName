package ui.scenes.editor.objects;

import block.BlockType;
import ui.GridPaneNode;

/**
 * Created by harshilgarg on 12/7/16.
 */
public class SingleBlockGameObject extends GameObject {

    public SingleBlockGameObject(String iconPath, BlockType blockType) {
        super(iconPath, blockType);
    }

    public void populateList() {
        String name = myIconPath;
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        myImageTiles.add(tempNode);
    }

}
