package api;

import interactions.Interaction;
import interactions.StepInteraction;
import interactions.TalkInteraction;

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

    public List<StepInteraction> getStepInteractions();

    public List<TalkInteraction> getTalkInteractions();

    public boolean addStepInteraction(StepInteraction stepInteraction);

    public boolean addTalkInteraction(TalkInteraction talkInteraction);
}
