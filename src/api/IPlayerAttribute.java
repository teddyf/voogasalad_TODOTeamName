package api;

/**
 * The interface for the player attribute class.
 * @author Aninda Manocha
 */

public interface IPlayerAttribute {

    /**
     * Increases the amount of the attribute
     */
    public void increase();

    /**
     * Decreases the amount of the attribute
     */
    public void decrease();

    /**
     * Gets the name/type (i.e. "health") of the attribute
     * @return the name/type
     */
    public String getName();

    /**
     * Gets the amount of the attribute
     * @return the amount
     */
    public double getAmount();

    /**
     * Gets the increment by which the amount is increased by (whenever the amount of the attribute is increased, it is
     * increased by this increment amount)
     * @return the increment
     */
    public double getIncrement();

    /**
     * Gets the decrement by which the amount is decreased by (whenever the amount of the attribute is decreased, it is
     * decreased by this decrement amount)
     * @return the decrement
     */
    public double getDecrement();
}
