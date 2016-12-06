package ui.scenes.editor.objects;

import java.util.*;

public class ItemPanelObjects {
    private List<GameObject> groundObjs;
    private List<GameObject> decorObjs;
    private List<GameObject> obstacleObjs;
    private List<GameObject> switchObjs;

    private GameObject selected = null;


    public ItemPanelObjects() {
        groundObjs = new ArrayList<GameObject>();
        decorObjs = new ArrayList<GameObject>();
        obstacleObjs = new ArrayList<GameObject>();
        switchObjs = new ArrayList<GameObject>();
        populateGroundObjs();
        populateDecorObjs();
        populateObstacleObjs();
        populateSwitchObjs();
        populateTeleObjs();
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
    
    private void populateTeleObjs(){
        teleObjs.add(new Tele1());
    }
    
    public List<GameObjects> getTeleObjs(){
        return teleObjs;
    }

    public List<GameObject> getGroundObjs() {
        return groundObjs;
    }

    public List<GameObject> getObstacleObjs() {
        return obstacleObjs;
    }

    public List<GameObject> getDecorObjs() {
        return decorObjs;
    }
    
    public List<GameObject> getSwitchObjs() {
        return switchObjs;
    }

    public void select(GameObject obj) {
        selected = obj;
    }

    public GameObject getSelected() {
        return selected;
    }

}