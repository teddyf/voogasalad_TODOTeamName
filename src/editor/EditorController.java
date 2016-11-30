package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import grid.Grid;
import grid.GridWorld;
import grid.RenderedGrid;
import player.Player;
import interactions.Interaction;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

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
    private int myNumRows;
    private int myNumColumns;
    private Player player;
    private GridXMLHandler xmlHandler;

    public EditorController() {
        gridWorld = new GridWorld();
        blockFactory = new BlockFactory();
        xmlHandler = new GridXMLHandler();
    }

    public void addGrid(int row, int col) {
        Grid newGrid = new Grid(row, col);
        gridWorld.addGrid(newGrid);
        gridWorld.updateGrid();
        changeGrid();
    }

    public void changeGrid() {
        currentGrid = gridWorld.getCurrentGrid();
        renderedGrid = new RenderedGrid(currentGrid);
        myNumRows =  currentGrid.getNumRows();
        myNumColumns = currentGrid.getNumCols();
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        currentGrid.setBlock(row, col, block);
    }

    public void addPlayer(String name, int row, int col) {
        player = new Player(name, row, col);
    }

    public void movePlayer(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    public void addInteraction(int row, int col, Interaction interaction){
        currentGrid.getBlock(row, col).addInteraction(interaction);
    }

    public int getRow() {
        return myNumRows;
    }

    public int getCol() {
        return myNumColumns;
    }

    public String getBlock(int row, int col) {
        return renderedGrid.get(row, col);
    }

    public void saveEditor(String file) {
        xmlHandler.saveContents(gridWorld, player);
    }

    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        player = gridWorldAndPlayer.getPlayer();
        gridWorld = gridWorldAndPlayer.getGridWorld();
        changeGrid();
        
    }
}
