package ui.scenes.engine;

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
    private GridForEngine grid;
    
    private EngineController myController;
    
    private VoogaAnimation anim;
    
    private GridDisplayer gd;
    private PlayerUI player;

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
        File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile == null) { // user clicked cancel
            return false;
        }
        myController.loadEngine(gameFile.getAbsolutePath());
        initGrid();
    	loadGrid();
    	setUpGrid();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
    	//myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        return true;
    }
    
    private void setUpGrid() {
    	
    	setUpKeys();
    	setUpPlayer();
    	anim = new VoogaAnimation(myRoot, grid, player, myBuilder);
    	
    	
    	//StatsDisplayUI statusUI = new StatsDisplayUI(myRoot,myBuilder,myResources);
    	//statusUI.initPlayerMenu();
    }
    
    private void setUpPlayer() {
    	player = new PlayerUI();
    	int gridX = Integer.parseInt(myResources.getString("gridX"));
        int gridY = Integer.parseInt(myResources.getString("gridY"));
        int windowWidth = Integer.parseInt(myResources.getString("windowWidth"));
        int windowHeight = Integer.parseInt(myResources.getString("windowHeight"));
        
    	player.setColumn(myController.getPlayerColumn());
    	player.setRow(myController.getPlayerRow());
    	System.out.println(player.getRowCharacter());
    	System.out.println(player.getColumnCharacter());
    	player.setCharacterImage("resources/images/sprites/Character/Pokemon/Player1SouthFacing.png");
        player.setCharacterImageSize(grid.getBlockSize());
    	player.setPosX(gridX+grid.getBlockSize()*myController.getPlayerColumn());
    	player.setPosY(gridY+grid.getBlockSize()*myController.getPlayerRow());
    	myBuilder.addComponent(myRoot, player.getCharacterImageView());
    }
    
    private void setUpKeys() {
    	setOnKeyPressed(e -> anim.handleKeyPress(e));
    	setOnKeyReleased(e -> anim.handleKeyRelease(e));
    }
    
    private void initGrid(){
        int gridCellsWidth = Integer.parseInt(myResources.getString("gridCellsWide"));
        int gridCellsHeight = Integer.parseInt(myResources.getString("gridCellsHeight"));
        int gridWidth = Integer.parseInt(myResources.getString("gridWidth"));
        int gridHeight = Integer.parseInt(myResources.getString("gridHeight"));
        int gridX = Integer.parseInt(myResources.getString("gridX"));
        int gridY = Integer.parseInt(myResources.getString("gridY"));
        grid = new GridForEngine(gridCellsWidth, gridCellsHeight, gridWidth, gridHeight, gridX, gridY);
    }
    
    public void loadGrid(){
        int colMax = myController.getGameInstance().getGrid().getNumCols();
        int rowMax = myController.getGameInstance().getGrid().getNumRows();
        grid.loadReset(rowMax, colMax);
        myBuilder.removeComponent(myRoot, grid.getGroup());
        for(int i = 0; i < rowMax; i++){
            for(int j = 0; j < colMax; j++){
                grid.blockToGridPane(i, j, myController.getBlock(i, j));

            }
        }
        grid.setRenderMap();
        myBuilder.addComponent(myRoot, grid.getGroup());
    }
    
}