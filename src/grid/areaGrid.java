package grid;

import boardObjects.Block;
import boardObjects.DecorationBlock;

import java.lang.reflect.Constructor;


/**
 * The rectangular grid in which all the block objects may be placed.
 *
 * @author Filip Mazurek
 */
public class areaGrid {
    private static final String DEFAULT_BLOCK = "DEFAULT";

    private int myNumRows;
    private int myNumColumns;
    private Block[][] myGrid;
    private double currentIdentification; // to use for connected block images, to make it easier for front end display

    public areaGrid(int numRows, int numColumns) {
        currentIdentification = 0;
        myNumRows = numRows;
        myNumColumns = numColumns;

        myGrid = new Block[numRows][numColumns];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                myGrid[i][j] = new DecorationBlock(DEFAULT_BLOCK);
            }
        }
    }

    public void setBlock(int startRow, int startColumn, int endRow, int endColumn, String name, BlockType someType) {
//        Block settingBlock = Class.forName();
//        Constructor blockConstructor = Con
            // TODO: make the block class by reflection
        for(int i = startRow; i < endRow; i++) {
            for(int j = startColumn; j < endColumn; j++) {

            }
        }
    }

}
