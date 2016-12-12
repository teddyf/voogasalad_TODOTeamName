package grid;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * This class manages all of the grids in the game
 * @author Aninda Manocha, Daniel Chai
 */

@XStreamAlias("gridWorld")
public class GridWorld {
	
	@XStreamImplicit
    private List<Grid> myGrids;

    public GridWorld(GridManager gridManager) {
        myGrids = gridManager.getGrids();
    }

    public List<Grid> getGrids() {
        return myGrids;
    }
}