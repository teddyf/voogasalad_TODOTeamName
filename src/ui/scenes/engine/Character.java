package ui.scenes.engine;

import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class handles the visualization of the Player
 * @author pim
 *
 */

public class Character extends Observable {
	
	private ImageView characterImage;
	private int curRow;
	private int curCol;
	private double posX;
	private double posY;
	private double size;
	private String name;
	
	public Character(GameEngine gameEngine) {
		this.addObserver(gameEngine);
	}

	/*setting size of character image
	 * */
	public void setCharacterImageSize(double size) {
		this.size = size;
		characterImage.setFitHeight(size);
		characterImage.setFitWidth(size); 
	}
	
	/*setting character image
	 * */
	public void setCharacterImage(String path) {
		characterImage = new ImageView(new Image(path));
	}
	
	public void changeCharacterImage(String path) {
		this.setCharacterImage(path);
		this.setCharacterImageSize(size);
		this.getCharacterImageView().setLayoutX(posX);
    	this.getCharacterImageView().setLayoutY(posY);
    	this.setName(path);
    	setChanged();
    	notifyObservers();
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
	
	public void setPosX(double posX) {
		getCharacterImageView().setLayoutX(posX);
		this.posX=posX;
	}
	
	public void setPosY(double posY) {
		getCharacterImageView().setLayoutY(posY);
		this.posY=posY;
	}

	public double getSize() {
		return size;
	}
	
	public int getRowCharacter() {
		return curRow;
	}
	
	public int getColumnCharacter() {
		return curCol;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
}
