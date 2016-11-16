package engine;

import editor.backend.Block;
import editor.backend.Grid;
import engine.backend.GameStatus;
import player.Player;

public class GameInstance implements IGameInstance {
	
	private Player myPlayer;
	private Grid myGrid;
	private int myScore;
	private GameStatus myStatus;
	
	public GameInstance() {
		
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
	
	public Player loadPlayer() {
		return null;
	}
	
	public void savePlayer() {
		
	}

	public void resetPlayer() {

	}
	
	public void movePlayer(UserInstruction input) {
		Block newBlock;
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
			case TALK:
			default:
				break;
		}
		
		if (inBounds(newBlock) && isWalkable(newBlock)) {
			myPlayer.setRow(newBlock.getRow());
			myPlayer.setCol(newBlock.getCol());
		}
	}
	
	private boolean inBounds(Block block) {
		int row = myGrid.getNumRows();
		int col = myGrid.getNumCols();
		
		return (block.getRow() >= 0 && block.getRow() < row && block.getCol() >= 0 && block.getCol() < col); 
	}
	
	private boolean isWalkable(Block block) {
		return block.isWalkable();
	}
}
