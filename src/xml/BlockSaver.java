package xml;

import block.Block;
import block.CommunicatorBlock;
import block.DecorationBlock;
import block.EnemyBlock;
import block.GateBlock;
import block.ObstacleBlock;
import block.SwitchFloorBlock;
import block.SwitchTouchBlock;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Saves a single Block to XML.
 * 
 * @author Daniel Chai
 */
public class BlockSaver {
	private static final String BLOCK_ALIAS = "block"; 
	
	private XStream mySerializer;
	
	public BlockSaver() {
		mySerializer = new XStream(new DomDriver());
		setAlias();
	}
	
	public String saveBlock(Block block) {
		try {
			return mySerializer.toXML(block) + "\n";	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private void setAlias() {
		mySerializer.alias(BLOCK_ALIAS, CommunicatorBlock.class);
		mySerializer.alias(BLOCK_ALIAS, DecorationBlock.class);
		mySerializer.alias(BLOCK_ALIAS, EnemyBlock.class);
		mySerializer.alias(BLOCK_ALIAS, GateBlock.class);
		mySerializer.alias(BLOCK_ALIAS, ObstacleBlock.class);	
		mySerializer.alias(BLOCK_ALIAS, SwitchFloorBlock.class);
		mySerializer.alias(BLOCK_ALIAS, SwitchTouchBlock.class);
	}
}
