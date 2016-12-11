//package ui.scenes.engine;
//
//import java.io.File;
//import java.util.Observable;
//import java.util.ResourceBundle;
//
//import ui.builder.UIBuilder;
//import ui.scenes.engine.Character;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.VBox;
//
///*
// *
// * This class is to create menu for users to change player pic in engine
// * @author pim chuaylua
// * */
//
//public class CharacterChanger extends Observable {
//
//	private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
//	private ResourceBundle myResources;
//	private Group group;
//	private VBox vbox;
//	private FlowPane flowPane;
//	private Character player;
//	private UIBuilder uiBuilder;
//	private Parent root;
//
//	CharacterChanger(Character player,UIBuilder uiBuilder,Parent root) {
//		this.player=player;
//		this.uiBuilder=uiBuilder;
//		this.root=root;
//		init();
//	}
//
//	private void init() {
//		group = new Group();
//		vbox = new VBox();
//		myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
//		initFlowPane();
//		addPlayerOptions();
//	}
//
//	private void initFlowPane() {
//		flowPane = new FlowPane();
//		flowPane = new FlowPane();
//		flowPane.setPrefWrapLength(300);
//		flowPane.setHgap(20);
//		flowPane.setVgap(20);
//		flowPane.setPadding(new Insets(20, 20, 20, 20));
//		vbox.getChildren().add(new Label("Change player"));
//		vbox.getChildren().add(flowPane);
//		group.getChildren().add(vbox);
//	}
//
//	private void addPlayerOptions() {
//
//		//change this
//		for (int i=1;i<9;i++) {
//			String path = "resources/images/sprites/"+i+"-down.png";
//			Button button = new Button();
//			ImageView imageView = new ImageView(new Image(path));
//			imageView.setFitWidth(30);
//			imageView.setFitHeight(30);
//			button.setGraphic(imageView);
//			button.getStyleClass().add("playerOption");
//			button.setOnAction(new EventHandler<ActionEvent>() {
//	            @Override
//	            public void handle(ActionEvent event) {
//	            	uiBuilder.removeComponent(root,player.getCharacterImageView());
//	            	player.changeCharacterImage(path);
//	            	uiBuilder.addComponent(root,player.getCharacterImageView());
//	            }
//	        });
//
//			flowPane.getChildren().addAll(button);
//		}
//	}
//
//
//	public Group getGroup() {
//		return group;
//	}
//
//
//
//
//
//}
