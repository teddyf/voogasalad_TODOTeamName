package engine;

import api.IGameInstance;
import battle.controller.BattleController;
import battle.model.BattleModel;
import battle.view.BattleView;
import block.*;
import grid.Grid;
import grid.GridWorld;
import javafx.stage.Stage;
import player.Player;
import player.PlayerDirection;
import player.PlayerUpdate;

import java.util.ArrayList;
import java.util.List;
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
    private Player myPlayer;
	private int myScore;
	private GameStatus myStatus;
	private List<BlockUpdate> blockUpdates;
	private BattleController battleController;
	
	public GameInstance(Player player, GridWorld gridWorld) {
	    myGridWorld = gridWorld;
	    myGrid = myGridWorld.getCurrentGrid();
        myPlayer = player;
	    myScore = 0;
		myStatus = new GameStatus();
		blockUpdates = new ArrayList<>();
	}

	public GridWorld getGridWorld() {
	    return myGridWorld;
    }

	public Grid getGrid() {
		return myGrid;
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

	public List<BlockUpdate> getBlockUpdates() {
	    return blockUpdates;
    }
	
	public void processInput(UserInstruction input) {
		int row = myPlayer.getRow();
		int col = myPlayer.getCol();
		PlayerUpdate playerUpdate = null;
		PlayerDirection direction = myPlayer.getDirection();
		System.out.println(direction);
		
		switch (input) {
			case UP:
			    if(direction == NORTH) {
                    playerUpdate = handleMovement(row-1, col, PlayerUpdate.ROW);
                } else {
			        playerUpdate = handleDirection(PlayerDirection.NORTH);
                }
				break;
			case DOWN:
				if(direction == SOUTH) {
                    playerUpdate = handleMovement(row+1, col, PlayerUpdate.ROW);
                } else {
                    playerUpdate = handleDirection(PlayerDirection.SOUTH);
                }
				break;
			case RIGHT:
			    if(direction == EAST) {
                    playerUpdate = handleMovement(row, col+1, PlayerUpdate.COLUMN);
                } else {
                    playerUpdate = handleDirection(PlayerDirection.EAST);
                }
				break;
			case LEFT:
			    if(direction == WEST) {
                    playerUpdate = handleMovement(row, col-1, PlayerUpdate.COLUMN);
                } else {
                    playerUpdate = handleDirection(PlayerDirection.WEST);
                }
				break;
			case TALK:
			    Block block = blockInFacedDirection(row, col, direction);
			    
			    if (block instanceof EnemyBlock) {
			    	enterBattle((EnemyBlock)block, BattleView.Difficulty.HARD);
			    }
			    else {
			    	blockUpdates = block.talkInteract(myPlayer);
					playerUpdate = PlayerUpdate.TALK;
					setChanged();
			    }
			default:
				break;
		}
		
        notifyObservers(playerUpdate);
	}
	
	private void enterBattle(EnemyBlock enemy, BattleView.Difficulty diff) {
        Stage primaryStage = new Stage();
		//TODO take in a difficulty parameter from block
		BattleView view = new BattleView(diff, "resources/images/battles/background/background-1.jpg");
		BattleModel model = new BattleModel(myPlayer, enemy);
		BattleController controller = new BattleController(view, model);
		primaryStage.setScene(controller.getView().getScene());
		primaryStage.show();
	}

    /**
     * Handles the case where the player moves
     * @param row - the row of the player after it moves
     * @param col - the column of the player after it moves
     * @param playerUpdate - the player update type depending on whether the row or column changes (ROW or COLUMN)
     * @return the player update type
     */
	private PlayerUpdate handleMovement(int row, int col, PlayerUpdate playerUpdate) {
        Block newBlock = myGrid.getBlock(row, col);
        if (inBounds(newBlock) && isWalkable(newBlock)) {
            myPlayer.setRow(newBlock.getRow());
            myPlayer.setCol(newBlock.getCol());
            blockUpdates = newBlock.stepInteract(myPlayer);
            setChanged();
        }
        return playerUpdate;
    }

	/**
	 * Determines if a block is within the bounds of the grid
	 * @param block - the block
	 * @return whether the block is in bounds
	 */
	private boolean inBounds(Block block) {
		if (block == null) {
		    return false;
        } else {
		    return true;
        }
	}

    /**
     * Determines if a block is walkable
     * @param block - the block
     * @return whether the block is walkable
     */
	private boolean isWalkable(Block block) {
		return block.isWalkable();
	}

    /**
     * Handles the case where the player changes direction
     * @param direction - the new direction the player will face
     * @return the player update type (DIRECTION)
     */
    private PlayerUpdate handleDirection(PlayerDirection direction) {
        myPlayer.setDirection(direction);
        setChanged();
        return PlayerUpdate.DIRECTION;
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

    public List<BlockUpdate> getInteractions() {
        return blockUpdates;
    }

    public void handleInteraction() {
        blockUpdates.clear();
    }

    public void changeGrid(int index) {
        //myGridWorld.setCurrentIndex(index);
        myGrid = myGridWorld.getCurrentGrid();
    }
}
