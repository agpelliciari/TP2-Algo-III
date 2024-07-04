package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import tp2.clases.model.Game;
import tp2.clases.view.screens.PlayersInputScreen;
import tp2.clases.view.screens.PlayersNamesInputScreen;

import java.util.function.Consumer;

public class ConfirmButtonHandler implements EventHandler<ActionEvent> {

    private Game game;
    private StackPane stackPane;
    private PlayersInputScreen playersInputScreen;
    private Consumer<Integer> numberOfPlayersConsumer;
    private Consumer<Integer> numberOfQuestionsConsumer;
    private Consumer<Integer> numberOfPointsConsumer;

    public ConfirmButtonHandler(PlayersInputScreen playersInputScreen, Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        this.playersInputScreen = playersInputScreen;
        this.numberOfPlayersConsumer = numberOfPlayersConsumer;
        this.numberOfQuestionsConsumer = numberOfQuestionsConsumer;
        this.numberOfPointsConsumer = numberOfPointsConsumer;
    }

    public ConfirmButtonHandler(Game game, PlayersInputScreen playersInputScreen, StackPane stackPane) {
        this.playersInputScreen = playersInputScreen;
        this.stackPane = stackPane;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();

        game.setNumberOfPlayers(playersInputScreen.getNumberOfPlayersInput());
        game.setQuestionLimit(playersInputScreen.getQuestionLimitInput());
        game.setPointLimit(playersInputScreen.getPointLimitInput());

        PlayersNamesInputScreen namesInputScreen = new PlayersNamesInputScreen(stackPane, game);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(namesInputScreen);

        Stage stage = (Stage) stackPane.getScene().getWindow();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(false);
    }
}