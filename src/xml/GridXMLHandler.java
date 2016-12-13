package xml;

import api.Player;
import grid.Grid;
import grid.GridWorld;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Handles saving a GridManager and Player to XML.
 * Handles loading a GridManager and Player from XML.
 * 
 * @author Daniel Chai
 */
public class GridXMLHandler {
	private XStream xstream;
	
	public GridXMLHandler() {
		xstream = new XStream(new DomDriver());
		initXStream();
	}
	
	/**
	 * Saves the XML file representing a GridManager and Player.
	 * Returns the success/failure of the operation.
	 */
	public boolean saveContents(String filePath, GridWorld gridWorld, Player player) {
		String fileContent = xstream.toXML(new GridWorldAndPlayer(gridWorld, player));
		
		try { 
			File file = new File(filePath);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(fileContent);
			out.close();
			
			return true;
		}
		catch (IOException ex) {
			return false;
		}
	}
	
	/**
	 * Returns the GridManager and Player represented by an XML file.
	 */
	public GridWorldAndPlayer loadContents(String filePath) {
		File file = new File(filePath);
		return (GridWorldAndPlayer)xstream.fromXML(file);
	}
	
	private void initXStream() {
		xstream.processAnnotations(GridWorldAndPlayer.class);
		xstream.processAnnotations(GridWorld.class);
		xstream.processAnnotations(Grid.class);
		xstream.processAnnotations(Player.class);
		
		BlockAliasFactory factory = new BlockAliasFactory(xstream);
		//factory.setAlias();
	}
}
