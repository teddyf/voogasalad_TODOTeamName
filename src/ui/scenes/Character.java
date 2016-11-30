package ui.scenes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class handles the visualization of the Player
 * @author pim
 *
 */

public class Character {
	
	private ImageView characterImage;
	private int curRow;
	private int curCol;

	private final String PATH = "resources/images/Sprites/Character/Pokemon/default.png";
	
	public Character() {
		buildCharacter();
	}
	 
	private void buildCharacter() {
		setCharacterImage(PATH);
	}
	
	/*setting size of character image
	 * */
	public void setCharacterImageSize(int blockSize) {
		characterImage.setFitHeight(blockSize);
		characterImage.setFitWidth(blockSize); 
	}
	
	/*setting character image
	 * */
	public void setCharacterImage(String path) {
		characterImage = new ImageView(new Image(path));
	}
	
	public ImageView getCharacterImageView() {
		return characterImage;
	}
	
	public void setColumn(int column) {
		curCol = column;
	}
	
	public void setRow(int row) {
		curRow = row;
	}
	
	public int getRowCharacter() {
		return curRow;
	}
	
	public int getColumnCharacter() {
		return curCol;
	}
}
