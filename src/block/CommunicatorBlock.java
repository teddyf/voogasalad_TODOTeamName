package block;

import interactions.MessageInteraction;

/**
 * A board object with which the player character will have some active
 * interaction (i.e. pressing 'A' or stepping on a switch which elicits a
 * response.
 *
 * @author Filip Mazurek, Aninda Manocha
 */
public class CommunicatorBlock extends Block {

    private String myMessage;

	public CommunicatorBlock(String name, int row, int col) {
		super(name, row, col);
		setMessage("Waddup fuckers");
	}

	public void setMessage(String message) {
	    addTalkInteraction(new MessageInteraction(this.getRow(), this.getCol(), message));
	    myMessage = message;
    }
    public String getMessage(){
		return myMessage;
	}

}
