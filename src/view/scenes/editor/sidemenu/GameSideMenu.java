package view.scenes.editor.sidemenu;

import model.block.blocktypes.BlockType;
import controller.editor.EditorController;
import model.grid.GridSizeDirection;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.builder.ComponentProperties;

import java.util.ResourceBundle;

import view.media.SoundChooser;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class adds functionality for the control panel containing settings pertaining
 *         to the overall gameplay.
 */
public class GameSideMenu extends SideMenu {

    private EditorController myEditorController;
    private boolean myLinkClickedStatus;
    private SoundChooser mySoundChooser;
    private ItemSideMenu myItemMenu;

    GameSideMenu(Parent root, ResourceBundle resources, EditorController editorController, ItemSideMenu itemMenu) {
        super(root, resources);
        myEditorController = editorController;
        myItemMenu = itemMenu;
        mySoundChooser = new SoundChooser(myEditorController);
        myEditorController.addMusic(myResources.getString("defaultSongPath"));
        init();
        setSidePanelHeight(400);
    }

    /**
     * Refreshes the game menu, presumably after a new custom mySoundChooser has been added
     */
    void refresh() {
        myPanel.getTabs().clear();
        setSidePanelHeight(400);
        init();
    }

    /**
     * Stops the current music
     */
    public void stopMusic() {
        if (mySoundChooser != null) mySoundChooser.stop();
    }

    /**
     * Adds win blocks that trigger game win on user interaction
     *
     * @return a ScrollPane displaying the NPC blocks
     */
    private ScrollPane addWinBlocks() {
        return myItemMenu.createScrollPane(BlockType.WIN_TALK);
    }

