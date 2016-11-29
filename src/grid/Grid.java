package grid;

import block.Block;
import block.BlockType;
import block.DecorationBlock;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The rectangular grid in which all the block objects may be placed.
 *
 * @author Filip Mazurek
 */
public class Grid implements IGrid {

    private ResourceBundle myBlockPaths;
    private String myBlockPathsPath = "resources/properties/blockPaths";
    private static final String DEFAULT_BLOCK = "DEFAULT";
    private int myNumRows;
    private int myNumColumns;
    private Block[][] myGrid;
    private ShallowBlock[][] myShallowGrid;

    public Grid(int numRows, int numColumns) {
        myBlockPaths = ResourceBundle.getBundle(myBlockPathsPath);
        myNumRows = numRows;
        myNumColumns = numColumns;

        myGrid = new Block[numRows][numColumns];
        myShallowGrid = new ShallowBlock[numRows][numColumns];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                myGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK, i, j);
                myShallowGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK, i, j);
            }
        }
    }

    public void setBlock(int row, int col, String name, BlockType someType, List<Object> parameters) {
        try {
            Class<?> blockClass = Class.forName(myBlockPaths.getString(someType.toString()));
            Constructor<?> ctor = blockClass.getDeclaredConstructor(List.class);
            Block blockObject = createBoardObject(blockClass, ctor, parameters, row, col);
            myGrid[row][col] = blockObject;
            myShallowGrid[row][col] = blockObject;
        }
        catch (ClassNotFoundException e) {
            // TODO: custom exception that there is no such block type (may be bad path in properties)
        }
        catch (NoSuchMethodException e) {
            // TODO: no such constructor
        }
    }

    private Block createBoardObject(Class<?> commandClass, Constructor<?> ctor, List<Object> parameters, int row, int col) {
        try {
            Object blockObject = ctor.newInstance();
            return (Block) blockObject;
        }
        catch (Exception e) {
            // TODO: can't create a new block
        }
        return new DecorationBlock(DEFAULT_BLOCK, row, col); // TODO: better default? Currently just place a default square
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
}
