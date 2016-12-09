package ui.scenes.editor.sidemenu;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.FileBrowser;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by Harshil Garg on 12/8/16.
 */
public class CustomSideMenu extends SideMenu {

    public CustomSideMenu(Parent root, ResourceBundle resources) {
        super(root, resources);
        init();
    }

    private ScrollPane addCustomItemScrollPane() {
        FlowPane pane = createFlowPane();

        Button button = new Button("Add");
        button.setOnMouseClicked(e -> {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Portable Network Graphics (*.png)", "*.png");
            fc.getExtensionFilters().add(filter);
            Stage stage = new Stage();
            File selectedFile = fc.showOpenDialog(stage);
            if (selectedFile != null) {

            }
        });


        pane.getChildren().add(button);
        return new ScrollPane(pane);
    }

    public void addTabs() {
        Tab tab = createTab("Add Custom Items", addCustomItemScrollPane());
        myPanel.getTabs().add(tab);
    }

}
