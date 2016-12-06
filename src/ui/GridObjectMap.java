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
    
    public void storeObject(List<Point> a){
        for(int i = 0; i < a.size(); i++){
            for( int j = 0; j < a.size(); i++){
                if(i!=j){
                    if(data.containsKey(a.get(i))){
                        data.get(a.get(i)).add(a.get(j));
                    }
                }
            }
        }
    }
    
    public void collisionRemoval(Point a){
        ArrayList<Point>temp = data.get(a);
        for(int i = 0; i < temp.size(); i++){
            data.put(temp.get(i),new ArrayList<Point>());
        }
    }
    
    public boolean checkCollision(List<Point> a){
        for(int i = 0; i < a.size(); i++){
            if(!data.get(a.get(i)).isEmpty()){
                return true;
            }
        }
        return false;
    }
    
}
