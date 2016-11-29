package ui;

import block.*;
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
    private Block block;
    private BlockType blockType;
    private final String DEFAULT_IMAGE_PATH = "resources/Default.png";
    
    /**
     * Constructor for GridPaneNode 
     * @param row Row for node to be placed
     * @param col Column for node to be placed
     * @param name Name of node
     */
    public GridPaneNode(int row, int col, String name, BlockType blockType){
        this.row = row;
        this.col = col;
        this.name = name;
        this.imageView = new ImageView();
        setInitialImage();
        //default, hard coded values for now
        blockType = BlockType.DECORATION;
        this.blockType = blockType;
        block = new DecorationBlock("Decoration", row, col);
    }
    public void setImage(ImageView image){
        imageView = image;
    }
    //use this method to update cell's information, names are hard coded
    public void setBlockType(BlockType type){
        blockType = type;
        if(type == BlockType.DECORATION){
            block = new DecorationBlock("Decoration", row, col);
        }
        else if(type == BlockType.COMMUNICATOR){
            block = new CommunicatorBlock("Communicator", row, col);
        }
        else if(type == BlockType.OBSTACLE){
            block = new ObstacleBlock("Obstacle", row, col);
        }
        else if(type == BlockType.ENEMY) {
            block = new EnemyBlock("Enemy", row, col);
        }
    }
    public void setImageCoord(double x, double y){
        imageView.setX(x);
        imageView.setY(y);
    }
    public void displayOptions(){
        System.out.println(row + " " + col);
    }
    public void setImageSize(double x, double y){
        this.imageView.setFitWidth(x);
        this.imageView.setFitHeight(y);
    }
    
    private void setInitialImage(){
        Image image = new Image(DEFAULT_IMAGE_PATH);
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
        this.block = node.block;
        this.blockType = node.blockType;
        //this.typeNum = typeNum;
        this.name = type;
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
}
