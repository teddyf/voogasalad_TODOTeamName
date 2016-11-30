package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import grid.Grid;
import grid.GridWorld;
import grid.RenderedGrid;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController {
    private GridWorld gridWorld;
    private BlockFactory blockFactory;
    private Grid currentGrid;
    private RenderedGrid renderedGrid;

    public EditorController() {
        gridWorld = new GridWorld();
        blockFactory = new BlockFactory();
    }

    public void addGrid(int row, int col) {
        Grid newGrid = new Grid(row, col);
        gridWorld.addGrid(newGrid);
        gridWorld.updateGrid();
        currentGrid = newGrid;
        renderedGrid = new RenderedGrid(newGrid);
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
    }

    public String getBlock(int row, int col) {
        return renderedGrid.get(row, col);
    }
}
