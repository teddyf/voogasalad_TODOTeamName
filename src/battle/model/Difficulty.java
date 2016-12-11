package battle.model;

/**
 * Enum for battle difficulty values.
 * 
 * @author Daniel Chai
 */
public enum Difficulty {
	EASY (10), 
	MEDIUM (25),
	HARD (40);
	
	private int value;
	
	private Difficulty(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
