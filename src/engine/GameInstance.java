package engine;

import boardObjects.Block;
import engine.backend.GameStatus;
import grid.IGrid;
import player.Player;

public class GameInstance implements IGameInstance {
	
	private Player myPlayer;
	private IGrid myGrid;
	private int myScore;
	private GameStatus myStatus;
	
	public GameInstance() {
		
	}
	
	public Player getPlayer() {
		return myPlayer;
	}
	
	public IGrid getGrid() {
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
	
	private boolean inBounds(Block block) {
		int row = myGrid.getNumRows();
		int col = myGrid.getNumCols();
		
		return (block.getRow() >= 0 && block.getRow() < row && block.getCol() >= 0 && block.getCol() < col); 
	}
	
	private boolean isWalkable(Block block) {
		return block.isWalkable();
	}
}
