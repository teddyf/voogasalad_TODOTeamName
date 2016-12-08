package player;

import api.IPlayerAttribute;

/**
 * This class contains the information for an attribute of a player.
 * @author Aninda Manocha
 */

public class PlayerAttribute implements IPlayerAttribute {
    private String myName;
    private double myAmount;

    public PlayerAttribute(String name, double amount) {
        myName = name;
        myAmount = amount;
    }

    public void increase(int change) {
        myAmount += change;
    }

    public void decrease(int change) {
        myAmount -= change;
    }

    public String getName() {
        return myName;
    }

    public double getAmount() {
        return myAmount;
    }
}
