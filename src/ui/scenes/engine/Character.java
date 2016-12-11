package ui.scenes.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * This class handles the visualization of the Player
 * @author Pim Chuaylua, Harshil Garg
 *
 */

public class Character {
	
	private ImageView myImageView;

	private List<String> myImagePaths;
	
	public Character(List<String> imagePaths, String defaultPath) {
		myImagePaths = imagePaths;
		myImageView = new ImageView(defaultPath);
	}

	public void setImage(String direction) {
		for (String path : myImagePaths) {
			if (path.contains(direction.toLowerCase())) {
				myImageView.setImage(new Image(path));
				break;
			}
		}
	}

	public void setCharacterImageSize(double size) {
		myImageView.setFitHeight(size);
		myImageView.setFitWidth(size);
	}

	public ImageView getImageView() {
		return myImageView;
	}
	
	public void setPosX(double posX) {
		myImageView.setLayoutX(posX);
	}
	
	public void setPosY(double posY) {
		myImageView.setLayoutY(posY);
	}

	public void setSize(double size) {
		myImageView.setFitWidth(size);
		myImageView.setFitHeight(size);
	}

	public double getSize() {
		return myImageView.getFitWidth();
	}
}