    /**
     * Tests an input to make sure it is a valid input for resizing the model.grid
     *
     * @param dir the GridSizeDirection in which the model.grid will grow
     * @param val the amount by which the model.grid will grow
     * @return true if dir and val represent a valid resize, false otherwise
     */
    private boolean invalidValue(GridSizeDirection dir, String val) {
        try {
            Integer.parseInt(val);
            return dir == null;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    /**
     * Creates the music pane with which the user can select the music for their game
     *
     * @return the ScrollPane holding the mySoundChooser control panel
     */
    private ScrollPane createMusicPane() {
        Pane musicPanel = new Pane();
        myBuilder.addNewLabel(musicPanel, new ComponentProperties(20, 20)
                .text(myResources.getString("soundInstructions"))
                .color(Color.WHITE)
                .size(20));
        Node soundControls = myBuilder.addComponent(musicPanel, mySoundChooser.getGroup());
        soundControls.setLayoutX(20);
        soundControls.setLayoutY(80);
        return new ScrollPane(musicPanel);
    }

    /**
     * Sets an event handler to a button that adds functionality to the link button
     * for linking blocks together
     *
     * @param node the JavaFX node to which the event handler is set
     */
    private void setLinkEventHandler(Node node) {
        node.setOnMouseClicked(e -> {
            setChanged();
            myLinkClickedStatus = !myLinkClickedStatus;
            notifyObservers(myLinkClickedStatus);
        });
    }

    /**
     * Creates the link pane with which the user can link different blocks together
     *
     * @return the ScrollPane holding the link control panel
     */
    private ScrollPane createLinkPane() {
        Pane linkPanel = new Pane();

        myBuilder.addNewLabel(linkPanel, new ComponentProperties(20, 20)
                .text(myResources.getString("linkInstructions1"))
                .color(Color.WHITE)
                .size(20));

        myBuilder.addNewLabel(linkPanel, new ComponentProperties(20, 80)
                .text(myResources.getString("linkInstructions2"))
                .color(Color.WHITE)
                .size(20));

        myBuilder.addNewLabel(linkPanel, new ComponentProperties(20, 170)
                .text(myResources.getString("linkInstructions3"))
                .color(Color.WHITE)
                .size(20));

        Node button = myBuilder.addNewButton(linkPanel, new ComponentProperties(20, 260)
                .width(350)
                .height(70)
                .path(myResources.getString("linkIconPath")));

        setLinkEventHandler(button);
        return new ScrollPane(linkPanel);
    }

    /**
     * Sets an event handler to a button that adds functionality to the resize button
     * for resizing the model.grid
     *
     * @param node              the button to which the event handler is set
     * @param directionComboBox the ComboBox specifying the direction to add/remove blocks
     * @param sizeInput         the amount by which blocks are added/removed
     * @param radioGroup        the radio buttons indicating whether blocks should be added/removed
     * @param decrease          the RadioButton representing the decrease case
     */
    private void setResizeEventHandler(Node node, ComboBox<GridSizeDirection> directionComboBox, TextField sizeInput, ToggleGroup radioGroup, RadioButton decrease) {
        node.setOnMouseClicked(e -> {
            GridSizeDirection dir = directionComboBox.getValue();
            int resize = Integer.parseInt(sizeInput.getText());
            if (radioGroup.getSelectedToggle() == decrease) {
                resize = resize * -1;
            }
            try {
                if (myEditorController.changeGridSize(dir, resize)) {
                    notifyObservers(dir);
                    notifyObservers(resize);
                    setChanged();
                }
            } catch (ArrayIndexOutOfBoundsException exc) {
                myBuilder.addNewAlert("File Error", "Error");
            }
        });
    }

    /**
     * Creates the reisze pane with which the user can resize the model.grid
     *
     * @return the ScrollPane holding the link control panel
     */
    private ScrollPane createGridResizePane() {
        Pane resizePanel = new Pane();

        ToggleGroup radioGroup = new ToggleGroup();

        myBuilder.addNewRadioButton(resizePanel, new ComponentProperties(20, 20)
                .text(myResources.getString("radioButton1Text"))
                .toggleGroup(radioGroup)
                .selected(true)
                .id(myResources.getString("radioButtonCSSId")));

        RadioButton decrease = (RadioButton) myBuilder.addNewRadioButton(resizePanel, new ComponentProperties(20, 60)
                .text(myResources.getString("radioButton2Text"))
                .toggleGroup(radioGroup)
                .selected(false)
                .id(myResources.getString("radioButtonCSSId")));

        myBuilder.addNewLabel(resizePanel, new ComponentProperties(20, 120)
                .text(myResources.getString("resizeInstructions1"))
                .color(Color.WHITE)
                .size(15));

        @SuppressWarnings("unchecked")
        ComboBox<GridSizeDirection> directionComboBox = (ComboBox<GridSizeDirection>) myBuilder.addNewComboBox(resizePanel,
                new ComponentProperties<GridSizeDirection>(190, 125)
                        .options(FXCollections.observableArrayList(GridSizeDirection.values())));

        myBuilder.addNewLabel(resizePanel, new ComponentProperties(20, 200)
                .text(myResources.getString("resizeInstructions2"))
                .color(Color.WHITE)
                .size(15));

        TextField sizeInput = (TextField) myBuilder.addNewTextField(resizePanel, new ComponentProperties(20, 230)
                .text(myResources.getString("sizeInputPrompt"))
                .width(200));

        Button button = (Button) myBuilder.addNewButton(resizePanel, new ComponentProperties(20, 300)
                .text(myResources.getString("resizePrompt")));

        // don't let user resize unless valid parameters given
        directionComboBox.valueProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        button.setDisable(true);

        setResizeEventHandler(button, directionComboBox, sizeInput, radioGroup, decrease);

        return new ScrollPane(resizePanel);
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        Tab resizeTab = createTab("Resize", createGridResizePane());
        Tab linkTab = createTab("Link", createLinkPane());
        Tab musicTab = createTab("Music", createMusicPane());
        Tab winTab = createTab("Winning", addWinBlocks());
        myPanel.getTabs().addAll(resizeTab, linkTab, musicTab, winTab);
    }
}
