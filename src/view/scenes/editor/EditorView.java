package view.scenes.editor;

import controller.editor.EditorController;
import controller.engine.EngineController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.grid.EditorGrid;
import view.UILauncher;
import utilities.builder.UIBuilder;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import view.scenes.editor.sidemenu.*;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game controller.editor that is used to build games. It
 *         creates the model.grid on which the overworld is created along with control
 *         panels for handling the control flow of the editing process.
 */
public class EditorView extends Scene implements GameEditorAlerts, Observer {

    private static final String EDITOR_RESOURCES = "resources/properties/game-controller.editor";
    private static final String ALERT_RESOURCES = "resources/properties/alerts-text";
    private static final String CSS_FILE_NAME = "resources/styles/game-editor.css";
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private ResourceBundle myAlertResources;
    private EditorController myController;
    private EditorEvents events;
    private GridUI myGridUI;
    private EditorIO myEditorIO;
    private boolean recentlySaved = false;

    public EditorView(Stage stage, Parent root, UILauncher launcher, EditorController controller) {
        super(root, Color.web("#1D3461"));
        myController = controller;
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myAlertResources = ResourceBundle.getBundle(ALERT_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
    }

    /**
     * Launches the controller.editor by initializing the control panels, drawing the overworld model.grid,
     * initializing the IO classes for saving and loading files, and initializing the menu
     * bar
     *
     * @param width  width of the model.grid to create
     * @param height height of the model.grid to create
     */
    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        // init side control panels
        EditorControls sideControls = new EditorControls(myRoot, myResources, myController);
        // init model.grid
        myGridUI = new GridUI(myRoot, myController, sideControls, width, height);
        setOnScrollStarted(event -> myGridUI.getScrollMechanism().trackpadStartScroll(event));
        setOnScrollFinished(event -> myGridUI.getScrollMechanism().trackpadEndScroll(event));
        // init file I/O
        myEditorIO = new EditorIO(myStage, myController, new EngineController(), myResources, myGridUI);
        myEditorIO.addObserver(myGridUI);
        events = new EditorEvents(myLauncher, myEditorIO, myResources);
        // init error checking
        myController.setAlerts(this);
        // init menu bar
        new MenuBarUI(myStage, myRoot, events, myResources);
        // add observer
        initObserver();
        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            myController = new EditorController();
            sideControls.getGridSideMenu().stopMusic();
            if (!recentlySaved) {
                events.exitPrompt(false);
            } else {
                myLauncher.launchMenu();
            }
            e.consume();
        });
        myStage.setScene(this);
    }

    /**
     * Initializes the game controller.editor window by prompting the user to choose an initial
     * overworld size
     */
    public void init() {
        SizeChooser2 sizeChooser = new SizeChooser2(this, new Group());
        myBuilder.initWindow(myStage, SizeChooser2.SIZE_CHOOSER_RESOURCES);
        myStage.setScene(sizeChooser);
    }

    public boolean warnUser(String warningKey) {
        return events.createWarning(warningKey);
    }

    public void exceptionDisplay(String content) {
        myBuilder.addNewAlert(myAlertResources.getString("EXCEPTION").toUpperCase(), content);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof EditorGrid){
            recentlySaved = false;
        }
        if (o instanceof EditorIO) {
            recentlySaved = true;
        }
    }

    private void initObserver() {
        myGridUI.getMyGridPane().addObserver(this);
        myEditorIO.addObserver(this);
    }

}