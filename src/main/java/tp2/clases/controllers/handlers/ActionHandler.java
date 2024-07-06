package tp2.clases.controllers.handlers;

import javafx.scene.media.AudioClip;

import java.io.File;

import static tp2.clases.ConstantsPaths.BUTTON_PRESSED_SOUND_PATH;

public interface ActionHandler {

    static void actionSound() {
        String buttonPressedSound = new File(BUTTON_PRESSED_SOUND_PATH).toURI().toString();
        AudioClip audio = new AudioClip(buttonPressedSound);
        audio.play();
    }
}