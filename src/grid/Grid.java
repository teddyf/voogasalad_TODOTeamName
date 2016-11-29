package grid;

import block.Block;
import block.BlockType;
import block.DecorationBlock;


/**
 * The rectangular grid in which all the block objects may be placed.
 *
 * @author Filip Mazurek
 */
public class Grid implements IGrid {
    private static final String DEFAULT_BLOCK = "DEFAULT";

    private int myNumRows;
    private int myNumColumns;
    private Block[][] myGrid;
    private double currentIdentification; // to use for connected block images, to make it easier for front end display

    public Grid(int numRows, int numColumns) {
        currentIdentification = 0;
        myNumRows = numRows;
        myNumColumns = numColumns;

        myGrid = new Block[numRows][numColumns];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                myGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK, i, j);
            }
        }
    }

    public int getNumRows() {
    	return myNumRows;
    }
    
    public int getNumCols() {
    	return myNumColumns;
    }

    public IGrid getGrid() {
    	return this;
    }
    
    public Block getBlock(int row, int col) {
        return myGrid[row][col];
    }

    //TODO: implement giving out a shallow representation
//    public ShallowBlock[][] getGridForRendering() {
//        return myShallowRepresentation;
//    }

    public void setBlock(int row, int col, String name, BlockType someType) {
//      Class<Block> clazz = Block.class;
//      Constructor<Block> ctor = clazz.getConstructor(Block.class);
//      Block instance = ctor.newInstance(5);
          // TODO: make the block class by reflection

  }
}
