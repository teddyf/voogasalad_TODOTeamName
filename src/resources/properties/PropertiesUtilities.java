package resources.properties;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Harshil Garg
 *
 * This class encapsulates utilities for interfacing with properties files.
 */
public class PropertiesUtilities {

    private ResourceBundle resources;

    public PropertiesUtilities(ResourceBundle resources) {
        this.resources = resources;
    }
	
	public String getStringProperty(String key) {
        return resources.getString(key);
	}

    public double getDoubleProperty(String key) {
        return Double.parseDouble(resources.getString(key));
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(resources.getString(key));
    }
}
