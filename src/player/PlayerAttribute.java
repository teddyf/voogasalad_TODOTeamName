package player;

import api.IPlayerAttribute;

/**
 * This class contains the information for an attribute of a player.
 * @author Aninda Manocha
 */

public class PlayerAttribute implements IPlayerAttribute {
    private String myName;
    private double myAmount;
    private double myIncrement;
    private double myDecrement;

    public PlayerAttribute(String name, double amount, double increment) {
        myName = name;
        myAmount = amount;
        myIncrement = increment;
    }

    public void increase() {
        myAmount += myIncrement;
    }

    public void decrease() {
        myAmount -= myDecrement;
    }

    public String getName() {
        return myName;
    }

    public double getAmount() {
        return myAmount;
    }

    public double getIncrement() {
        return myIncrement;
    }

    public double getDecrement() {
        return myDecrement;
    }
}
