package ui.scenes.engine;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.properties.PropertiesUtilities;
import ui.FileBrowser;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;
import ui.media.SnapShot;
import ui.media.SoundPlayer;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import player.PlayerDirection;
import engine.EngineController;

/**
 * @author Pim Chuaylua, Nisakorn Valyasevi
 *         <p>
 *         This class initializes player status ui.
 */
public class EngineSidePanel implements Observer {

    private static final String imagePath = "resources/images/sidepanel/";
    private static final double DEFAULT_HEALTH = 100;
    private static final int HEALTH_BOX_SPACING = 5;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;
    private VBox vbox;
    private PropertiesUtilities util;
    private EngineCharacter player;
    private EngineView gameEngine;
    private SoundPlayer soundPlayer;
    private EngineController engineController;
    private ProgressBar healthBar;
    private Label playerPos;
    private Label numPokemon;
    private Label healthNum;
    private Label north;
    private Label west;
    private Label east;
    private Label south;
    private Stage myStage;

    public EngineSidePanel(Stage stage, Parent root, UIBuilder builder, ResourceBundle resources, EngineView gameEngine, EngineController engineController) {
        myStage = stage;
        myRoot = root;
        myBuilder = builder;
        myResources = resources;
        util = new PropertiesUtilities(myResources);
        vbox = new VBox(10);
        this.gameEngine = gameEngine;
        this.engineController = engineController;
        initSidePanel();
        initStats();
        addSaveButton();
    }

    private void addSaveButton() {
        Node n = myBuilder.addNewButton(vbox, new ComponentProperties(20, 50).text("Save Progress"));
        n.setOnMouseClicked(e -> {
            File gameFile = new FileBrowser().saveGameFile(myStage, myResources.getString("engineFilePath"));
            engineController.saveEngine(gameFile.getAbsolutePath());
        });
    }

    /**
     * Creates the tab-based menu that will hold the ui.scenes.editor.objects to be added to the
     * overworld grid.
     *
     * @return the item menu, already with proper placement
     */
    private void initSidePanel() {

        int itemMenuXPos = util.getIntProperty("statusPanelPosX");
        int itemMenuYPos = util.getIntProperty("statusPanelPosY");

        Pane itemMenuRegion = myBuilder.addRegion(itemMenuXPos, itemMenuYPos);
        Pane canvas = new Pane();
        canvas.getStyleClass().add("canvas");
        canvas.setPrefSize(300, 1000);
        itemMenuRegion.getChildren().add(canvas);

        myBuilder.addComponent(myRoot, itemMenuRegion);
        itemMenuRegion.getChildren().add(vbox);

        vbox.setPadding(new Insets(10, 10, 10, 10));
        soundPlayer = new SoundPlayer(engineController.getMusic());
        soundPlayer.addNodeToControl(new SnapShot(gameEngine).getGroup());
        vbox.getChildren().add(soundPlayer.getGroup());
    }

    private void initStats() {
        vbox.getChildren().addAll(createLabel("Player " + engineController.getPlayerName()), createLabel("Your HP"));
        HBox healthBox = initHealthBar();
        vbox.getChildren().addAll(healthBox, createLabel("Your Position"));
        playerPos = createLabel(engineController.getPlayerRow() + "," + engineController.getPlayerColumn());
        vbox.getChildren().add(playerPos);
        vbox.getChildren().add(createLabel("Your Number of Pokemon"));
        numPokemon = createLabel(String.valueOf(engineController.getPlayerNumPokemon()));
        vbox.getChildren().add(numPokemon);
        Group compass = initCompass();
        vbox.getChildren().add(compass);

    }

    private HBox initHealthBar() {
        HBox healthBox = new HBox();
        healthBox.setSpacing(HEALTH_BOX_SPACING);
        healthBar = new ProgressBar();
        healthBar.setProgress(engineController.getPlayerHealth() / 100);
        healthNum = createLabel(String.valueOf(engineController.getPlayerHealth()));
        healthBox.getChildren().addAll(healthBar, healthNum);
        return healthBox;
    }

    private Group initCompass() {
        Group playerCompass = new Group();
        ImageView compassImage = new ImageView(new Image(imagePath + "compass.png")); // please clean up later
        compassImage.setFitHeight(140);
        compassImage.setFitWidth(140);
        north = createLabel("N");
        west = createLabel("W");
        east = createLabel("E");
        south = createLabel("S");
        playerCompass.getChildren().addAll(north, west, compassImage, east, south);
        north.setLayoutX(80);
        west.setLayoutY(80);
        east.setLayoutX(160);
        east.setLayoutY(80);
        south.setLayoutY(160);
        south.setLayoutX(80);
        compassImage.setLayoutY(15);
        compassImage.setLayoutX(15);
        return playerCompass;
    }

    private void changeCompassDirection(PlayerDirection direction) {
        switch (direction) {
            case NORTH:
                north.setTextFill(Color.BLUEVIOLET);
                east.setTextFill(Color.BLACK);
                west.setTextFill(Color.BLACK);
                south.setTextFill(Color.BLACK);
                break;
            case SOUTH:
                north.setTextFill(Color.BLACK);
                east.setTextFill(Color.BLACK);
                west.setTextFill(Color.BLACK);
                south.setTextFill(Color.BLUEVIOLET);
                break;
            case WEST:
                north.setTextFill(Color.BLACK);
                east.setTextFill(Color.BLACK);
                west.setTextFill(Color.BLUEVIOLET);
                south.setTextFill(Color.BLACK);
                break;
            case EAST:
                north.setTextFill(Color.BLACK);
                east.setTextFill(Color.BLUEVIOLET);
                west.setTextFill(Color.BLACK);
                south.setTextFill(Color.BLACK);
                break;
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        Font.loadFont("file:./src/resources/fonts/PokemonGB.ttf", 12);
        label.setFont(new Font("Pokemon GB", 12));
        return label;
    }

    void stopMusic() {
        if (soundPlayer != null) {
            soundPlayer.stopMusic();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        playerPos.setText(engineController.getPlayerRow() + "," + engineController.getPlayerColumn());
        numPokemon.setText(String.valueOf(engineController.getPlayerNumPokemon()));
        healthBar.setProgress(engineController.getPlayerHealth() / DEFAULT_HEALTH);
        healthNum.setText("" + engineController.getPlayerHealth());
        changeCompassDirection(engineController.getPlayerDirection());
    }
}