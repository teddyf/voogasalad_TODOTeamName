package ui.scenes.editor;

import java.util.ResourceBundle;

import resources.properties.PropertiesUtilities;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

/**
 * @author Harshil Garg, Robert Steilberg
 */
public class SizeChooser2 extends Scene {
	
	public static final String SIZE_CHOOSER_RESOURCES = "resources/properties/size-chooser-2";
	private static final String CSS_FILE_NAME = "resources/styles/size-chooser-2.css"; 
	
	private String [] labelsProperties = {"XPos", "YPos", "Size", "Text", "Id"};
	private String [] labels = {"header", "prompt", "title", "description", "dimensions"};
	
	private String [] inputsProperties = {"XPos", "YPos", "Width", "Height", "Text", "Id"};
	private String [] inputs = {"title-field", "description-field", "rows", "columns"};
	
	private String [] buttonsProperties = {"XPos", "YPos", "Width", "Text", "Id"};
	private String [] buttons = {"create"};
	
	private EditorView myEditor;
	private Parent myRoot;
	private UIBuilder myBuilder;
	private ResourceBundle myResources;
	private PropertiesUtilities myUtil;
	
	private TextField rows;
	private TextField columns;
	
	public SizeChooser2(EditorView editor, Parent root) {
		super(root, Color.web("#282828"));
		
		myRoot = root;
		myEditor = editor;
		myResources = ResourceBundle.getBundle(SIZE_CHOOSER_RESOURCES);
        myUtil = new PropertiesUtilities(myResources);
		myBuilder = new UIBuilder();
		
		myRoot.getStylesheets().add(CSS_FILE_NAME);
		
		initialize();
	}

	private void initialize() {
//		setButton();
		setInputs();
//		setLabels();
	}
	
	private void setLabels() {
		for (String label : labels) {
			int xPos = myUtil.getIntProperty(label + labelsProperties[0]);
			int yPos = myUtil.getIntProperty(label + labelsProperties[1]);
			int size = myUtil.getIntProperty(label + labelsProperties[2]);
	        String text = myUtil.getStringProperty(label + labelsProperties[3]);
	        String id = myUtil.getStringProperty(label + labelsProperties[4]);
	        myBuilder.addNewLabel(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .size(size)
	                .color(Color.WHITE)
	                .id(id));
		}
	}
	
	private void setInputs() {
		for (String input : inputs) {
			int xPos = myUtil.getIntProperty(input + inputsProperties[0]);
			int yPos = myUtil.getIntProperty(input + inputsProperties[1]);
			int width = myUtil.getIntProperty(input + inputsProperties[2]);
			int height = myUtil.getIntProperty(input + inputsProperties[3]);
	        String text = myUtil.getStringProperty( input + inputsProperties[4]);
	        String id = myUtil.getStringProperty(input + inputsProperties[5]);
	        TextField field = (TextField) (myBuilder.addNewTextField(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .width(width)
	                .height(height)
	                .color(Color.WHITE)
	                .id(id)));
	        
	        if (input.equals("rows")) {
	        	rows = field;
	        } else if (input.equals("columns")) {
	        	columns = field;
	        }
		}
	}
	
	private void setButton() {
		for (String button : buttons) {
			int xPos = myUtil.getIntProperty(button + buttonsProperties[0]);
			int yPos = myUtil.getIntProperty( button + buttonsProperties[1]);
			int width = myUtil.getIntProperty(button + buttonsProperties[2]);
	        String text = myUtil.getStringProperty(button + buttonsProperties[3]);
	        String id = myUtil.getStringProperty(button + buttonsProperties[4]);
	        Button myButton = (Button) (myBuilder.addNewButton(myRoot, new ComponentProperties(xPos, yPos)
	                .text(text)
	                .width(width)
	                .color(Color.WHITE)
	                .id(id)));
	        if (button.equals("create")) {
	        	System.out.println("cancer");
	        	myButton.setOnMouseClicked(e -> launchEditor(rows.getText(), columns.getText(), 500));
	        }
		}
	}
	
	private void launchEditor(String row, String column, int maxDim) {
		System.out.println("wtf1");
		if (validateDimensions(row, column, maxDim)) {
			System.out.println("wtf");
			myEditor.launchEditor(Integer.parseInt(row), Integer.parseInt(column));
		}
	}
	
	private boolean validateDimensions(String row, String column, int maxDim) {
		try {
            int widthVal = Integer.parseInt(row);
            int heightVal = Integer.parseInt(column);
            boolean widthEmpty = row.trim().isEmpty();
            boolean heightEmpty = column.trim().isEmpty();
            return !(widthEmpty ||
                    heightEmpty ||
                    widthVal > maxDim ||
                    widthVal <= 0 ||
                    heightVal > maxDim ||
                    heightVal <= 0);
        } catch (NumberFormatException e) {
            return true;
        }
	}

}
