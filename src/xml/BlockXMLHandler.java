package xml;

import api.Block;
import block.DecorationBlock;
import block.EnemyBlock;
import block.GateBlock;
import block.ObstacleBlock;
import block.SwitchFloorBlock;
import block.SwitchTouchBlock;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Handles saving a single Block to XML. 
 * Handles loading a single Block from XML.
 * No longer used, but kept for reference.
 * @deprecated
 * 
 * @author Daniel Chai
 */
public class BlockXMLHandler {
	public static final String BLOCK_ALIAS = "block"; 
	
	private XStream xstream;
	
	public BlockXMLHandler() {
		xstream = new XStream(new DomDriver());
		initXStream();
	}
	
	public String saveBlock(Block block) {
		try {
			return xstream.toXML(block) + "\n";	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Block loadBlock(String xmlContent) {
		try {
			return (Block)xstream.fromXML(xmlContent);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private void initXStream() {
		xstream.alias(BLOCK_ALIAS, DecorationBlock.class);
		xstream.alias(BLOCK_ALIAS, EnemyBlock.class);
		xstream.alias(BLOCK_ALIAS, GateBlock.class);
		xstream.alias(BLOCK_ALIAS, ObstacleBlock.class);	
		xstream.alias(BLOCK_ALIAS, SwitchFloorBlock.class);
		xstream.alias(BLOCK_ALIAS, SwitchTouchBlock.class);
	}
}
