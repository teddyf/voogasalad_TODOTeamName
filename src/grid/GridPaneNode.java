package grid;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GridPaneNode {
    private int row;
    private int col;
    
    private String type;
    private int imageNum;
    private int typeNum;
    private String name;
    private ImageView imageView;
    
    private final String DEFAULT_IMAGE_PATH = "/resources/Default.png";
    
    public GridPaneNode(int row, int col, String name){
        this.row = row;
        this.col = col;
        this.name = name;
        this.imageView = new ImageView();
        extractName(name);
        setInitialImage();
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
        Image image = new Image(DEFAULT_IMAGE_PATH);
        imageView.setImage(image);
    }
    private void extractName(String a){
        String[] nameSplit = a.split("\\.");
        this.type = nameSplit[0];
        this.imageNum = Integer.parseInt(nameSplit[2]);
        this.typeNum = Integer.parseInt(nameSplit[1]);
    }
    
    
    private String getPath(){
        String sol = "";
        return sol;
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
        extractName(a);
    }
    
    public String toString(){
        String sol = "row: " + row + "\ncol: " + col + "\nname: " + type;
        return sol;
    }
    

    
}
