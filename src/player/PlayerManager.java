package player;

import exceptions.BadPlayerPlacementException;
import exceptions.DuplicatePlayerException;

import java.util.List;

/**
 * Created by anindamanocha on 12/11/16.
 */
public class PlayerManager {
    private Player myPlayer;

    public PlayerManager() {

    }

    public boolean addPlayer(List<String> names, String playerName, int row, int col, int gridIndex) throws BadPlayerPlacementException, DuplicatePlayerException {
        if(!(currentGrid.getBlock(row, col).isWalkable())) {
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
}
