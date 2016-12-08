package ui.scenes.editor;

import editor.EditorController;
import engine.EngineController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

import ui.scenes.editor.sidemenu.ItemSideMenu;
import ui.scenes.editor.sidemenu.PlayerMenuUI;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene {

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
    private static final String CSS_FILE_NAME = "resources/styles/game-editor.css";
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private EditorController myController;


    public GameEditor(Stage stage, Parent root, UILauncher launcher, EditorController controller) {
        super(root, Color.web("#0585B2"));
        myController = controller;
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
    }


    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        

        ItemSideMenu itemMenu = new ItemSideMenu(myRoot, myResources, "item");
		itemMenu.init();
		
        GridUI grid = new GridUI(myRoot, itemMenu, myController);
        grid.initGrid(width, height);
        
        PlayerMenuUI playerMenu = new PlayerMenuUI(myRoot, myBuilder, myResources,myController);
        playerMenu.init();
        
        EditorControls controls = new EditorControls(myRoot, myResources, itemMenu,playerMenu);
        controls.addEditorControls();

        EditorIO IO = new EditorIO(myStage, myController, new EngineController(), myResources, grid);
        EditorEvents events = new EditorEvents(myLauncher, IO, myResources);
        
        MenuBarUI menuBar = new MenuBarUI(myStage, myRoot, events, myResources);
        menuBar.initMenuBar();
        initPlayerButton();

        //        EngineController loadedEngine = editorController.runEngine(); // running test
        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            e.consume();
            events.exitPrompt(false);
        });

                /* New code */
        myStage.setScene(this);

    }

    /**
     * Initializes the game editor window by prompting the user to choose an initial
     * overworld size
     */
    public void initEditor() {
        //SizeChooserUI sizeChooser = new SizeChooserUI(myStage, new Group(), this, myLauncher, myBuilder);
        //sizeChooser.promptUserForSize();

        SizeChooser2 sizeChooser = new SizeChooser2(this, new Group());
        myBuilder.initWindow(myStage, SizeChooser2.SIZE_CHOOSER_RESOURCES);
        myStage.setScene(sizeChooser);
    }


    private void initPlayerButton() {
        ColorAdjust hoverOpacity = new ColorAdjust();
        hoverOpacity.setBrightness(-.3);
        int playerX = Integer.parseInt(myResources.getString("playerX"));
        int playerY = Integer.parseInt(myResources.getString("playerY"));
        int playerWidth = Integer.parseInt(myResources.getString("playerWidth"));
        String playerText = myResources.getString("playerLabel");
        Node playerButton = myBuilder.addCustomButton(myRoot, playerText, playerX, playerY, playerWidth);
        playerButton.setOnMouseClicked(e -> {
            myLauncher.launchCharacterMenu();
        });
        playerButton.setOnMouseEntered(e -> {
            playerButton.setEffect(hoverOpacity);
        });
        playerButton.setOnMouseExited(e -> {
            playerButton.setEffect(null);
        });
    }

    public String getPath() {
        return EDITOR_RESOURCES;
    }
}