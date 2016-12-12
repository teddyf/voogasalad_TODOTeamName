package grid;

import java.util.ArrayList;
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
    private List<Grid> grids;
    
    @XStreamOmitField
    private int currentIndex;
    
    

    public GridWorld() {
        grids = new ArrayList<>();
        currentIndex = 0;
    }

    public Grid addGrid(int numRows, int numCols) {
        Grid newGrid = new Grid(getNumGrids(), numRows, numCols);
        grids.add(newGrid);
        return changeGrid(getNumGrids()-1);
    }

    public Grid changeGrid(int index) {
        currentIndex = index;
        return grids.get(currentIndex);
    }

    public Grid getCurrentGrid() {
        return grids.get(currentIndex);
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