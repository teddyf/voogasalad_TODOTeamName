package ui.scenes;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * This class handles the visualization of the Player
 * @author pim
 *
 */

public class Character {
	
	private ImageView characterImage;
	private int curRow;
	private int curCol;
	
	public static final int BLOCK_SIZE = 50;
	public static final int INITIAL_ROW = 0;
	public static final int INITIAL_COL = 0;
	public static final int INITIAL_X = 0;
	public static final int INITIAL_Y = 0;
	private final String PATH = "resources/images/Sprites/Character/Pokemon/default.png";
	Character(GameEngine gameEngine) {
		buildCharacter();
		gameEngine.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}
	 
	private void buildCharacter() {
		setCharacterImage(PATH);
		setCharacterImageSize(BLOCK_SIZE);
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
	
	public int getRowCharacter() {
		return curRow;
	}
	
	public int getColumnCharacter() {
		return curCol;
	}
	
	/*handle position of character by arrow key
	 * */
	private void handleKeyInput(KeyCode code) {

        switch (code) {
        case U:
        	curRow--;
        	characterImage.setY(characterImage.getY() - BLOCK_SIZE);
            break;
        case J:
        	curRow++;
        	characterImage.setY(characterImage.getY() + BLOCK_SIZE);
            break;  
        case K:
        	curCol++;
        	characterImage.setX(characterImage.getX() + BLOCK_SIZE);
            break; 
        case H:
        	curCol--;
        	characterImage.setX(characterImage.getX() - BLOCK_SIZE);
            break; 
        case Y:
        	curCol--;
        	curRow--;
        	characterImage.setX(characterImage.getX() - BLOCK_SIZE);
        	characterImage.setY(characterImage.getY() - BLOCK_SIZE);
            break; 
        case I:
        	curCol++;
        	curRow--;
        	characterImage.setX(characterImage.getX() + BLOCK_SIZE);
        	characterImage.setY(characterImage.getY() - BLOCK_SIZE);
            break;
        case N:
        	curCol--;
        	curRow++;
        	characterImage.setX(characterImage.getX() -BLOCK_SIZE );
        	characterImage.setY(characterImage.getY() + BLOCK_SIZE);
            break;
        case M:
        	curCol++;
        	curRow++;
        	characterImage.setX(characterImage.getX() + BLOCK_SIZE);
        	characterImage.setY(characterImage.getY() + BLOCK_SIZE);
            break;
        default:
        }
        System.out.println("cur row: "+curRow);
        System.out.println("cur col: "+curCol);
        
        //TODO if curRow and curCol is obstacle -> do not change, if hits switch -> changes that gate
    }
}
