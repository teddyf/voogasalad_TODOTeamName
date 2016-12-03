package api;

import interactions.Interaction;

import java.util.Collections;
import java.util.List;

/**
 * The block interface
 * @author Aninda Manocha
 */

public interface IBlock {
    public String getName();

    public int getRow();

    public int getCol();

    public boolean isWalkable();

    public List<Interaction> getInteractions();

    public boolean addInteraction(Interaction someInteraction);
}
