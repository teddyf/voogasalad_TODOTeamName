package block;

import api.Block;
import block.blocktypes.BlockType;
import exceptions.BlockCreationException;
import java.lang.reflect.Constructor;
import java.util.ResourceBundle;


/**
 * This class generates block ui.scenes.editor.objects
 *
 * @author Aninda Manocha, Filip Mazurek
 */
public class BlockFactory {

    private ResourceBundle myBlockPaths = ResourceBundle.getBundle("resources/properties/block-paths");

    /**
     * Use reflection to create the block requested by the front end class. Uses the block-paths.properties file to
     * decide which block to make based on which blockType is selected.
     *
     * @param name: the string file path which the front end uses to render the block
     * @param blockType: the class of block which to create.
     * @param row: row property to give the block
     * @param col: column property to give the block
     * @return the created block
     */
    public Block createBlock(String name, BlockType blockType, int row, int col) throws BlockCreationException {
        try {
            Class<?> blockClass = Class.forName(myBlockPaths.getString(blockType.toString()));
            Constructor<?> constructor = blockClass.getDeclaredConstructor(String.class, int.class, int.class);
            Object block = constructor.newInstance(name, row, col);
            return (Block) block;
        }
        catch (Exception e) {
            throw new BlockCreationException();
        }
    }
}
