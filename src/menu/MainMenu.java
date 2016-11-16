package menu;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ui.UILauncher;
import ui.builder.ComponentProperties;
import ui.builder.UIBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import static javafx.scene.control.ContentDisplay.RIGHT;

/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class handles launching the main menu and transitioning into the
 *         game engine or game editor.
 *         <p>
 *         Dependencies: UILauncher, UIBuilder
 */
public class MainMenu extends Scene {

    private static final String MAINMENU_RESOURCES = "resources/mainmenu";
    private static final String CSS_FILE_NAME = "resources/UIStyle.css";
    private Stage myStage;
    private UILauncher myLauncher;
    private Parent myRoot;
    private UIBuilder myBuilder;
    private ResourceBundle myResources;

    public MainMenu(Stage stage, UILauncher launcher, Parent root) {
        super(root);
        myStage = stage;
        myBuilder = new UIBuilder();
        myLauncher = launcher;
        myRoot = root;
        myResources = ResourceBundle.getBundle(MAINMENU_RESOURCES);
        root.getStylesheets().add(CSS_FILE_NAME);
        initMenu();
    }

    /**
     * Initializes the navigational buttons in the main menu
     */
    private void initButtons() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.1);

        ImageView build = new ImageView(new Image("resources/images/menu/build.png"));
        build.setPreserveRatio(true);
        build.setFitWidth(300);
        build.setLayoutY(240);
        build.setLayoutX(-85);
        build.setOnMouseClicked(e -> myLauncher.launchEditor());
        build.setOnMouseEntered(e -> build.setEffect(ca));
        build.setOnMouseExited(e -> build.setEffect(null));
        myBuilder.addComponent(myRoot,build);

        ImageView play = new ImageView(new Image("resources/images/menu/play.png"));
        play.setPreserveRatio(true);
        play.setFitWidth(300);
        play.setLayoutY(310);
        play.setLayoutX(-44);
        play.setOnMouseClicked(e -> myLauncher.launchEngine());
        play.setOnMouseEntered(e -> play.setEffect(ca));
        play.setOnMouseExited(e -> play.setEffect(null));
        myBuilder.addComponent(myRoot,play);

        ImageView exit = new ImageView(new Image("resources/images/menu/exit.png"));
        exit.setPreserveRatio(true);
        exit.setFitWidth(300);
        exit.setLayoutY(380);
        exit.setLayoutX(-1);


        exit.setOnMouseClicked(e -> myStage.hide());
        exit.setOnMouseEntered(e -> exit.setEffect(ca));
        exit.setOnMouseExited(e -> exit.setEffect(null));


        myBuilder.addComponent(myRoot,exit);






    }

    /**
     * Initializes the main menu window
     */
    private void initMenu() {
        myStage.setTitle(myResources.getString("windowTitle"));
        myStage.setHeight(Integer.parseInt(myResources.getString("windowHeight")));
        myStage.setWidth(Integer.parseInt(myResources.getString("windowWidth")));
        myStage.centerOnScreen();
        myStage.show();

        Image i = new Image("resources/images/menu/background.png");
        ImageView iv = new ImageView(i);
        iv.setPreserveRatio(true);
        iv.setFitWidth(900);
        myBuilder.addComponent(myRoot,iv);




        Font.loadFont("file:./src/resources/images/menu/blackout.ttf",12);



        ImageView pb = new ImageView(new Image("resources/images/menu/pokeball.png"));
        pb.setPreserveRatio(true);
        pb.setFitWidth(50);
        Label l = new Label("VOOGA", pb);
        l.setContentDisplay(RIGHT);
        l.setGraphicTextGap(10);
        l.setLayoutX(15);
        l.setFont(new Font("Blackout Midnight",60));
        myBuilder.addComponent(myRoot,l);





        Label l2 = new Label("Let's start building.");
        l2.setLayoutX(15);
        l2.setLayoutY(80);
        l2.setFont(new Font("Blackout Midnight",40));
        myBuilder.addComponent(myRoot,l2);




        initButtons();




    }
}
