package ui.builder;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;


/**
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class is used to build JavaFX objects and add them to the stage.
 *         <p>
 *         Dependencies: ComponentBuilder, ButtonBuilder, ImageViewBuilder, LabelBuilder
 */
public class UIBuilder {

    private ComponentBuilder alertBuilder;
    private ComponentBuilder buttonBuilder;
    private ComponentBuilder imageViewBuilder;
    private ComponentBuilder labelBuilder;
    private ComponentBuilder textFieldBuilder;

    public UIBuilder() {
        alertBuilder = new AlertBuilder();
        buttonBuilder = new ButtonBuilder();
        imageViewBuilder = new ImageViewBuilder();
        labelBuilder = new LabelBuilder();
        textFieldBuilder = new TextFieldBuilder();
    }

    /**
     * Create a new group that can serve as a region holding other nodes
     *
     * @param layoutX is the x coordinate of the region
     * @param layoutY is the y coordinate of the region
     * @return the new region as a Group
     */
    public Pane addRegion(int layoutX, int layoutY) {
        Pane region = new Pane();
        region.setLayoutX(layoutX);
        region.setLayoutY(layoutY);
        return region;
    }

    /**
     * Adds a JavaFX node to the specified Group or Pane
     *
     * @param layout    is the Group or Pane to which the node will be added
     * @param component is the JavaFX node to be added
     * @return the added node
     */
    public Node addComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().add(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().add(component);
        } else if (layout instanceof VBox) {
            VBox pane = (VBox) layout;
            pane.getChildren().add(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Removes a JavaFX node from the specified Group or Pane
     *
     * @param layout    is the Group or Pane from which the node will be removed
     * @param component is the JavaFX node to be removed
     * @return the removed node
     */
    public Node removeComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().remove(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().remove(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Create a new JavaFX Button and adds it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the Button will be added
     * @param properties the ComponentProperties object containing information
     *                   about the Button
     * @return the newly added Button as a node
     */
    public Node addNewButton(Parent layout, ComponentProperties properties) {
        return addComponent(layout, buttonBuilder.createComponent(properties));
    }

    /**
     * Creates a new JavaFX Button and adds it to the given Group or Pane
     *
     * @param layout the Group or Pane to which the Button will be added
     * @param text   Text to be used for the button
     * @param x      X-coordinate to set the button
     * @param y      Y-coordinate to set the button
     * @param width  Width of the button
     * @return
     */
    public Node addCustomButton(Parent layout, String text, int x, int y, int width) {
        return addNewImageView(layout, new ComponentProperties(x, y).path(text)
                .preserveRatio(true)
                .width(width));
    }

    /**
     * Creates a new JavaFX ImageView and adds it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the ImageView will be added
     * @param properties the ComponentProperties object containing information
     *                   about the ImageView
     * @return the newly added ImageView as a node
     */
    public Node addNewImageView(Parent layout, ComponentProperties properties) {
        return addComponent(layout, imageViewBuilder.createComponent(properties));
    }

    /**
     * Creates a new JavaFX ImageView, sets its position, image path, width, and CSS id,
     * and adds it to the given Group or Pane
     *
     * @param layout is the Group or Pane to which the ImageView is added
     * @param xPos   is the X position of the ImageView
     * @param yPos   is the Y position of the ImageView
     * @param path   is the image path
     * @param width  is the width of the ImageView
     * @param id     is the CSS ID
     * @return
     */
    public Node addCustomImageView(Parent layout, int xPos, int yPos, String path, int width, String id) {
        return addNewImageView(layout, new ComponentProperties(xPos, yPos)
                .path(path)
                .preserveRatio(true)
                .width(width)
                .id(id));
    }

//    /**
//     * Create a new JavaFX Label and adds it to the given Group or Pane
//     *
//     * @param layout     the Group or Pane to which the Label will be added
//     * @param properties the ComponentProperties object containing information
//     *                   about the Label
//     * @return the newly added Label as a node
//     */
//    public Node addNewLabel(Parent layout, ComponentProperties properties) {
//        return addComponent(layout, labelBuilder.createComponent(properties));
//    }

    /**
     * Create a customized JavaFX Label and add it to the given Group or Pane
     *
     * @param layout the Group or Pane to which the Label will be added
     * @param text   Text to be set in properties
     * @param x      Sets the X for properties
     * @param y      Sets the Y for properties
     * @return
     */
    public Node addCustomLabel(Parent layout, String text, int x, int y, String font, int size) {
        return addComponent(layout, labelBuilder.createComponent(new ComponentProperties(x, y)
                .text(text)
                .font(font)
                .size(size)));
    }

    /**
     * Create a new JavaFX text field and add it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the Label will be added
     * @param properties the ComponentProperties object containing information about the text field
     * @return
     */
    public Node addNewTextField(Parent layout, ComponentProperties properties) {
        return addComponent(layout, textFieldBuilder.createComponent(properties));
    }

    /**
     * Create a customized JavaFX text field and add it to the given Group or Pane
     *
     * @param layout is the Group or Parent to which the text field is added
     * @param text   is the text to be displayed in the text field
     * @param x      is the X position of the text field
     * @param y      is the Y position of the text field
     * @param width  is the width of the text field
     * @return the properly formatted text field
     */
    public Node addCustomTextField(Parent layout, String text, int x, int y, int width) {
        return addComponent(layout, textFieldBuilder.createComponent(new ComponentProperties(x, y)
                .width(width)
                .text(text)));
    }

    public Node addNewAlert(String header, String content) {
        return alertBuilder.createComponent(new ComponentProperties()
                .header(header)
                .content(content));
    }

    /**
     * Initializes a JavaFX window with the specified stage and parameters given
     * in a properties file
     *
     * @param currStage          the JavaFX stage with which the window is created
     * @param propertiesFilePath the path to the properties file containing the
     *                           window's parameters
     */
    public void initWindow(Stage currStage, String propertiesFilePath) {
        ResourceBundle resources = ResourceBundle.getBundle(propertiesFilePath);
        System.out.println(Integer.parseInt(resources.getString("windowHeight")));
        System.out.println(Integer.parseInt(resources.getString("windowWidth")));
        currStage.setHeight(Integer.parseInt(resources.getString("windowHeight")));
        currStage.setWidth(Integer.parseInt(resources.getString("windowWidth")));
        currStage.centerOnScreen();
        currStage.show();
    }

}
