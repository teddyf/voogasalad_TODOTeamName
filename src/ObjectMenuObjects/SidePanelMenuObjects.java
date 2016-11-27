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
        Tree1 tree1 = new Tree1();
        Tree2 tree2 = new Tree2();
        obstacles.add(tree1);
        obstacles.add(tree2);
    }
    
    private void populateDecorations(){
        Flower1 flower1 = new Flower1();
        decorations.add(flower1);
    }
    
    public List<GameObjects> getObstacles(){
        return obstacles;
    }
    
    public List<GameObjects> getDecorations(){
        return decorations;
    }
    
    public void select(GameObjects obj){
        selected = obj;
    }
    
    public GameObjects getSelected(){
        return selected;
    }
    
}
