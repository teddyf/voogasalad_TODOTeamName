package ObjectMenuObjects;
import grid.*;
import java.util.*;

public class SidePanelMenuObjects {
    private List<GameObjects> obstacles;
    private List<GameObjects> decorations;
    private GameObjects selected;
    
    
    public SidePanelMenuObjects(){
        obstacles  = new ArrayList<GameObjects>();
        decorations = new ArrayList<GameObjects>();
        populateObstacles();
        populateDecorations();
    }
    
    private void populateObstacles(){
        obstacles.add(new Tree1());
    }
    
    private void populateDecorations(){
        decorations.add(new Flower1());
        decorations.add(new Flower2());
        decorations.add(new Grass1());
        decorations.add(new Weed1());
        decorations.add(new Dirt1());
        
    }
    
    public List<GameObjects> getObstacles(){
        return obstacles;
    }
    
    public List<GameObjects> getDecorations(){
        return decorations;
    }
    
    public List<GameObjects> getDecorationsHBoxList(){
        return decorations;
    }
    
    public void select(GameObjects obj){
        selected = obj;
    }
    
    public GameObjects getSelected(){
        return selected;
    }
    
}
