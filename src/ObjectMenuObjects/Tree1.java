package ObjectMenuObjects;
import grid.*;
import javafx.scene.image.Image;

public class Tree1 extends GameObjects{
    private final String PATH = "resources/images/Sprites/Obstacle/Tree/tree1";
    public Tree1 () {
        super();
        Image image = new Image(PATH+".png");
        this.imageView.setImage(image);
        type = "tree1";
        path = "resources/images/Sprites/Obstacle/Tree/tree1";
    }

    @Override
    public void populateList () {
        int count = 1;
        for(int i = 0; i < 3 ;i++){
            for(int j = 0; j < 2; j++){
                String name = reName(PATH,count);
                GridPaneNode tempNode = new GridPaneNode(i,j,name);
                list.add(tempNode);
                count++;
            }
        }
        
    }
    
}
