package ui.scenes.editor;

import java.util.ResourceBundle;

import resources.properties.PropertiesUtilities;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Robert Steilberg
 */
public class SizeChooser2 extends Scene {
	
	public static final String SIZE_CHOOSER_RESOURCES = "resources/properties/size-chooser-2";
	private static final String CSS_FILE_NAME = "resources/styles/size-chooser-3.css"; 
	
	private String [] labelsProperties = {"XPos", "YPos", "Size", "Text", "Id"};
	private String [] labels = {"header", "prompt", "title", "description", "dimensions"};
	
	private String [] inputsProperties = {"XPos", "YPos", "Width", "Height", "Text", "Id"};
	private String [] inputs = {"title-field", "description-field", "rows", "columns"};
	
	private String [] buttonsProperties = {"XPos", "YPos", "Width", "Text", "Id"};
	private String [] buttons = {"create"};
	
	private GameEditor myEditor;
	private Parent myRoot;
	private UIBuilder myBuilder;
	private ResourceBundle myResources;
	private PropertiesUtilities myUtil;
	
	public SizeChooser2(GameEditor editor, Parent root) {
		super(root, Color.web("#282828"));
		
		myRoot = root;
		myEditor = editor;
		myResources = ResourceBundle.getBundle(SIZE_CHOOSER_RESOURCES);
        myUtil = new PropertiesUtilities();
		myBuilder = new UIBuilder();
		
		myRoot.getStylesheets().add(CSS_FILE_NAME);
		
		initialize();
	}

	private void initialize() {
		setButton();
		setInputs();
		setLabels();
	}
	
	private void setLabels() {
		for (String label : labels) {
			int xPos = myUtil.getIntProperty(myResources, label + labelsProperties[0]);
			int yPos = myUtil.getIntProperty(myResources, label + labelsProperties[1]);
			int size = myUtil.getIntProperty(myResources, label + labelsProperties[2]);
	        String text = myUtil.getStringProperty(myResources, label + labelsProperties[3]);
	        String id = myUtil.getStringProperty(myResources, label + labelsProperties[4]);
	        myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .size(size)
	                .color(Color.WHITE)
	                .id(id));
		}
	}
	
	private void setInputs() {
		for (String input : inputs) {
			int xPos = myUtil.getIntProperty(myResources, input + inputsProperties[0]);
			int yPos = myUtil.getIntProperty(myResources, input + inputsProperties[1]);
			int width = myUtil.getIntProperty(myResources, input + inputsProperties[2]);
			int height = myUtil.getIntProperty(myResources, input + inputsProperties[3]);
	        String text = myUtil.getStringProperty(myResources, input + inputsProperties[4]);
	        String id = myUtil.getStringProperty(myResources, input + inputsProperties[5]);
	        myBuilder.addNewTextField(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .width(width)
	                .height(height)
	                .color(Color.WHITE)
	                .id(id));
		}
	}
	
	private void setButton() {
		for (String button : buttons) {
			int xPos = myUtil.getIntProperty(myResources, button + buttonsProperties[0]);
			int yPos = myUtil.getIntProperty(myResources, button + buttonsProperties[1]);
			int width = myUtil.getIntProperty(myResources, button + buttonsProperties[2]);
	        String text = myUtil.getStringProperty(myResources, button + buttonsProperties[3]);
	        String id = myUtil.getStringProperty(myResources, button + buttonsProperties[4]);
	        myBuilder.addNewButton(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .width(width)
	                .color(Color.WHITE)
	                .id(id));
		}
	}

}
