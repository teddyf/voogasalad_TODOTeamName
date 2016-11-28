package grid;

import boardObjects.Block;
import boardObjects.DecorationBlock;
import boardObjects.ShallowBlock;

import java.lang.reflect.Constructor;


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
    private ShallowBlock[][] myShallowGrid;
    private double currentIdentification; // to use for connected block images, to make it easier for front end display

    public Grid(int numRows, int numColumns) {
        currentIdentification = 0;
        myNumRows = numRows;
        myNumColumns = numColumns;

        myGrid = new Block[numRows][numColumns];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                myGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK, i, j);
                myShallowGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK, i, j); // should be a bunch of shallow blocks(?)
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

    public ShallowBlock[][] getGridForRendering() {
        return myShallowGrid;
    }

    public void setBlock(int row, int col, String name, BlockType someType) {
//      Class<Block> clazz = Block.class;
//      Constructor<Block> ctor = clazz.getConstructor(Block.class);
//      Block instance = ctor.newInstance(5);
          // TODO: make the block class by reflection

  }
}
