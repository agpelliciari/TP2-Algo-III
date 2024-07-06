package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static tp2.clases.ConstantsPaths.BUTTON_PRESSED_SOUND_PATH;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    String buttonPressedSound = new File(BUTTON_PRESSED_SOUND_PATH).toURI().toString();
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