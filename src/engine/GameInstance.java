package engine;

import api.IGameInstance;
import block.Block;
import engine.backend.GameStatus;
import grid.Grid;
import grid.GridWorld;
import player.Player;
import xml.GridXMLHandler;

/**
 * This class holds all of the information pertaining to a game instance
 *
 */

public class GameInstance implements IGameInstance {

    private Player myPlayer;
    private GridWorld myGridWorld;
	private Grid myGrid;
	private int myScore;
	private GameStatus myStatus;
	
	public GameInstance(GridXMLHandler xmlHandler) {
	    //myPlayer = xmlHandler.getPlayer();
	    //myGridWorld = xmlHandler.getGridWorld();
	    //myGrid = myGridWorld;
	    myScore = 0;
		myStatus = new GameStatus();
	}
	
	public Player getPlayer() {
		return myPlayer;
	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public int getScore() {
		return myScore;
	}

	public GameStatus getGameStatus() {
		return myStatus;
	}
	
	/*public Player loadPlayer() {
		return null;
	}
	
	public void savePlayer() {
		
	}

	public void resetPlayer() {

	}*/
	
	public void movePlayer(UserInstruction input) {
		Block newBlock = null; //TODO
		int row = myPlayer.getRow();
		int col = myPlayer.getCol();
		switch (input) {
			case UP: 
				newBlock = myGrid.getBlock(row-1, col);
				break;
			case DOWN: 
				newBlock = myGrid.getBlock(row+1, col);
				break;
			case RIGHT:
				newBlock = myGrid.getBlock(row, col+1);
				break;
			case LEFT:
				newBlock = myGrid.getBlock(row, col-1);
				break;
			case NORTHEAST:
				break;
			case NORTHWEST:
				break;
			case SOUTHEAST:
				break;
			case SOUTHWEST:
				break;
			case TALK:
			default:
				//TODO: custom exception
				break;
		}
		
		if (inBounds(newBlock) && isWalkable(newBlock)) {
			myPlayer.setRow(newBlock.getRow());
			myPlayer.setCol(newBlock.getCol());
		}
	}

	/**
	 * Determines if a block is within the bounds of the grid
	 * @param block - the block
	 * @return whether the block is in bounds
	 */
	private boolean inBounds(Block block) {
		int row = myGrid.getNumRows();
		int col = myGrid.getNumCols();
		
		return (block.getRow() >= 0 && block.getRow() < row && block.getCol() >= 0 && block.getCol() < col); 
	}

    /**
     * Determines if a block is walkable
     * @param block - the block
     * @return whether the block is walkable
     */
	private boolean isWalkable(Block block) {
		return block.isWalkable();
	}
}
