package player;

import block.Block;
import block.DecorationBlock;
import exceptions.BadPlayerPlacementException;
import exceptions.DuplicateAttributeException;
import exceptions.DuplicatePlayerException;
import exceptions.NoPlayerException;
import grid.Grid;
import grid.GridManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class manages the player in the editor or engine
 * @author Aninda Manocha
 */

public class PlayerManager implements Observer{

    private Player myPlayer;
    private Grid myGrid;

    public PlayerManager(Grid grid) {
        myGrid = grid;
    }

    public boolean addPlayer(List<String> names, String playerName, int row, int col, int gridIndex) throws BadPlayerPlacementException, DuplicatePlayerException {
        if(!(myGrid.getBlock(row, col).isWalkable())) {
            throw new BadPlayerPlacementException(row, col);
        }
        if(myPlayer == null) {
            myPlayer = new Player(names, playerName, row, col, gridIndex);
            return true;
        }
        else {
            throw new DuplicatePlayerException(myPlayer.getRow(), myPlayer.getCol());
        }
    }

    public void deletePlayer() {
        myPlayer = null;
    }

    public boolean movePlayer(int row, int col) throws BadPlayerPlacementException {
        Block block = myGrid.getBlock(row, col);
        if (block instanceof DecorationBlock) {
            myPlayer.setRow(row);
            myPlayer.setCol(col);
            return true;
        }
        throw new BadPlayerPlacementException(row, col);
    }

    public boolean addPlayerAttribute(String name, double amount, double increment, double decrement) throws DuplicateAttributeException {
        PlayerAttribute playerAttribute = new PlayerAttribute(name, amount, increment, decrement);
        if (!myPlayer.addAttribute(playerAttribute)) {
            throw new DuplicateAttributeException();
        }
        return true;
    }

    public void update(Observable grid, Object update) {
        if (grid instanceof GridManager) {
            PlayerBlockUpdate playerBlockUpdate = (PlayerBlockUpdate)update;
            if (playerBlockUpdate.getUpdate() == PlayerUpdate.ROW) {
                myPlayer.setRow(myPlayer.getRow() - playerBlockUpdate.getOffset());
            } else {
                myPlayer.setCol(myPlayer.getCol() - playerBlockUpdate.getOffset());
            }
        }
    }

    public Player getPlayer() throws NoPlayerException {
        if (myPlayer == null) {
            throw new NoPlayerException();
        }
        return myPlayer;
    }

    public void setPlayer(Player player) {
        myPlayer = player;
    }

    public void setGrid(Grid grid) {
        myGrid = grid;
    }
}
