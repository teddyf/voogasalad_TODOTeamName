package ObjectMenuObjects;
import java.util.*;
import grid.*;


public abstract class GameObjects {
    
    protected String name; 
    protected List<GridPaneNode> list;
    
    public GameObjects(String name){
        this.name = name;
        list = new ArrayList<GridPaneNode>();
        populateList();
    }
    public List<GridPaneNode> getList(){
        return list;
    }
    
    protected String reName(String a, int count){
        String[] nameSplit = a.split("\\.");
        String type = nameSplit[0];
        return type+count;
    }
    
    public abstract void populateList();
    
}
