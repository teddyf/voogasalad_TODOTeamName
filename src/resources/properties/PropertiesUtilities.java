package resources.properties;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg, Harshil Garg
 *
 * This class encapsulates utilities for interfacing with properties files.
 */
public class PropertiesUtilities {
	
	public String getStringProperty(ResourceBundle resources, String key) {
		return resources.getString(key);
	}

    public double getDoubleProperty(ResourceBundle resources, String key) {
        return Double.parseDouble(resources.getString(key));
    }

    public int getIntProperty(ResourceBundle resources, String key) {
        return Integer.parseInt(resources.getString(key));
    }
}
