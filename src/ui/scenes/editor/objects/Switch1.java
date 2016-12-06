package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Switch1 extends GameObjects{
    
    private final String PATH = "resources/images/tiles/Links/switch-1";

    public Switch1(){
        super();
        Image image = new Image(PATH + ".png");
        imageView.setImage(image);
        path = PATH;
        blockType = BlockType.SWITCH_FLOOR;
    }
    
    @Override
    public void populateList () {
        String name = PATH + ".png";
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        list.add(tempNode);
        
    }

}
