package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.DecorationBlock;
import grid.Grid;
import grid.GridWorld;
import sun.jvm.hotspot.ui.Editor;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController {
    private GridWorld gridWorld;
    private BlockFactory blockFactory;
    private Grid currentGrid;

    public EditorController() {
        gridWorld = new GridWorld();
        blockFactory = new BlockFactory();
    }

    public void addGrid(int row, int col) {
        Grid newGrid = new Grid(row, col);
        gridWorld.addGrid(newGrid);
        gridWorld.updateGrid();
        currentGrid = newGrid;
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
    }
}
