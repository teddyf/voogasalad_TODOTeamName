package ui.scenes;

import engine.EngineController;
import ui.FileBrowser;
import ui.GridPane;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import player.PlayerDirection;
import ui.UILauncher;
import ui.builder.UIBuilder;
import ui.scenes.editor.StatsDisplayUI;
import ui.scenes.engine.GridDisplayer;

import java.io.File;
import java.util.ResourceBundle;

import engine.UserInstruction;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game engine that is used to run games.
 *
 *         Dependencies: FileBrowser.java
 */
public class GameEngine extends Scene {

    private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
	private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private GridPane gridPane;
    
    private EngineController myController;
    
    private VoogaAnimation anim;
    
    private GridDisplayer gd;

    public GameEngine(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
        myController = new EngineController();
    }

    /**
     * Initializes the game engine window and prompts the user to choose a game
     * file to open for playing
     *
     * @return true if initialization was successful and a valid game file was chosen
     */
    public boolean init() {
        File gameFile = new FileBrowser().openEditorFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile == null) { // user clicked cancel
            return false;
        }
        EngineController gameController = new EngineController();
        gameController.loadEngine(gameFile.getAbsolutePath());

    	
    	setUpGrid();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
    	//myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        return true;
    }
    
    private void setUpGrid() {
    	
    	//EngineDisplayer ed = new EngineDisplayer(myController);
    	
    	setUpKeys();

    	gridPane =
                new GridPane(Integer.parseInt(myResources.getString("gridCellsWide")),
                             Integer.parseInt(myResources.getString("gridCellsHeight")),
                             Integer.parseInt(myResources.getString("gridWidth")),
                             Integer.parseInt(myResources.getString("gridHeight")),
                             Integer.parseInt(myResources.getString("gridX")),
                             Integer.parseInt(myResources.getString("gridY")));

    	//gridPane.getNodeList().get(1250).setImage(new ImageView("resources/flower.png"));
    	//gridPane.setRenderMap();
    	Character player = new Character();
    	player.setColumn((int)(gridPane.getWidth()-1) /2);
    	player.setRow(((int)gridPane.getHeight()-1) /2);
    	player.setCharacterImage("resources/images/sprites/Character/Pokemon/Player1SouthFacing.png");
    	anim = new VoogaAnimation(myRoot, gridPane, player, myBuilder);
    	
    	//gd = new GridDisplayer(gridPane);
    	
    	System.out.println(gridPane.getWidth());
    	System.out.println(gridPane.getHeight());
    	

    	myBuilder.addComponent(myRoot, gridPane.getGroup());
    	
//    	Group g = new Group();
//    	g.getChildren().add(player.getCharacterImageView());
    	player.getCharacterImageView().setLayoutX(Integer.parseInt(myResources.getString("gridWidth"))/2);
    	player.getCharacterImageView().setLayoutY(Integer.parseInt(myResources.getString("gridHeight"))/2);
    	myBuilder.addComponent(myRoot, player.getCharacterImageView());
    	
    	StatsDisplayUI statusUI = new StatsDisplayUI(myRoot,myBuilder,myResources);
    	statusUI.initPlayerMenu();
    }
    
    private void setUpKeys() {
    	setOnKeyPressed(e -> anim.handleKeyPress(e));
    	setOnKeyReleased(e -> anim.handleKeyRelease(e));
    }
    
//    private void onKeyPress(KeyEvent e) {
//    	KeyCode code = e.getCode();
//    	switch (code) {
//    		case UP:
//    			gd.updateDisplay(gridPane.getGroup(), PlayerDirection.NORTH);
//    			//myController.keyListener(UserInstruction.UP);
//    			break;
//    		case DOWN:
//    			gd.updateDisplay(gridPane.getGroup(), PlayerDirection.SOUTH);
//    			//myController.keyListener(UserInstruction.DOWN);
//    			break;
//    		case LEFT:
//    			//myController.keyListener(UserInstruction.LEFT);
//    			break;
//    		case RIGHT:
//    			//myController.keyListener(UserInstruction.RIGHT);
//    			break;
//    		default:
//    			break;
//    	}
//    }
}
