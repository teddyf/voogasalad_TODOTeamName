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

import ui.scenes.editor.sidemenu.*;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class handles the game editor that is used to build games.
 */
public class GameEditor extends Scene implements GameEditorAlerts {

    private static final String EDITOR_RESOURCES = "resources/properties/game-editor";
    private static final String ALERT_RESOURCES = "resources/properties/alerts-text";
    private static final String CSS_FILE_NAME = "resources/styles/game-editor.css";
    private Stage myStage;
    private Parent myRoot;
    private UILauncher myLauncher;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private ResourceBundle alertResources;
    private EditorController myController;
    private EditorEvents events;

    public GameEditor(Stage stage, Parent root, UILauncher launcher, EditorController controller) {
        super(root, Color.web("#0585B2"));
        myController = controller;
        myStage = stage;
        myRoot = root;
        myLauncher = launcher;
        myBuilder = new UIBuilder();
        myResources = ResourceBundle.getBundle(EDITOR_RESOURCES);
        alertResources = ResourceBundle.getBundle(ALERT_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
    }


    void launchEditor(int width, int height) {
        myBuilder.initWindow(myStage, EDITOR_RESOURCES);
        ItemSideMenu itemMenu = new ItemSideMenu(myRoot, myResources);

        GridUI grid = new GridUI(myRoot, myController, itemMenu, width, height);
        new EditorControls(myRoot, myResources, itemMenu);

        EditorIO IO = new EditorIO(myStage, myController, new EngineController(), myResources, grid);
        events = new EditorEvents(myLauncher, IO, myResources);
        myController.setAlerts(this);

        MenuBarUI menuBar = new MenuBarUI(myStage, myRoot, events, myResources);
        menuBar.initMenuBar();
//        initPlayerButton();

        //        EngineController loadedEngine = editorController.runEngine(); // running test
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


//    private void initPlayerButton() {
//        ColorAdjust hoverOpacity = new ColorAdjust();
//        hoverOpacity.setBrightness(-.3);
//        int playerX = Integer.parseInt(myResources.getString("playerX"));
//        int playerY = Integer.parseInt(myResources.getString("playerY"));
//        int playerWidth = Integer.parseInt(myResources.getString("playerWidth"));
//        String playerText = myResources.getString("playerLabel");
//        Node playerButton = myBuilder.addCustomButton(myRoot, playerText, playerX, playerY, playerWidth);
//        playerButton.setOnMouseClicked(e -> {
//            myLauncher.launchCharacterMenu();
//        });
//        playerButton.setOnMouseEntered(e -> {
//            playerButton.setEffect(hoverOpacity);
//        });
//        playerButton.setOnMouseExited(e -> {
//            playerButton.setEffect(null);
//        });
//    }

    public String getPath() {
        return EDITOR_RESOURCES;
    }

    public boolean warnUser(String warningKey) {
        return events.createWarning(warningKey);
    }

    public void exceptionDisplay(String content) {
        System.out.println("CONTENT = " + content);
        myBuilder.addNewAlert(alertResources.getString("EXCEPTION").toUpperCase(), content);
    }
}