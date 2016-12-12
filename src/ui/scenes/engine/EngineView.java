package ui.scenes.engine;

import engine.EngineController;
import ui.FileBrowser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game engine that is used to run games.
 *
 *         Dependencies: FileBrowser.java
 */
public class EngineView extends Scene implements Observer {

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
    private EngineSidePanel engineSidePanel;

    public EngineView(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        myRoot.getStylesheets().add(CSS_FILE_NAME);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            myController = null;
            e.consume();
            engineSidePanel.stopMusic();
            myLauncher.launchMenu();
        });
        myController = new EngineController();
    }

    public EngineView(Stage stage, Parent root) {
        super(root);
        myStage = stage;
        myRoot = root;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        myRoot.getStylesheets().add(CSS_FILE_NAME);
//        myStage.setOnCloseRequest(e -> {
//            // closing the window takes you back to main menu
//            e.consume();
//            myLauncher.launchMenu();
//        });
        myController = new EngineController();
    }

    public void setController(EngineController controller) {
        myController = controller;
    }

    /**
     * Initializes the game engine window and prompts the user to choose a game
     * file to open for playing
     *
     * @return true if initialization was successful and a valid game file was chosen
     */
    public boolean init() {
        File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("engineFilePath"));
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

    public void runInstance() {
        initGrid();
        loadGrid();
        setUpGrid();
        setUpSidePanel();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
    }
    
    private void setUpGrid() {
    	setUpKeys();
    	setUpPlayer();
    	anim = new VoogaAnimation(myRoot, grid, player, myBuilder, myController);
        initObserver();
    	myController.addObserver(anim);
    }
    
    public String getPath(){
        return ENGINE_RESOURCES;
    }
    
    private void setUpSidePanel() {
    	EngineSidePanel engineSidePanel = new EngineSidePanel(myRoot,myBuilder,myResources,this, myController);
    	myController.addObserver(engineSidePanel);
    	engineSidePanel.update(myController, myController.getPlayer());
    }
    
    private void setUpPlayer() {
        List<String> playerImagePaths = myController.getPlayerImages();
        String defaultPath = playerImagePaths.get(0);

    	player = new Character(playerImagePaths, defaultPath);
        player.setSize(grid.getBlockSize());

        int gridWidth = Integer.parseInt(myResources.getString("gridWidth"));
        int gridHeight = Integer.parseInt(myResources.getString("gridHeight"));

        player.setPosX(gridWidth/2 - player.getSize()/2);
        player.setPosY(gridHeight/2 - player.getSize()/2);
    	myBuilder.addComponent(myRoot, player.getImageView());

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
    
    void loadGrid(){
        int colMax = myController.getNumCols();
        int rowMax = myController.getNumRows();
        grid.loadReset(rowMax, colMax);
        myBuilder.removeComponent(myRoot, grid.getGroup());
        for(int i = 0; i < rowMax; i++){
            for(int j = 0; j < colMax; j++){
                grid.blockToGridPane(i, j, myController.getBlock(i, j));
            }
        }
        grid.populateBorder();
        grid.setRenderMap();
        myBuilder.addComponent(myRoot, grid.getGroup());
    }

    private void winGame() {
    }

    private void initObserver() {
        InteractionHandler handler = anim.getInteractionHandler();
        handler.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        winGame();
    }
}