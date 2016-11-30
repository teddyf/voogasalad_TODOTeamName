package interactions;

import block.CommunicatorBlock;

/**
 * When interacted with by the player (e.g. pressing 'A' interaction button), give a message
 * which will be displayed to the user.
 *
 * @author Filip Mazurek
 */
public class GiveMessageOnInteract extends AbstractInteraction {
    private CommunicatorBlock communicator;
    public GiveMessageOnInteract(CommunicatorBlock block){
        communicator = block;
    }
    @Override
    public void actOnStep(){
        //like an obstacle
        //getCurrentAction().act();
    }
    public void actOnTalk(String message){
        //add all the code to add message and stuff here

    }
}
