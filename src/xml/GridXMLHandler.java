package xml;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import block.Block;
import block.BlockType;
import grid.Grid;

/**
 * Handles saving a grid of Blocks to XML.
 * Handles loading a  grid of Blocks from XML.
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
	 * Returns the XML String that represents a grid of blocks.
	 */
	public String saveGrid(Grid grid) {
		Blocks blocks = new Blocks();
		
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				Block block = grid.getBlock(row, col);
				blocks.addBlock(block);
			}
		}
		
		return xstream.toXML(blocks);
	}
	
	/**
	 * Returns the grid of blocks represented by a XML String.
	 */
	public Grid loadGrid(String xmlConent) {
		Blocks blocks = (Blocks)xstream.fromXML(xmlConent);
		List<Block> blockList = blocks.getBlocks();
		
		if (blockList.size() == 0) {
			return new Grid(0, 0);
		}
		
		Block lastBlock = blockList.get(blockList.size() - 1);
		int numRows = lastBlock.getRow() + 1;
		int numCols = lastBlock.getCol() + 1;
		
		Grid grid = new Grid(numRows, numCols);
		
		for (int i = 0; i < blockList.size(); i++) {
			Block currBlock = blockList.get(i);
			grid.setBlock(currBlock.getRow(), currBlock.getCol(), currBlock.getName(), 
					currBlock.getBlockType(), new ArrayList<Object>());
		}
		
		return grid;
	}
	
	private void initXStream() {
		xstream.alias("blocks", Blocks.class);
		xstream.addImplicitCollection(Blocks.class, "blockList");
	}
	
	/**
	 * For testing purposes.
	 */
	public static void main(String[] args) {
		GridXMLHandler test = new GridXMLHandler();
		
		// makes test grid of blocks
		Grid grid = new Grid(3, 3);
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				grid.setBlock(row, col, "", BlockType.COMMUNICATOR, new ArrayList<Object>());
			}
		}
		
		// converts grid to xml
		String xml = test.saveGrid(grid);
		System.out.println(xml);
		
		// converts xml to new grid
		// converts new grid to new xml
		// checks xml is same as new xml
		Grid newGrid = test.loadGrid(xml);
		String newXml = test.saveGrid(newGrid);
		System.out.println(xml.equals(newXml));
	}
}
