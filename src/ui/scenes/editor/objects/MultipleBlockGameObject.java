package ui.scenes.editor.objects;

import grid.Grid;
import ui.GridPaneNode;
import block.BlockType;

/**
 * Created by Harshil Garg on 12/7/16.
 */
public class MultipleBlockGameObject extends GameObject {

    private String [][] myDisplayPaths;

    public MultipleBlockGameObject(String iconPath, BlockType blockType, int rows, int columns) {
        super(iconPath, blockType);
        myDisplayPaths = new String[rows][columns];
        populateList();
    }

    public void populateList() {
        int index = myIconPath.indexOf('.');

        for (int i = 0; i < myDisplayPaths.length; i++) {
            for (int j = 0; j < myDisplayPaths[0].length; j++) {
                int i2 = i + 1;
                int j2 = j + 1;
                myDisplayPaths[i][j] = myIconPath.substring(0, index) + "." + i2 + "_" + j2 + myIconPath.substring(index);
                GridPaneNode node = new GridPaneNode(i, j, myDisplayPaths[i][j]);
                myImageTiles.add(node);
            }
        }
    }


}
