package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.PlayersInputScreen;
import tp2.clases.screens.PlayersNamesInputScreen;

import java.io.File;
import java.util.function.Consumer;

public class ConfirmButtonHandler implements EventHandler<ActionEvent> {

    Game game;
    Stage stage;

    PlayersInputScreen playersInputScreen;

    private Consumer<Integer> numberOfPlayersConsumer;
    private Consumer<Integer> numberOfQuestionsConsumer;
    private Consumer<Integer> numberOfPointsConsumer;

    public ConfirmButtonHandler(PlayersInputScreen playersInputScreen, Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        this.playersInputScreen = playersInputScreen;
        this.numberOfPlayersConsumer = numberOfPlayersConsumer;
        this.numberOfQuestionsConsumer = numberOfQuestionsConsumer;
        this.numberOfPointsConsumer = numberOfPointsConsumer;
    }

    public ConfirmButtonHandler(Game game, PlayersInputScreen playersInputScreen, Stage primaryStage) {
        this.playersInputScreen = playersInputScreen;
        this.stage = primaryStage;
        this.game = game;
    }

    /*@Override
    public void handle(ActionEvent event) {
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPlayersTextField(), numberOfPlayersConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfQuestionsTextField(), numberOfQuestionsConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPointsTextField(), numberOfPointsConsumer, 1);
    }*/

    @Override
    public void handle(ActionEvent event) {
        String buttonPressedSound = new File("src/main/resources/sounds/button-pressed.mp3").toURI().toString();
        AudioClip audio = new AudioClip(buttonPressedSound);
        audio.play();

        game.setNumberOfPlayers(playersInputScreen.getNumberOfPlayersInput());

        game.setQuestionLimit(playersInputScreen.getQuestionLimitInput());

        game.setPointLimit(playersInputScreen.getPointLimitInput());

        PlayersNamesInputScreen namesInputScreen = new PlayersNamesInputScreen(stage, game);
        Scene namesInputScene = new Scene(namesInputScreen, 800, 600);

        stage.setScene(namesInputScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);
    }
}

