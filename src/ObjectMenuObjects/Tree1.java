package ObjectMenuObjects;
import grid.*;

public class Tree1 extends GameObjects{

    public Tree1 (String name) {
        super(name);
    }

    @Override
    public void populateList () {
        for(int i = 0; i < 2 ;i++){
            for(int j = 0; j < 3; j++){
                name = reName(name,i+j);
                GridPaneNode tempNode = new GridPaneNode(i,j,name);
                list.add(tempNode);
            }
        }
        
    }

}
