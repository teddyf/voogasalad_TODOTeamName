package ui.scenes.editor.sidemenu;

import editor.EditorController;
import grid.GridSizeDirection;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.builder.ComponentProperties;

import java.util.ResourceBundle;

import ui.media.SoundChooser;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class adds functionality for the grid's control panel.
 */
public class GridSideMenu extends SideMenu {

    private EditorController myEditorController;
    private boolean clickedStatus;
    private SoundChooser sound;

    GridSideMenu(Parent root, ResourceBundle resources, EditorController editorController) {
        super(root, resources);
        myEditorController = editorController;
        init();
        setSidePanelHeight(400);
    }

    void refresh() {
        myPanel.getTabs().clear();
        setSidePanelHeight(400);
        init();
    }

    private boolean invalidValue(GridSizeDirection dir, String val) {
        try {
            Integer.parseInt(val);
            return dir == null;
        } catch (NumberFormatException e) { // non-integer value given
            return true;
        }
    }

    private ScrollPane createMusicPane() {
        Pane musicPanel = new Pane();
        myBuilder.addCustomLabel(musicPanel, "Choose a song to play during your game.", 20, 20, null, Color.WHITE, 20);
        sound = new SoundChooser();
        Node n = myBuilder.addComponent(musicPanel, sound.getGroup());
        n.setLayoutX(20);
        n.setLayoutY(80);
        return new ScrollPane(musicPanel);
    }

    private ScrollPane createLinkPane() {
        Pane linkPanel = new Pane();
        myBuilder.addCustomLabel(linkPanel, "Use the link button to link different blocks\ntogether.", 20, 20, null, Color.WHITE, 20);


        myBuilder.addCustomLabel(linkPanel, "Create a portal between two teleport\nblocks by clicking the link button and\nthen clicking the two blocks.", 20, 80, null, Color.WHITE, 20);

        myBuilder.addCustomLabel(linkPanel, "Link a gate to a switch by clicking the link\nbutton and then clicking each of the two\nblocks.", 20, 170, null, Color.WHITE, 20);


        Node button = myBuilder.addNewButton(linkPanel, new ComponentProperties(20, 260)
                .width(350)
                .height(70)
                .path("resources/images/buttons/link.png"));
        button.setOnMouseClicked(e -> {
            setChanged();
            changeStatus();
            notifyObservers(clickedStatus);
        });
        return new ScrollPane(linkPanel);
    }


    private ScrollPane createGridResizePane() {
        Pane resizePanel = new Pane();

        ToggleGroup radioGroup = new ToggleGroup();
        myBuilder.addCustomRadioButton(resizePanel, "Increase size", 20, 20, radioGroup, true, "grid-button");
        RadioButton decrease = (RadioButton) myBuilder.addCustomRadioButton(resizePanel, "Decrease size", 20, 60, radioGroup, false, "grid-button");

        myBuilder.addCustomLabel(resizePanel, "Grid side from which to\nadd or remove blocks", 20, 120, null, Color.WHITE, 15);

        @SuppressWarnings("unchecked")
        ComboBox<GridSizeDirection> directionComboBox = (ComboBox<GridSizeDirection>) myBuilder.addNewComboBox(resizePanel,
                new ComponentProperties<GridSizeDirection>(190, 125)
                        .options(FXCollections.observableArrayList(GridSizeDirection.values())));

        myBuilder.addCustomLabel(resizePanel, "Number of rows or columns to add or remove:", 20, 200, null, Color.WHITE, 15);
        TextField sizeInput = (TextField) myBuilder.addCustomTextField(resizePanel, "block size", 20, 230, 200);

        Button button = (Button) myBuilder.addNewButton(resizePanel, new ComponentProperties(20, 300).text("Resize"));

        directionComboBox.valueProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        sizeInput.textProperty().addListener(e -> button.setDisable(invalidValue(directionComboBox.getValue(), sizeInput.getText())));
        button.setDisable(true);

        button.setOnMouseClicked(e -> {
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

        return new ScrollPane(resizePanel);
    }

    /**
     * Creates and adds tabs for each object type to the Item Menu
     */
    public void addTabs() {
        Tab resizeTab = createTab("Resize", createGridResizePane());
        Tab linkTab = createTab("Link", createLinkPane());
        Tab musicTab = createTab("Music", createMusicPane());
        myPanel.getTabs().addAll(resizeTab, linkTab, musicTab);
    }

    private void changeStatus() {
        clickedStatus = !clickedStatus;
    }

    public void stopMusic() {
        sound.stop();
    }

}
