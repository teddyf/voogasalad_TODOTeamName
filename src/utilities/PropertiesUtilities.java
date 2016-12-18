package utilities;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 *         <p>
 *         This class encapsulates utilities for interfacing with properties files.
 */
public class PropertiesUtilities {

    private ResourceBundle resources;

    public PropertiesUtilities(ResourceBundle resources) {
        this.resources = resources;
    }

    /**
     * @return a String value from a properties file
     */
    public String getStringProperty(String key) {
        return resources.getString(key);
    }

    /**
     * @return a double value from a properties file
     */
    public double getDoubleProperty(String key) {
        return Double.parseDouble(resources.getString(key));
    }

    /**
     * @return an int value from a properties file
     */
    public int getIntProperty(String key) {
        return Integer.parseInt(resources.getString(key));
    }
}
