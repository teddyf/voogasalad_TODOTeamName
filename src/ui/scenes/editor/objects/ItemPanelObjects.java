package ui.scenes.editor.objects;

import java.util.*;

public class ItemPanelObjects {
    private List<GameObjects> groundObjs;
    private List<GameObjects> decorObjs;
    private List<GameObjects> obstacleObjs;
    private List<GameObjects> switchObjs;

    private GameObjects selected;


    public ItemPanelObjects() {
        groundObjs = new ArrayList<GameObjects>();
        decorObjs = new ArrayList<GameObjects>();
        obstacleObjs = new ArrayList<GameObjects>();
        switchObjs = new ArrayList<GameObjects>();
        populateGroundObjs();
        populateDecorObjs();
        populateObstacleObjs();
        populateSwitchObjs();
    }

    private void populateGroundObjs() {
        groundObjs.add(new Grass1());
        groundObjs.add(new Dirt1());
        groundObjs.add(new Sand1());
        groundObjs.add(new Snow1());
        groundObjs.add(new Water1());
    }

    private void populateDecorObjs() {
        decorObjs.add(new Weed1());
        decorObjs.add(new Weed2());
        decorObjs.add(new Flower1());
        decorObjs.add(new Flower2());
        decorObjs.add(new Flower3());
        decorObjs.add(new Bridge1());
    }

    private void populateObstacleObjs() {
        obstacleObjs.add(new Tree1());
        obstacleObjs.add(new Rock1());
        obstacleObjs.add(new Sign1());
    }
    
    private void populateSwitchObjs(){
        switchObjs.add(new Switch1());
    }

    public List<GameObjects> getGroundObjs() {
        return groundObjs;
    }

    public List<GameObjects> getObstacleObjs() {
        return obstacleObjs;
    }

    public List<GameObjects> getDecorObjs() {
        return decorObjs;
    }
    
    public List<GameObjects> getSwitchObjs() {
        return switchObjs;
    }

    public void select(GameObjects obj) {
        selected = obj;
    }

    public GameObjects getSelected() {
        return selected;
    }

}
