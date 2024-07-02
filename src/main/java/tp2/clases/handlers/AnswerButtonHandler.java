package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.AnswerScreen;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.Panel;

public class AnswerButtonHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Game game;
    Panel panel;
    int lastPlayerIndex;
    int questionIndex;


    public AnswerButtonHandler(Stage primaryStage, Game game, int playerIndex, int questionIndex, Panel lastPanel) {
        this.stage = primaryStage;
        this.game = game;
        this.panel = lastPanel;
        this.lastPlayerIndex = playerIndex;
        this.questionIndex = questionIndex;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        MultiplicatorButtonHandler multiplicatorButtonHandler = new MultiplicatorButtonHandler(panel.getFactor());
        multiplicatorButtonHandler.selectMultiplier(game.getPlayer(lastPlayerIndex), panel.isMultiplicatorSelected());

        NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
        nullifierHandler.selectNullifier(game.getPlayer(lastPlayerIndex), game.getPlayers(), panel.isNullifierSelected());

        game.setPlayerRoundScore(lastPlayerIndex, questionIndex, panel.getSelectedAnswers());

        //Logica de exclusividad se deberia parecer a los otros poderes y manejarse con handlers
        if (panel.isExclusivitySelected()) {

            game.registerUsedExclusivity();

            game.getPlayer(lastPlayerIndex).getExclusivity().decreaseNumber();
        }

        lastPlayerIndex++;

        if (lastPlayerIndex >= game.getNumberOfPlayers()) {

            if (game.getQuestion(questionIndex).getMode().isPenaltyMode()) {

                game.updateRoundScores();

            } else {

                game.updatePlayersScoreWithExclusivity();

            }
            //Mostrar escena que muestra las respuestas
            AnswerScreen answerScreen = new AnswerScreen(stage, game);
            Scene answerScene = new Scene(answerScreen, 800,600);
            stage.setScene(answerScene);
            stage.setFullScreen(false);
        }
        else {
            //Mostrar la misma pregunta para el jugador de indice +1
            Panel gameScreen = new Panel(stage, game, lastPlayerIndex, questionIndex);
            Scene gameScene = new Scene(gameScreen, 800,600);
            stage.setScene(gameScene);
            stage.setFullScreen(false);
        }

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);

    }
}
