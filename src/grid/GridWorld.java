package grid;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages all of the grids in the game
 * @author Aninda Manocha
 */

public class GridWorld {
    private List<Grid> grids;
    private int currentIndex;

    public GridWorld() {
        grids = new ArrayList<>();
        currentIndex = 0;
    }

    public void addGrid(Grid grid) {
        grids.add(grid);

    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Grid getCurrentGrid() {
        return grids.get(currentIndex);
    }

    public void updateGrid() {
        currentIndex = grids.size() - 1;
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
    }
}