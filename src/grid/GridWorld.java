package grid;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages all of the grids in the game
 * @author Aninda Manocha
 */

public class GridWorld {
    private List<Grid> grids;
    private int currentGrid;

    public GridWorld() {
        grids = new ArrayList<Grid>();
        currentGrid = 0;
    }

    public void addGrid(Grid grid) {
        grids.add(grid);
    }

    public Grid getCurrentGrid() {
        return grids.get(currentGrid);
    }

    public void setCurrentGrid() {
        currentGrid++;
    }
}
