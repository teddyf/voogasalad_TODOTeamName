package ui.scenes;

import ui.GridPane;
import ui.GridPaneNode;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;
import ui.scenes.engine.GridDisplayer;

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
	private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private GridPane gridPane;
    private GridDisplayer myGridDisplayer;
    private Group gridRegion;

    public GameEngine(Stage stage, Parent root, UILauncher launcher) {
        super(root);
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(ENGINE_RESOURCES);
        gridRegion = myBuilder.addRegion(-50, -50);
        myStage.setOnCloseRequest(e -> {
            // closing the window takes you back to main menu
            e.consume();
            myLauncher.launchMenu();
        });
    }

    /**
     * Initializes the game engine window and prompts the user to choose a game
     * file to open for playing
     *
     * @return true if initialization was successful and a valid game file was chosen
     */
    public boolean init() {
        /*File gameFile = new FileBrowser().openGameFile(myStage, myResources.getString("gameFilePath"));
        if (gameFile == null) { // user clicked cancel
            return false;
        }*/
    	
    	setUpGrid();
        myBuilder.initWindow(myStage, ENGINE_RESOURCES);
    	//myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        return true;
    }
    
    private void setUpGrid() {

    	gridPane =
                new GridPane(Integer.parseInt(myResources.getString("gridCellsWide")),
                             Integer.parseInt(myResources.getString("gridCellsHeight")),
                             Integer.parseInt(myResources.getString("gridWidth")),
                             Integer.parseInt(myResources.getString("gridHeight")),
                             Integer.parseInt(myResources.getString("gridX")),
                             Integer.parseInt(myResources.getString("gridY")));
    	myGridDisplayer = new GridDisplayer(gridPane);
//    	gridRegion.getChildren().add(myGridDisplayer.updateDisplay(30, 30));
    	myBuilder.addComponent(myRoot, gridRegion);
    	
    	/*ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(Double.parseDouble(myResources.getString("buttonHoverOpacity")));
        
        int updateX = Integer.parseInt(myResources.getString("updateX"));
        int updateY = Integer.parseInt(myResources.getString("updateY"));
        int updateWidth = Integer.parseInt(myResources.getString("updateWidth"));
        int widthInputX = Integer.parseInt(myResources.getString("inputWidthX"));
        int widthInputY = Integer.parseInt(myResources.getString("inputWidthY"));
        int widthInputWidth = Integer.parseInt(myResources.getString("inputWidthWidth"));
        String widthInputText = myResources.getString("inputWidthText");
        int heightInputX = Integer.parseInt(myResources.getString("inputHeightX"));
        int heightInputY = Integer.parseInt(myResources.getString("inputHeightY"));
        int heightInputWidth = Integer.parseInt(myResources.getString("inputHeightWidth"));
        String heightInputText = myResources.getString("inputHeightText");
        String updatePath = myResources.getString("updatePath");

        Node widthInputField =
                myBuilder.addCustomTextField(myRoot, widthInputText, widthInputX, widthInputY,
                                             widthInputWidth);
        Node heightInputField =
                myBuilder.addCustomTextField(myRoot, heightInputText, heightInputX, heightInputY,
                                             heightInputWidth);
        
        
        Node updateButton =
                myBuilder.addCustomButton(myRoot, updatePath, updateX, updateY, updateWidth);
        updateButton.setOnMouseClicked(e -> {
            myBuilder.removeComponent(myRoot, gridPane.getGroup());
            TextField xText = (TextField) widthInputField;
            TextField yText = (TextField) heightInputField;
            int xInput = Integer.parseInt(xText.getText());
            int yInput = Integer.parseInt(yText.getText());
            gridPane.resizeReset(xInput, yInput);
            myBuilder.addComponent(myRoot, gridPane.getGroup());
        });*/

    }
}
