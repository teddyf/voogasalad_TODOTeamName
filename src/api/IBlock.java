package api;

import interactions.Interaction;

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

    public List<Interaction> getStepInteractions();

    public List<Interaction> getTalkInteractions();

    public boolean addStepInteraction(Interaction stepInteraction);

    public boolean addTalkInteraction(Interaction talkInteraction);
}
