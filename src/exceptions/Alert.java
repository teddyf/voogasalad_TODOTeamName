package exceptions;

import java.util.ResourceBundle;
/**
 * @author Filip Mazurek
 */
public interface Alert {
    String RESOURCE_PACKAGE = "resources/properties/alerts-text";
    ResourceBundle MY_RESOURCES = ResourceBundle.getBundle(RESOURCE_PACKAGE);

    String BAD_PLAYER_PLACEMENT = MY_RESOURCES.getString("BAD_PLAYER_PLACEMENT");

}
