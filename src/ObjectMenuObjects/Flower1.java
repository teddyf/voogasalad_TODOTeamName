package ObjectMenuObjects;

import java.util.*;
import grid.GridPaneNode;

public class Flower1 extends GameObjects{

    public Flower1 (String name) {
        super(name);
    }

    @Override
    public void populateList () {
        GridPaneNode tempNode = new GridPaneNode(0,0,name);
        list.add(tempNode);
    }

}
