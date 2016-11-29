package xml;

import boardObjects.Block;
import boardObjects.CommunicatorBlock;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Saves a single Block to XML.
 * 
 * @author Daniel Chai
 */
public class BlockSaver {
	private XStream mySerializer;
	
	public BlockSaver() {
		mySerializer = new XStream(new DomDriver());
	}
	
	public String saveBlock(Block block) {
		try {
			return mySerializer.toXML(block);	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		BlockSaver blockSaver = new BlockSaver();
		Block block = new CommunicatorBlock("Test Block", 0, 0);
		
		blockSaver.saveBlock(block);
	}
}
