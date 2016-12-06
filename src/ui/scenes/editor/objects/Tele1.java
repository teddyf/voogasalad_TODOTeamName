package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Tele1 extends GameObjects{

    private final String PATH = "resources/images/tiles/Teleporters/tele1";
    public Tele1(){
        super();
        Image image = new Image(PATH + ".png");
        imageView.setImage(image);
        path = PATH;
        blockType = BlockType.TELEPORT;
    }
    
    @Override
    public void populateList () {
        String name = PATH + ".png";
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        System.out.println(name);
        list.add(tempNode);     
    }

}
