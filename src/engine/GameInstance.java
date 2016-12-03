package engine;

import api.IGameInstance;
import block.Block;
import grid.Grid;
import grid.GridWorld;
import grid.RenderedGrid;
import player.Player;
import player.PlayerDirection;
import xml.GridXMLHandler;

import java.util.Observable;

/**
 * This class holds all of the information pertaining to a game instance
 * @author Aninda Manocha, Filip Mazurek
 */

public class GameInstance extends Observable implements IGameInstance {

    private static final PlayerDirection NORTH = PlayerDirection.NORTH;
    private static final PlayerDirection SOUTH = PlayerDirection.SOUTH;
    private static final PlayerDirection EAST = PlayerDirection.EAST;
    private static final PlayerDirection WEST = PlayerDirection.WEST;
    private GridWorld myGridWorld;
	private Grid myGrid;
	private RenderedGrid myRenderedGrid;
    private Player myPlayer;
	private int myScore;
	private GameStatus myStatus;
	
	public GameInstance(Player player, GridWorld gridWorld) {
	    myGridWorld = gridWorld;
	    myGrid = myGridWorld.getCurrentGrid();
	    myRenderedGrid = new RenderedGrid(myGrid);
        myPlayer = player;
	    myScore = 0;
		myStatus = new GameStatus();
	}

	public GridWorld getGridWorld() {
	    return myGridWorld;
    }

	public Grid getGrid() {
		return myGrid;
	}

	public RenderedGrid getRenderedGrid() {
	    return myRenderedGrid;
    }

    public Player getPlayer() {
        return myPlayer;
    }

	public int getScore() {
		return myScore;
	}

	public GameStatus getGameStatus() {
		return myStatus;
	}
	
	public void processInput(UserInstruction input) {
		Block newBlock = null; //TODO
		int row = myPlayer.getRow();
		int col = myPlayer.getCol();
		boolean move = false;
		PlayerDirection direction = myPlayer.getDirection();
		switch (input) {
			case UP:
			    if(direction == NORTH) {
                    newBlock = myGrid.getBlock(row - 1, col);
                    move = true;
                }
                else {
			        myPlayer.setDirection(PlayerDirection.NORTH);
			        setChanged();
                }
				break;
			case DOWN:
				System.out.println("yolo");
			    if(direction == SOUTH) {
                    newBlock = myGrid.getBlock(row+1, col);
                    move = true;
                }
                else {
			        myPlayer.setDirection(SOUTH);
			        setChanged();
                }
				break;
			case RIGHT:
			    if(direction == EAST) {
                    newBlock = myGrid.getBlock(row, col+1);
                    move = true;
                }
                else {
			        myPlayer.setDirection(EAST);
			        setChanged();
                }
				break;
			case LEFT:
			    if(direction == WEST) {
                    newBlock = myGrid.getBlock(row, col-1);
                    move = true;
                }
                else {
			        myPlayer.setDirection(WEST);
			        setChanged();
                }
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
			    // TODO: talk interaction
			    Block talkBlock = blockInFacedDirection(row, col, direction);
                talkBlock.talkInteract("hello");
			default:
				break;
		}
		
		if (move && inBounds(newBlock) && isWalkable(newBlock)) {
			myPlayer.setRow(newBlock.getRow());
			myPlayer.setCol(newBlock.getCol());

			// TODO: do the step on interaction
            // newBlock.doStepOnInteraction(myPlayer);
			System.out.println("hi");
			setChanged();
		}
        notifyObservers();
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

	private Block blockInFacedDirection(int row, int col, PlayerDirection direction) {
	    switch (direction) {
            case NORTH:
                return myGrid.getBlock(row - 1, col);
            case SOUTH:
                return myGrid.getBlock(row+1, col);
            case EAST:
                return myGrid.getBlock(row, col+1);
            case WEST:
                return myGrid.getBlock(row, col-1);
            default:
                // TODO: throw custom exception--player is not facing in any direction
                return null;
        }
    }
}
