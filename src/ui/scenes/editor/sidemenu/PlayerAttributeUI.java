package ui.scenes.editor.sidemenu;

import java.util.Observable;

import ui.builder.UIBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerAttributeUI extends Observable{
	private Group group;
	private VBox vbox;
	private UIBuilder uiBuilder;
	private TextField nameTextField;
	private TextField incrementTextField;
	private TextField decrementTextField;
	private TextField amountTextField;
	private double decrement;
	private double increment;
	private double amount;
	private String name;
	
	PlayerAttributeUI(PlayerMenuUI playerMenuUI) {
		group = new Group();
		vbox = getCustomizedVBox();
		addFields();
		this.addObserver(playerMenuUI);
	}
	
	private void addFields() {
		
		//adding name box
		HBox nameBox = getCustomizedHBox();
		Label nameLabel = new Label("Name");
		nameTextField = new TextField();
		nameBox.getChildren().addAll(nameLabel,nameTextField);
		
		//adding decrement box
		HBox decrementBox = getCustomizedHBox();
		Label decrementLabel = new Label("Decrement");
		decrementTextField = new TextField();
		decrementBox.getChildren().addAll(decrementLabel,decrementTextField);
		
		//adding increment box
		HBox incrementBox = getCustomizedHBox();
		Label incrementLabel = new Label("Increment");
		incrementTextField = new TextField();
		incrementBox.getChildren().addAll(incrementLabel,incrementTextField);
		
		//adding amount box
		HBox amountBox = getCustomizedHBox();
		Label amountLabel = new Label("Increment");
		amountTextField = new TextField();
		amountBox.getChildren().addAll(amountLabel,amountTextField);
		
		//adding submit button
		Button submitButton = new Button("Update!");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	name = nameTextField.getText();
            	increment = Double.parseDouble(incrementTextField.getText());
            	decrement = Double.parseDouble(decrementTextField.getText());
            	amount = Double.parseDouble(amountTextField.getText());
            	
            	//this update the editorcontroller
            	setChanged();
            	notifyObservers();
            }
        }); 
		
		vbox.getChildren().addAll(nameBox,decrementBox,incrementBox,amountBox,submitButton);
		group.getChildren().add(vbox);
	}
	
	private HBox getCustomizedHBox() {
		HBox hbox = new HBox(20);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		return hbox;
	}
	
	private VBox getCustomizedVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 10, 10, 10));
		return vbox;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public double getIncrement() {
		return increment;
	}
	
	public double getDecrement() {
		return decrement;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getName() {
		return name;
	}
}
