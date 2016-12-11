package ui;

import java.awt.Point;
import java.util.*;

public class TeddysTester {
    static GridObjectMap map = new GridObjectMap(10,10);
    static List<GridPaneNode> list = new ArrayList<GridPaneNode>();
    static GridPaneNode node = new GridPaneNode(0,0,"resources/images/tiles/decorations/grass-3.png");
    static GridPaneNode node2 = new GridPaneNode(0,1, "resources/images/tiles/decorations/grass-3.png");
    static GridPaneNode node3 = new GridPaneNode(1,0,"resources/images/tiles/decorations/grass-3.png");
    static GridPaneNode node4 = new GridPaneNode(1,1,"resources/images/tiles/decorations/grass-3.png");
    static GridPaneNode node5 = new GridPaneNode(1,2,"resources/images/tiles/decorations/grass-3.png");
    public static void main(String[] args){
        list.add(node);
        list.add(node3);
        list.add(node2);
        list.add(node4);
        list.add(node5);
        map.storeObject(list);
        //map.collisionRemoval(1, 1);
        map.collisionRemoval(2, 1);
    }
    
    static int occupied(){
        int count = 0;
        for(Point p: map.getMap().keySet()){
            if(!map.getMap().get(p).isEmpty()){
                count++;
            }
        }
        return count;
    }
}
