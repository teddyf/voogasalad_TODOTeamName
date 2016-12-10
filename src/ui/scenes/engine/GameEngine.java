package ui.scenes.engine;

import engine.EngineController;
import ui.FileBrowser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game engine that is used to run games.
 *
 *         Dependencies: FileBrowser.java
 */
public class GameEngine extends Scene {

    private static final String ENGINE_RESOURCES = "resources/properties/game-engine";
    private static final String CSS_FILE_NAME = "resources/styles/game-engine.css";
    
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private GridForEngine grid;
    private EngineController myController;
    private VoogaAnimation anim;
    private Character player;

    public GameEngine(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        myRoot.getStylesheets().add(CSS_FILE_NAME);
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
    	setUpSidePanel();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
        return true;
    }
    
    private void setUpGrid() {
    	setUpKeys();
    	setUpPlayer();
    	anim = new VoogaAnimation(myRoot, grid, player, myBuilder, myController);
    	myController.addObserver(anim);
    }
    
    public String getPath(){
        return ENGINE_RESOURCES;
    }
    
    private void setUpSidePanel() {
    	EngineSidePanel engineSidePanel = new EngineSidePanel(myRoot,myBuilder,myResources,player);
    }
    
    private void setUpPlayer() {
    	int gridX = Integer.parseInt(myResources.getString("gridX"));
        int gridY = Integer.parseInt(myResources.getString("gridY"));
        
    	player = new Character(this);
    	System.out.println(player.getRowCharacter());
    	System.out.println(player.getColumnCharacter());
    	player.setCharacterImage("resources/images/sprites/1-down.png");
        player.setCharacterImageSize(grid.getBlockSize());

        int gridWidth = Integer.parseInt(myResources.getString("gridWidth"));
        int gridHeight = Integer.parseInt(myResources.getString("gridHeight"));

        player.setPosX(gridWidth/2 - player.getSize()/2);
        player.setPosY(gridHeight/2 - player.getSize()/2);
    	player.setName("resources/images/sprites/1-down.png");
    	myBuilder.addComponent(myRoot, player.getCharacterImageView());

        //setup grid
        double ypixel = myController.getPlayerRow()*grid.getBlockSize();
        double xpixel = myController.getPlayerColumn()*grid.getBlockSize();

        double a = 0;
        double b = 0;
        //Find central block
        if (grid.getWidth() %2 != 0){
            a = ((grid.getWidth()-1)/2 - myController.getPlayerColumn())*grid.getBlockSize();
        } else {
            a = (grid.getWidth()/2 - 0.5 - myController.getPlayerColumn())*grid.getBlockSize();
        }

        if (grid.getHeight() %2 != 0){
            b = ((grid.getHeight()-1)/2 - myController.getPlayerRow())*grid.getBlockSize();
        } else {
            b = (grid.getHeight()/2 - 0.5 - myController.getPlayerRow())*grid.getBlockSize();
        }

        grid.getGroup().setLayoutX(grid.getGroup().getLayoutX() + a);
        grid.getGroup().setLayoutY(grid.getGroup().getLayoutY() + b);
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
        grid.populateBorder();
        grid.setRenderMap();
        System.out.println(grid.getNodeList());
        myBuilder.addComponent(myRoot, grid.getGroup());
    }
	
}