package ui;
import java.awt.Point;
import java.util.*;

public class GridObjectMap {
    
    private Map<Point,ArrayList<Point>> data;
    private int height;
    private int width;
    
    public GridObjectMap(int width, int height){
        data = new HashMap<Point,ArrayList<Point>>();
        this.height = height;
        this.width = width;
        initMap();
    }
    
    private void initMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                data.put(new Point(j,i), new ArrayList<Point>());
            }
        }
    }
    
    public ArrayList<Point> sharesObjectWith(int x,int y){
        try{
            ArrayList<Point> neighbors = data.get(new Point(x,y));
            return neighbors;
        }
        catch(Exception e){
            return new ArrayList<Point>();
        }
    }
    
    public boolean storeObject(List<GridPaneNode> a){
        ArrayList<Point> temp = new ArrayList<Point>();
        for(int i = 0; i < a.size();i++){
            temp.add(nodeToPoint(a.get(i)));
        }
        for(int i = 0; i < temp.size(); i++){
            for( int j = 0; j < temp.size(); i++){
                if(i!=j){
                    if(data.containsKey(temp.get(i))){
                        data.get(temp.get(i)).add(temp.get(j));
                    }
                }
            }
        }
        return true;
    }
    
    public void collisionRemoval(int row, int col){
        Point a = new Point(row,col);
        ArrayList<Point>temp = data.get(a);
        for(int i = 0; i < temp.size(); i++){
            data.put(temp.get(i),new ArrayList<Point>());
        }
    }
    
    public boolean available(int x, int y){
        Point temp = new Point(x,y);
        if(data.containsKey(temp)){
            if(data.get(temp).isEmpty()){
                return true;
            }
        }
        return false;
    }
    
    public void resizeAdd(int row, int col){
        Point a = new Point(row,col);
        if(!data.containsKey(a)){
            data.put(a, new ArrayList<Point>());
        }
    }
    
    public void resizeRemove(int row, int col){
        Point a = new Point(row,col);
        if(data.containsKey(a)){
            if(data.get(a).isEmpty()){
                data.remove(a);
            }
            else{
                collisionRemoval((int)a.getY(), (int)a.getX());
            }
        }
    }
    
    private Point nodeToPoint(GridPaneNode node){
        return new Point(node.getCol(), node.getRow());
    }
    
}
