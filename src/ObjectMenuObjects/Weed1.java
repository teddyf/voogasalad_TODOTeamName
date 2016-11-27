package ObjectMenuObjects;

import grid.GridPaneNode;
import javafx.scene.image.Image;

public class Weed1 extends GameObjects {
	 private final String PATH = "resources/images/Sprites/Declaration/Weed/weed1";
	    public Weed1() {
	        super();
	        Image image = new Image(PATH+".png");
	        this.imageView.setImage(image);
	        type = "flower";
	        path = PATH;
	    }

	    @Override
	    public void populateList () {
	        String name = reName(PATH,1);
	        GridPaneNode tempNode = new GridPaneNode(0,0,name);
	        list.add(tempNode);
	    }
}
