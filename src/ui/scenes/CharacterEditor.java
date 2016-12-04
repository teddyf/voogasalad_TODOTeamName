
package ui.scenes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import editor.EditorController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;


public class CharacterEditor extends Scene{
    
    private final String PATH = "resources/properties/character-editor";
    
    private Parent myRoot;
    private ResourceBundle myResources;
    private Stage myStage;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ColorAdjust hoverOpacity;
    private List<String> imagePaths;
    private String selectedImage;
    private int imageIndex;
    private EditorController myController;
    
    
    public CharacterEditor(Stage stage, Parent root, UILauncher launcher, EditorController controller){
        super(root, Color.web("#0585B2"));
        myLauncher = launcher;
        myController = controller;
        myRoot = root;
        myStage = stage;
        myResources = ResourceBundle.getBundle(PATH);
        myBuilder = new UIBuilder();
        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.3);
        imagePaths = new ArrayList<String>();
        launchEditor();
    }
    
    void launchEditor(){ 
        myBuilder.initWindow(myStage, PATH);
        addDefaultPlayers();
        
        EditorController editorController = new EditorController();
        
        Node backButton = buildButton("backX","backY","backWidth","backText");
        backButton.setOnMouseClicked(e->{
        });
        backButton.setOnMouseEntered(e->{
            backButton.setEffect(hoverOpacity);
        });
        backButton.setOnMouseExited(e->{
            backButton.setEffect(null);
        });
        
        Node attributeButton = buildButton("atButtX","atButtY","atButtWidth","atButtText");;
        attributeButton.setOnMouseClicked(e->{
            myLauncher.launchAttributePopup();
        });
        attributeButton.setOnMouseEntered(e->{
            attributeButton.setEffect(hoverOpacity);
        });
        attributeButton.setOnMouseExited(e->{
            attributeButton.setEffect(null);
        });
        
        
        Node nameField = buildField("nameX","nameY","nameWidth","nameText");
        Node xField = buildField("xX","xY","xWidth", "xText");
        Node yField = buildField("yX","yY","yWidth","yText");
        Node enterButton = buildButton("buildX","buildY","buildWidth","buildText");
        enterButton.setOnMouseClicked(e->{
            TextField nameP = (TextField)nameField;
            TextField xP = (TextField)xField;
            TextField yP = (TextField)yField;
            try{
                String name = nameP.getText();
                int x = Integer.parseInt(xP.getText());
                int y = Integer.parseInt(yP.getText());
                myController.addPlayer(name, y, x);
            }
            catch(Exception exc){
                myBuilder.addNewAlert("Invalid Input", "Enter valid values for player info");
            }
            
            
        });
        Node playerImage = buildImage("pngX","pngY","pngWidth","pngPath");
        Node playerRight = buildButton("rightX","rightY","rightWidth","rightText");
        playerRight.setOnMouseClicked(e->{
            rotateImage(1,playerImage);
        });
        
        Node playerLeft = buildButton("leftX","leftY","leftWidth","leftText");
        playerLeft.setOnMouseClicked(e->{
            rotateImage(-1,playerImage);
        });
        
          
        myStage.setOnCloseRequest(e->{
            e.consume();
            myLauncher.goToPrevScene(myBuilder);
        });
        myStage.setScene(this);   
    }
    
    private Node buildButton(String xPos, String yPos, String width, String path){
        int x = Integer.parseInt(myResources.getString(xPos));
        int y = Integer.parseInt(myResources.getString(yPos));
        int girth = Integer.parseInt(myResources.getString(width));
        String route = myResources.getString(path);
        Node node = myBuilder.addCustomButton(myRoot, route, x, y, girth);
        node.setOnMouseEntered(e->{
            node.setEffect(hoverOpacity);
        });
        node.setOnMouseExited(e->{
            node.setEffect(null);
        });
        return node;
        
    }
    
    private Node buildField(String xPos, String yPos, String width, String path){
        int x = Integer.parseInt(myResources.getString(xPos));
        int y = Integer.parseInt(myResources.getString(yPos));
        int girth = Integer.parseInt(myResources.getString(width));
        String route = myResources.getString(path);
        Node node = myBuilder.addCustomTextField(myRoot, route, x, y, girth);
        return node;
    }
    
    private Node buildImage(String xPos, String yPos, String width, String path){
        int x = Integer.parseInt(myResources.getString(xPos));
        int y = Integer.parseInt(myResources.getString(yPos));
        int girth = Integer.parseInt(myResources.getString(width));
        String route = myResources.getString(path);
        Node node = myBuilder.addCustomButton(myRoot, route, x, y, girth);
        return node;
    }
    
    private void addDefaultPlayers(){
        imagePaths.add("resources/images/Sprites/Character/Pokemon/Player1NorthFacing.png");
        imagePaths.add("resources/images/Sprites/Character/Pokemon/Player2NorthFacing.png");
        imagePaths.add("resources/images/Sprites/Character/Pokemon/Player1SouthFacing.png");
        imagePaths.add("resources/images/Sprites/Character/Pokemon/Player2SouthFacing.png");
    }
    
    
    private void rotateImage(int dir, Node node){
        imageIndex+=dir;
        if(imageIndex<0){
            imageIndex = imagePaths.size()-1;
        }
        else if(imageIndex >= imagePaths.size()){
            imageIndex = 0;
        }
        ImageView nImage = (ImageView)node;
        nImage.setImage(new Image(imagePaths.get(imageIndex)));
    }
    
    private void toGameEditor(){
        myLauncher.launchEditor();
    }
    
    public String getPath(){
        return PATH;
    }
    
    
}
