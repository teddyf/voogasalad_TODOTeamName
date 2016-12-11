package block;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

/**
 * This class generates block ui.scenes.editor.objects
 * @author Aninda Manocha, Filip Mazurek
 */

public class BlockFactory {

    public static final String DEFAULT_BLOCK = "DEFAULT";
    private ResourceBundle myBlockPaths = ResourceBundle.getBundle("resources/properties/block-paths");

    public Block createBlock(String name, BlockType blockType, int row, int col) {
        try {
            Class<?> blockClass = Class.forName(myBlockPaths.getString(blockType.toString()));
            Constructor<?> constructor = blockClass.getDeclaredConstructor(String.class, int.class, int.class);
            Object block = constructor.newInstance(name, row, col);
            System.out.println(block.getClass());
            return (Block) block;
        }
        catch (Exception e) {
            // TODO: can't create a new block
            System.out.println(e.toString());
        }
        // TODO:  add to resource file
        return new DecorationBlock("resources/images/tiles/ground/grass-1.png", row, col); // TODO: better default? Currently just place a default square
    }
}
