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
    
    private ArrayList<Point> sharesObjectWith(int x,int y){
        try{
            ArrayList<Point> neighbors = data.get(new Point(x,y));
            return neighbors;
        }
        catch(Exception e){
            return new ArrayList<Point>();
        }
    }
    
    public ArrayList<Integer> sharesObjWith(int x, int y){
        ArrayList<Point> neighbors = sharesObjectWith(x,y);
        ArrayList<Integer> points = new ArrayList<Integer>();
        for(int i = 0; i < neighbors.size(); i++){
            points.add((int)neighbors.get(i).getX());
            points.add((int)neighbors.get(i).getY());
        }
        return points;
    }
    
    public boolean storeObject(List<GridPaneNode> a){
        ArrayList<Point> temp = new ArrayList<Point>();
        for(int i = 0; i < a.size();i++){
            temp.add(nodeToPoint(a.get(i)));
        }
        for(int i = 0; i < temp.size(); i++){
            for( int j = 0; j < temp.size(); j++){
                //if(i!=j){
                    if(data.containsKey(temp.get(i))){
                        data.get(temp.get(i)).add(temp.get(j));
                    }
               // }
            }
        }
        return true;
    }
    
    
    public void collisionRemoval(int row, int col){
        Point a = new Point(col,row);
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
                collisionRemoval(row,col);
            }
        }
    }
    
    private Point nodeToPoint(GridPaneNode node){
        return new Point(node.getCol(), node.getRow());
    }
    
    public String toString(){
        return data.toString();
    }
    
    public Map<Point,ArrayList<Point>> getMap(){
        return data;
    }
    
  
//    //Debugger methods for Deletion
//    public void visObjectMap(){
//        for(int i = 0; i < height; i++){
//            for(int j = 0; j < width; j++){
//                if(data.get(new Point(j,i)).isEmpty()){
//                    System.out.print("O");
//                }
//                else
//                    System.out.print("x");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        visObjectMapNums();
//        System.out.println(data);
//    }
//
//    public void visObjectMapNums(){
//        for(int i = 0; i < height; i++){
//            for(int j = 0; j < width; j++){
//                if(!data.get(new Point(j,i)).isEmpty()){
//                    System.out.print("("+i+","+j+")");
//                }
//            }
//        }
//        System.out.println();
//    }
    
    
}