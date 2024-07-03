package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    String buttonPressedSound = new File("src/main/resources/sounds/button-pressed.mp3").toURI().toString();
    AudioClip audio = new AudioClip(buttonPressedSound);

    private final Scene nextScene;
    private final MediaPlayer mediaPlayer;

    public StartButtonEventHandler(Scene nextScene, MediaPlayer mediaPlayer) {
        this.nextScene = nextScene;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();
        mediaPlayer.setVolume(0.5);

        if (nextScene != null) {
            nextScene.getWindow().sizeToScene();
            nextScene.getWindow().centerOnScreen();
            nextScene.getWindow().requestFocus();
        }
    }
}