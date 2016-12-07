
package ui.scenes;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.ResourceBundle;
import editor.EditorController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;


public class AttributeEditor extends Scene{
    
    private final String PATH = "resources/properties/attribute-editor";
  
    private Parent myRoot;
    private ResourceBundle myResources;
    private Stage myStage;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ColorAdjust hoverOpacity;
    private EditorController editorController;
    
    
    public AttributeEditor(Stage stage, Parent root, UILauncher launcher, EditorController control){
        super(root, Color.web("#0585B2"));
        myLauncher = launcher;
        myRoot = root;
        myStage = stage;
        myResources = ResourceBundle.getBundle(PATH);
        myBuilder = new UIBuilder();
        hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.3);
        launchEditor();
        editorController = control;
    }
    
    void launchEditor(){ 
        myBuilder.initWindow(myStage, PATH);
        
        Node backButton = buildButton("backX","backY","backWidth","backText");
        backButton.setOnMouseClicked(e->{
            toGameEditor();
        });
        backButton.setOnMouseEntered(e->{
            backButton.setEffect(hoverOpacity);
        });
        backButton.setOnMouseExited(e->{
            backButton.setEffect(null);
        });

        Node atName = buildField("atNameX","atNameY","atNameWidth","atNameText");
        Node atAmount = buildField("atAmountX","atAmountY","atAmountWidth","atAmountText");
        Node atInc = buildField("atIncX","atIncY","atIncWidth", "atIncText");
        Node atDec = buildField("atDecX","atDecY","atDecWidth","atDecText");
        Node enterButton = buildButton("buildX","buildY","buildWidth","buildText");
        
        enterButton.setOnMouseClicked(e->{
            TextField nameTF = (TextField)atName;
            TextField amountTF = (TextField)atAmount;
            TextField incTF = (TextField)atInc;
            TextField decTF = (TextField)atDec;
            String name = nameTF.getText();
            double amount = Double.parseDouble(amountTF.getText());
            double inc = Double.parseDouble(incTF.getText());
            double dec = Double.parseDouble(decTF.getText());
            editorController.addPlayerAttribute(name, amount);
        });
          
        myStage.setOnCloseRequest(e->{
            e.consume();
            myLauncher.goToPrevCharEditor(myBuilder);
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
    
    private void toGameEditor(){
        myLauncher.launchEditor();
    }
    
    public String getPath(){
        return PATH;
    }
    
    
}
