package ui.scenes.editor.objects;

import block.BlockType;
import javafx.scene.image.Image;

public class Switch1 extends GameObjects{
    
    private final String PATH = "resources/images/tiles/decorations/flower-1";

    public Switch1(){
        super();
        Image image = new Image(PATH + ".png");
        imageView.setImage(image);
        path = PATH;
        blockType = BlockType.DECORATION;
    }
    
    @Override
    public void populateList () {
        // TODO Auto-generated method stub
        
    }

}
