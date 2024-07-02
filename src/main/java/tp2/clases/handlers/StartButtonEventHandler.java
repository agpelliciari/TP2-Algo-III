package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    //private final ActionHandler startHandler;

    Stage stage;
    Scene nextScene;
    private MediaPlayer mediaPlayer;

    /*public StartButtonEventHandler(ActionHandler startHandler) {
        this.startHandler = startHandler;
    }

    @Override
    public void handle(ActionEvent event) {
        startHandler.execute();
    }*/

    public StartButtonEventHandler(Stage primaryStage, Scene playerInputScene, MediaPlayer mediaPlayer) {

        this.stage = primaryStage;
        this.nextScene = playerInputScene;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void handle(ActionEvent event) {
        mediaPlayer.setVolume(0.5);

        stage.setScene(nextScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(true);
    }
}
