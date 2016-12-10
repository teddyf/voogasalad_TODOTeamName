package ui.scenes;

import java.util.ResourceBundle;
import editor.EditorController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import ui.builder.UIBuilder;

public class CharacterUI {

    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private Parent myRoot;
    private EditorController myController;
    
    public CharacterUI(Parent root, EditorController controller, ResourceBundle resources){
        myRoot = root;
        myResources = resources;
        myController = controller;
    }
    
    private void initEditorControl(){
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(1);
        String decText = myResources.getString("decText");
        int decX = Integer.parseInt(myResources.getString("decX"));
        int decY = Integer.parseInt(myResources.getString("decY"));
        int decWidth = Integer.parseInt(myResources.getString("decWidth"));
        Node decButton = myBuilder.addCustomButton(myRoot, decText, decX, decY, decWidth);
        decButton.setOnMouseClicked(e->{
            ;
        });
        decButton.setOnMouseEntered(e->{
            decButton.setEffect(hoverOpacity);
        });
        decButton.setOnMouseExited(e->{
            decButton.setEffect(null);
        });
    }
}
