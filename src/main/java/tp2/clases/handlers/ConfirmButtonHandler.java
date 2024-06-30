package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.PlayersInputScreen;

import java.util.function.Consumer;

public class ConfirmButtonHandler implements EventHandler<ActionEvent> {

    Game game;
    Scene nextScene;
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

    public ConfirmButtonHandler(Game game, PlayersInputScreen playersInputScreen, Scene namesInputScene, Stage primaryStage) {
        this.playersInputScreen = playersInputScreen;
        this.game = game;
        this.nextScene = namesInputScene;
        this.stage = primaryStage;
    }

    /*@Override
    public void handle(ActionEvent event) {
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPlayersTextField(), numberOfPlayersConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfQuestionsTextField(), numberOfQuestionsConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPointsTextField(), numberOfPointsConsumer, 1);
    }*/

    @Override
    public void handle(ActionEvent event) {
        game.setNumberOfPlayers(playersInputScreen.getNumberOfPlayersInput());

        game.setQuestionLimit(playersInputScreen.getQuestionLimitInput());

        game.setPointLimit(playersInputScreen.getPointLimitInput());

        stage.setScene(nextScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(true);
    }
}

