package ui.media;
import java.io.File;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class SoundControl {
	private MediaPlayer player;
	private HashMap<String,String> playlist;
	private Group group;
	private ComboBox comboBox;
	private HBox hbox;
	private boolean playButtonClicked;
	
	public SoundControl() {
		init();
	}
	
	private void init() {
		group = new Group();
		playlist = new HashMap<String,String>();
		hbox = new HBox(10);
		comboBox = new ComboBox();
		comboBox.setPromptText("Choose song");
		hbox.getChildren().add(comboBox);
		addSong("Aquacorde","src/resources/songs/aquacorde.mp3");
		addSong("Fallarbor","src/resources/songs/fallarbor.mp3");
		addSong("Pallettown","src/resources/songs/pallettown.mp3");
		initPlayButton();
		initPauseButton();
		
		player = new MediaPlayer(new Media(new File("src/resources/songs/aquacorde.mp3").toURI().toString()));
		setPlayinLoop(player);
		player.play();
        playButtonClicked=true;
        
		group.getChildren().add(hbox);
	}
	
	public void addSong(String songName, String filePath) {
		playlist.put(songName,filePath);
		comboBox.getItems().add(songName);
	}
	
	public Group getGroup() {
		return group;
	}
	
	private void initPlayButton() {
		Button button = new Button();
		setButtonImage(button,"resources/images/media/play.png");
		button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (playButtonClicked==true) {
            		player.stop();
            	}
                String chosenSong = (String) comboBox.getValue();
                playSong(chosenSong);
            }
        });
		hbox.getChildren().add(button);	
	}
	
	public void playSong(String songName){
		if (playButtonClicked==true) {
    		player.stop();
    	}
        String filePath = playlist.get(songName);
        player = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
        setPlayinLoop(player);
        player.play();
        playButtonClicked=true;
	}
	
	private void initPauseButton() {
		Button button = new Button();
        setButtonImage(button,"resources/images/media/pause.png");
		button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.stop();
            }
        });
		hbox.getChildren().add(button);
	}
	
	public void addNodeToControl(Node node) {
		hbox.getChildren().add(node);
	}
	
	private void setButtonImage(Button button, String imageFilePath) {
		Image image = new Image(imageFilePath);
        ImageView itemView = new ImageView();
        itemView.setImage(image);
        itemView.setFitWidth(25);
        itemView.setFitHeight(25);
        button.setGraphic(itemView);
	}
	
	private void setPlayinLoop(MediaPlayer mediaPlayer) {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   mediaPlayer.seek(Duration.ZERO);
		       }
		   });
	}
}
