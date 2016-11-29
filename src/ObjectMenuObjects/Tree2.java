package ObjectMenuObjects;
import block.BlockType;
import javafx.scene.image.Image;
import ui.GridPaneNode;

public class Tree2 extends GameObjects{
    private final String PATH = "resources/images/Sprites/Obstacle/Tree/tree1";
    public Tree2 () {
        super();
        Image image = new Image(PATH+".png");
        this.imageView.setImage(image);
        type = "tree2";
        path = "resources/images/Sprites/Obstacle/Tree/tree2";
    }

    @Override
    public void populateList () {
        int count = 1;
        for(int i = 0; i < 3 ;i++){
            for(int j = 0; j < 4; j++){
                String name = reName(PATH,count);
                GridPaneNode tempNode = new GridPaneNode(i,j,name,BlockType.OBSTACLE);
                list.add(tempNode);
                count++;
            }
        }
        
    }
    
}