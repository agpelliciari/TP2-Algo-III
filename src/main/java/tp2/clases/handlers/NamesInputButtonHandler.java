package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.Panel;
import tp2.clases.screens.PlayersNamesInputScreen;

import java.io.File;
import java.util.ArrayList;

public class NamesInputButtonHandler implements EventHandler<ActionEvent> {

    PlayersNamesInputScreen namesInputScreen;
    Game game;
    Stage stage;

    public NamesInputButtonHandler(Game game, Stage stage, PlayersNamesInputScreen playersNamesInputScreen) {
        this.game = game;
        this.stage = stage;
        this.namesInputScreen = playersNamesInputScreen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String buttonPressedSound = new File("src/main/resources/sounds/button-pressed.mp3").toURI().toString();
        AudioClip audio = new AudioClip(buttonPressedSound);
        audio.play();

        game.registerUsers(namesInputScreen.getNames());

        int questionIndex = game.getRandomQuestionIndex();

        Panel gameScreen = new Panel(stage, game, 0, questionIndex);
        Scene gameScene = new Scene(gameScreen, 800, 600);

        stage.setScene(gameScene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);
    }
}
