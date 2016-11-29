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

    /**
     * Increases the amount of the attribute
     */
    public void increase() {
        myAmount += myIncrement;
    }

    /**
     * Decreases the amount of the attribute
     */
    public void decrease() {
        myAmount -= myDecrement;
    }

    /**
     * Gets the name/type (i.e. "health") of the attribute
     * @return the name/type
     */
    public String getName() {
        return myName;
    }

    /**
     * Gets the amount of the attribute
     * @return the amount
     */
    public double getAmount() {
        return myAmount;
    }

    /**
     * Gets the increment by which the amount is increased by (whenever the amount of the attribute is increased, it is
     * increased by this increment amount)
     * @return the increment
     */
    public double getIncrement() {
        return myIncrement;
    }

    /**
     * Gets the decrement by which the amount is decreased by (whenever the amount of the attribute is decreased, it is
     * decreased by this decrement amount)
     * @return the decrement
     */
    public double getDecrement() {
        return myDecrement;
    }
}
