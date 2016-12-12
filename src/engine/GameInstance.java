package engine;

import api.IGameInstance;
import battle.controller.BattleController;
import battle.model.BattleModel;
import battle.model.Difficulty;
import battle.view.BattleView;
import block.*;
import com.thoughtworks.xstream.mapper.Mapper;
import grid.Grid;
import grid.GridManager;
import grid.GridWorld;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import player.Player;
import player.PlayerDirection;
import player.PlayerUpdate;
import xml.GridXMLHandler;

import java.util.ArrayList;
import java.util.Collection;
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

    private GridXMLHandler xmlHandler;
    private GridManager myGridManager;
    private Grid myGrid;
    private Player myPlayer;

    private int myScore;
    private GameStatus myStatus;
    private List<BlockUpdate> blockUpdates;
    private BattleController battleController;

    public GameInstance(Player player, GridManager gridManager) {
        xmlHandler = new GridXMLHandler();
        myGridManager = gridManager;
        myGrid = myGridManager.getCurrentGrid();
        myPlayer = player;
        myScore = 0;
        myStatus = new GameStatus();
        blockUpdates = new ArrayList<>();
    }

    public void changeGrid(int index) {
        myGridManager.changeGrid(index);
        myGrid = myGridManager.getCurrentGrid();
    }

    public void processInput(UserInstruction input) {
        int row = myPlayer.getRow();
        int col = myPlayer.getCol();
        PlayerUpdate playerUpdate = null;
        PlayerDirection direction = myPlayer.getDirection();

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
			    	Collection<Difficulty> choices = new ArrayList<Difficulty>();
			    	choices.add(Difficulty.EASY);
			    	choices.add(Difficulty.MEDIUM);
			    	choices.add(Difficulty.HARD);
			    	
			    	ChoiceDialog box = new ChoiceDialog(Difficulty.EASY, choices);
			    	box.setHeaderText("Enter battle");
			    	box.setContentText("Choose Difficulty");
			    	box.showAndWait();
			    	
			    	Difficulty diff = (Difficulty) box.getSelectedItem();
			    	enterBattle((EnemyBlock)block, diff);
			    }
			    
			    else if (block instanceof PokemonGiverBlock) {
			    	PokemonGiverBlock pokeBlock = (PokemonGiverBlock) block;
			    	
			    	if (pokeBlock.getHasPokemon()) {
			    		pokeBlock.setHasPokemon(false);
					    blockUpdates = block.talkInteract(myPlayer);
						playerUpdate = PlayerUpdate.TALK;
						setChanged();
			    	}
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

    private void enterBattle(EnemyBlock enemy, Difficulty diff) {
        Stage primaryStage = new Stage();
        BattleView view = new BattleView(diff, "resources/images/battles/background/background-1.jpg");
        BattleModel model = new BattleModel(myPlayer, enemy);
        BattleController controller = new BattleController(view, model);
        primaryStage.setScene(controller.getView().getScene());
        primaryStage.show();
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
     * Handles the case where the player changes direction
     * @param direction - the new direction the player will face
     * @return the player update type (DIRECTION)
     */
    private PlayerUpdate handleDirection(PlayerDirection direction) {
        myPlayer.setDirection(direction);
        setChanged();
        return PlayerUpdate.DIRECTION;
    }

    /**
     * Gets the block that the player is facing
     * @param row - the row of the player
     * @param col - the column of the player
     * @param direction - the direction of the player
     * @return the block the player is facing
     */
    private Block blockInFacedDirection(int row, int col, PlayerDirection direction) {
        switch (direction) {
            case NORTH:
                return myGrid.getBlock(row - 1, col);
            case SOUTH:
                return myGrid.getBlock(row + 1, col);
            case EAST:
                return myGrid.getBlock(row, col + 1);
            case WEST:
                return myGrid.getBlock(row, col - 1);
            default:
                return null;
        }
    }

    public List<BlockUpdate> getInteractions() {
        List<BlockUpdate> tempList = new ArrayList<>();
        tempList.addAll(blockUpdates);
        blockUpdates.clear();
        return tempList;
    }

    public void saveEngine(String file) {
        GridWorld gridWorld = new GridWorld(myGridManager, myGridManager.getMusic());
        xmlHandler.saveContents(file, gridWorld, myPlayer);
    }

    /***** GETTERS *****/

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

    public String getMusic() {
        return myGridManager.getMusic();
    }
}
