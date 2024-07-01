package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.PlayersInputScreen;
import tp2.clases.screens.PlayersNamesInputScreen;

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

    public ConfirmButtonHandler(Game game, PlayersInputScreen playersInputScreen, Scene gameScene, Stage primaryStage) {
        this.playersInputScreen = playersInputScreen;
        this.stage = primaryStage;
        this.game = game;
        this.nextScene = new Scene(new MainContainer()); //Tiene que ser la escena del flujo del juego principal
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

        PlayersNamesInputScreen namesInputScreen = new PlayersNamesInputScreen(stage, nextScene, game);
        Scene namesInputScene = new Scene(namesInputScreen);

        stage.setScene(namesInputScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(true);
    }
}

