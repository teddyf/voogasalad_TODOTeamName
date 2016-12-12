package xml;

import grid.Grid;
import grid.GridWorld;
import player.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Handles saving a GridManager and PLayer to XML.
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
	 * Returns the GridManager and Player represented by a XML file.
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
	
	/**
	 * For testing purposes.
	 */
	/*public static void main(String[] args) {
		GridXMLHandler test = new GridXMLHandler();
		
		GridManager gridWorld = new GridManager();
		Grid grid = new Grid(0,2, 2); // grid of index 0
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				grid.setBlock(row, col, new CommunicatorBlock("Test Block", row, col));
			}
		}
		gridWorld.addGrid(grid);
		Player player = new Player("Test Player", 0, 0, 0);
		
		test.saveContents("data/gamefiles/test.xml", gridWorld, player);
		GridWorldAndPlayer contents = test.loadContents("data/gamefiles/test.xml");
		test.saveContents("data/gamefiles/test2.xml", contents.getGridWorld(), contents.getPlayer());
	}*/
}
