package resources.properties;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *
 * This class encapsulates utilities for interfacing with properties files.
 */
public class PropertiesUtilities {

    public double getDoubleProperty(ResourceBundle resources, String key) {
        return Double.parseDouble(resources.getString(key));
    }

    public int getIntProperty(ResourceBundle resources, String key) {
        return Integer.parseInt(resources.getString(key));
    }
}
