package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import block.CommunicatorBlock;
import grid.Grid;
import grid.GridWorld;
import player.Player;

/**
 * Handles saving a GridWorld and PLayer to XML.
 * Handles loading a GridWorld and Player from XML.
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
	 * Returns the XML String that represents a GridWorld and Player.
	 */
	public String saveContents(GridWorld gridWorld, Player player) {
		GridWorldAndPlayer contents = new GridWorldAndPlayer(gridWorld, player);
		return xstream.toXML(contents);
	}
	
	/**
	 * Returns the GridWorld and Player represented by a XML String.
	 */
	public GridWorldAndPlayer loadContents(String xmlContent) {
		return (GridWorldAndPlayer)xstream.fromXML(xmlContent);
	}
	
	private void initXStream() {
		xstream.processAnnotations(GridWorldAndPlayer.class);
		xstream.processAnnotations(GridWorld.class);
		xstream.processAnnotations(Grid.class);
		xstream.processAnnotations(Player.class);
	}
	
	/**
	 * For testing purposes.
	 */
	public static void main(String[] args) {
		GridXMLHandler test = new GridXMLHandler();
		
		GridWorld gridWorld = new GridWorld();
		Grid grid = new Grid(2, 2);
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				grid.setBlock(row, col, new CommunicatorBlock("Test Block", row, col));
			}
		}
		gridWorld.addGrid(grid);
		
		Player player = new Player("Test Player", 0, 0);
		
		String xml = test.saveContents(gridWorld, player);
		System.out.println(xml);
		
		GridWorldAndPlayer newContents = test.loadContents(xml);
		String newXml = test.saveContents(newContents.getGridWorld(), newContents.getPlayer());
		System.out.println(xml.equals(newXml));
	}
}
