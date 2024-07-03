package tp2.clases.handlers;

import javafx.scene.media.AudioClip;

import java.io.File;

public interface ActionHandler {

    static void actionSound() {
        String buttonPressedSound = new File("src/main/resources/sounds/button-pressed.mp3").toURI().toString();
        AudioClip audio = new AudioClip(buttonPressedSound);
        audio.play();
    }
}