package grid;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * This class manages all of the grids in the editor or engine
 * @author Aninda Manocha, Daniel Chai
 */

@XStreamAlias("gridManager")
public class GridManager {
	
	@XStreamImplicit
    private List<Grid> grids;
    
    @XStreamOmitField
    private int currentIndex;

    private Grid currentGrid;

    public GridManager() {
        grids = new ArrayList<>();
        currentIndex = 0;
        currentGrid = grids.get(currentIndex);
    }

    public Grid addGrid(int numRows, int numCols) {
        Grid newGrid = new Grid(getNumGrids(), numRows, numCols);
        grids.add(newGrid);
        return changeGrid(getNumGrids()-1);
    }

    public void changeGrid(int index) {
        currentIndex = index;
        currentGrid = grids.get(currentIndex);
    }

    /***** GETTERS *****/

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getNumGrids() {
        return grids.size();
    }

    public Grid getGrid(int index) {
        return grids.get(index);
    }
}