package xml;

import java.util.ArrayList;
import java.util.List;

import block.Block;

/**
 * Wrapper class on a list of Blocks used for XStream.
 * 
 * @author Daniel Chai
 */
public class Blocks {
	List<Block>	blockList;
	
	public Blocks() {
		blockList = new ArrayList<Block>();
	}
	
	public List<Block> getBlocks() {
		return blockList;
	}
	
	public void addBlock(Block block) {
		blockList.add(block);
	}
}
