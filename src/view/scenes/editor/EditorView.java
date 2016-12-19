package view.scenes.editor;

import controller.editor.EditorController;
import controller.engine.EngineController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.builder.ComponentProperties;
import view.UILauncher;
import utilities.builder.UIBuilder;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import view.grid.Grid;
import view.scenes.editor.sidemenu.*;

/**
 * @author Robert Steilberg, Harshil Garg
 *         <p>
 *         This class handles the game editor that is used to build games. It
 *         creates the model.grid on which the overworld is created along with control
 *         panels for handling the control flow of the editing process.
 */
public class EditorView extends Scene implements GameEditorAlerts, Observer {

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
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
     * Warn the user of a disallowed action
     *
     * @param warningKey is the content of the alert
     * @return true if the user clicked OK, false otherwise
     */
    public boolean warnUser(String warningKey) {
        return events.createWarning(warningKey);
    }

    /**
     * Display an alert to the user triggered by an exception
     *
     * @param content is the content of the alert
     */
    public void exceptionDisplay(String content) {
        myBuilder.addNewAlert(new ComponentProperties()
                .header(myAlertResources.getString("EXCEPTION")
                        .toUpperCase()).content(content));
    }

    /**
     * Update on saving the game
     *
     * @param o   object observing
     * @param arg Object triggering the update
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Grid) {
            recentlySaved = false;
        }
        if (o instanceof EditorIO) {
            recentlySaved = true;
        }
    }

    /**
     * Initialize observer for only triggering save when not recently saved
     */
    private void initObserver() {
        myGridUI.getMyGridPane().addObserver(this);
        myEditorIO.addObserver(this);
    }

    /**
     * Initializes the game editor window by prompting the user to choose an initial
     * overworld size
     */
    public void init() {
        SizeChooserUI sizeChooser = new SizeChooserUI(this, new Group());
        myBuilder.initWindow(myStage, SizeChooserUI.SIZE_CHOOSER_RESOURCES);
        myStage.setScene(sizeChooser);
    }
}