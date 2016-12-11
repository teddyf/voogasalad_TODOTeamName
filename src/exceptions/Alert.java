package exceptions;

import java.util.ResourceBundle;

/**
 * Interface which serves to hold error message strings
 * @author Filip Mazurek
 */
public interface Alert {
    String RESOURCE_PACKAGE = "resources/properties/alerts-text";
    ResourceBundle MY_RESOURCES = ResourceBundle.getBundle(RESOURCE_PACKAGE);

    // Alerts
    String NOT_COMMUNICABLE_BLOCK = MY_RESOURCES.getString("NOT_COMMUNICABLE_BLOCK");
    String UNLINKABLE_BLOCKS = MY_RESOURCES.getString("UNLINKABLE_BLOCKS");

    // Exceptions
    String BAD_PLAYER_PLACEMENT = MY_RESOURCES.getString("BAD_PLAYER_PLACEMENT");
    String DUPLICATE_PLAYER = MY_RESOURCES.getString("DUPLICATE_PLAYER");
    String DUPLICATE_PLAYER_ATTRIBUTE = MY_RESOURCES.getString("DUPLICATE_PLAYER_ATTRIBUTE");
    String LARGE_GRID = MY_RESOURCES.getString("LARGE_GRID");
    String NEGATIVE_GRID = MY_RESOURCES.getString("NEGATIVE_GRID");
    String NO_PLAYER = MY_RESOURCES.getString("NO_PLAYER");

    // Warnings
    String DELETE_PLAYER = MY_RESOURCES.getString("DELETE_PLAYER");
}
