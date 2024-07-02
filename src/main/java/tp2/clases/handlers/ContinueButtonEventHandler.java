package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.*;

public class ContinueButtonEventHandler implements EventHandler<ActionEvent> {
    Game game;
    Scene nextScene;
    Stage stage;

    public ContinueButtonEventHandler(Game game, Stage primaryStage) {
        this.stage = primaryStage;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent event) {

        if (game.isFinished()) {

            EndGameScreen endScreen = new EndGameScreen(stage, game);
            Scene endScene = new Scene(endScreen, 800, 600);

            stage.setScene(endScene);

        } else {

            game.deactivatePowers();

            int questionIndex = game.getRandomQuestionIndex();
            Panel gameScreen = new Panel(stage, game, 0, questionIndex);
            Scene gameScene = new Scene(gameScreen, 800, 600);

            stage.setScene(gameScene);
        }

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);
    }
}