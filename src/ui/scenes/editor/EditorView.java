package ui.scenes.editor;

import editor.EditorController;
import engine.EngineController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

import ui.scenes.editor.sidemenu.*;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game editor that is used to build games. It
 *         creates the grid on which the overworld is created along with control
 *         panels for handling the control flow of the editing process.
 */
public class EditorView extends Scene implements GameEditorAlerts {

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

    public EditorView(Stage stage, Parent root, UILauncher launcher, EditorController controller) {
//        super(root, Color.web("#0585B2"));
        super(root, Color.GRAY);
        myController = controller;
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        myAlertResources = ResourceBundle.getBundle(ALERT_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
    }


    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        EditorControls sideControls = new EditorControls(myRoot, myResources, myController);
        GridUI grid = new GridUI(myRoot, myController, sideControls.getMyItemMenu(), width, height);



        EditorIO IO = new EditorIO(myStage, myController, new EngineController(), myResources, grid);
        events = new EditorEvents(myLauncher, IO, myResources);
        myController.setAlerts(this);

        MenuBarUI menuBar = new MenuBarUI(myStage, myRoot, events, myResources);
        menuBar.initMenuBar();

//                EngineController loadedEngine = myController.runEngine();


        myStage.setOnCloseRequest(e -> {
            // closing the window prompts save and takes you back to main menu
            e.consume();
            events.exitPrompt(false);
        });
        myStage.setScene(this);
        this.setOnScrollStarted(event -> grid.getScrollMechanism().trackpadStartScroll(event));
        this.setOnScrollFinished(event -> grid.getScrollMechanism().trackpadEndScroll(event));
    }

    /**
     * Initializes the game editor window by prompting the user to choose an initial
     * overworld size
     */
    public void initEditor() {
        SizeChooserUI sizeChooser = new SizeChooserUI(this, new Group());

//                myStage, new Group(), this, myLauncher, myBuilder);


        //sizeChooser.promptUserForSize();

//        SizeChooser2 sizeChooser = new SizeChooser2(this, new Group());
        myBuilder.initWindow(myStage, SizeChooserUI.SIZE_CHOOSER_RESOURCES);

//        myBuilder.initWindow(myStage, SizeChooserUI.SIZE_CHOOSER_RESOURCES);
        myStage.setScene(sizeChooser);
    }


    public String getPath() {
        return EDITOR_RESOURCES;
    }

    public boolean warnUser(String warningKey) {
        return events.createWarning(warningKey);
    }

    public void exceptionDisplay(String content) {
        System.out.println("CONTENT = " + content);
        myBuilder.addNewAlert(myAlertResources.getString("EXCEPTION").toUpperCase(), content);
    }
}