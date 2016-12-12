package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GridPaneNode {
    private int row;
    private int col;

    private String type;
    private int imageNum;
    //private int typeNum;
    private String name;
    private ImageView imageView;

    /**
     * Constructor for GridPaneNode 
     * @param row Row for node to be placed
     * @param col Column for node to be placed
     * @param name Name of node
     */
    public GridPaneNode(int row, int col, String name){
        this.row = row;
        this.col = col;
        this.name = name;
        this.imageView = new ImageView();
        setInitialImage();
        //default, hard coded values for now
    }
    public void setImage(ImageView image){
        imageView = image;
    }

    
    public void setImageCoord(double x, double y){
        imageView.setX(x);
        imageView.setY(y);
    }

    public void setImageSize(double x, double y){
        this.imageView.setFitWidth(x);
        this.imageView.setFitHeight(y);
    }
    
    private void setInitialImage(){
        Image image = new Image(name);
        imageView.setImage(image);
    }
    
    
    private String getPath(){
        String sol = "";
        return sol;
    }
    
    public void swap(GridPaneNode node, int typeNum){
        Image image = new Image(node.getName());
        this.imageView.setImage(image);
        this.imageNum = node.getImageNum();
        this.name = node.getName();
    }
    
    //Getters
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public String getType(){
        return type;
    }
    
    public ImageView getImage(){
        return imageView;
    }
    
    //Setters
    
    public void setName(String a){
        this.name = a;
    }
    
    public String getName(){
        return name;
    }
    
    public int getImageNum(){
        return this.imageNum;
    }
    public String toString(){
        String sol = "row: " + row + "\ncol: " + col + "\nname: " + name;
        return sol;
    }
    public void setCol (int col) {
        this.col = col;
        
    }
    public void setRow (int row){
        this.row = row;
    }
}
