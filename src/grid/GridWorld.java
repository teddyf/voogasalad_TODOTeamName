package grid;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * This class manages all of the grids in the game
 * @author Aninda Manocha, Daniel Chai
 */

@XStreamAlias("gridWorld")
public class GridWorld {
	
	@XStreamImplicit
    private List<Grid> myGrids;
    
    @XStreamOmitField
    private int currentIndex;

    public GridWorld(GridManager gridManager) {
        myGrids = gridManager.getGrids();
        currentIndex = 0;
    }

    public List<Grid> getGrids() {
        return myGrids;
    }
}