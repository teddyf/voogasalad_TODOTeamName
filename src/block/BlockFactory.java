package block;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class generates block objects
 * @author Aninda Manocha, Filip Mazurek
 */

public class BlockFactory {

    public static final String DEFAULT_BLOCK = "DEFAULT";
    private ResourceBundle myBlockPaths;

    public BlockFactory() {
        myBlockPaths = ResourceBundle.getBundle("resources/properties/blockPaths");
    }

    public Block createBlock(String name, BlockType blockType, int row, int col) {
        try {
            Class<?> blockClass = Class.forName(myBlockPaths.getString(blockType.toString()));
            Constructor<?> constructor = blockClass.getDeclaredConstructor(List.class);
            Object block = constructor.newInstance(name, blockType, row, col);
            return (Block) block;
        }
        catch (Exception e) {
            // TODO: can't create a new block
        }
        return new DecorationBlock(DEFAULT_BLOCK, row, col); // TODO: better default? Currently just place a default square
    }
}
