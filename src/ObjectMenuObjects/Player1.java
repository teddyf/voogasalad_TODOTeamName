package ObjectMenuObjects;

import javafx.scene.image.Image;
import ui.GridPaneNode;
import block.BlockType;

public class Player1 extends GameObjects {
	 private final String PATH = "resources/images/Sprites/Character/Pokemon/default";
	    public Player1() {
	        super();
	        Image image = new Image(PATH+".png");
	        imageView.setImage(image);
	        type = "flower";
	        path = "resources/images/Sprites/Character/Pokemon/default";
	        blockType = BlockType.DECORATION;
	    }

	    @Override
	    public void populateList () {
	        String name = reName(PATH,1);
	        GridPaneNode tempNode = new GridPaneNode(0,0,name);
	        list.add(tempNode);
	    }
}
