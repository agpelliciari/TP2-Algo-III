package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.Panel;

public class AnswerButtonHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Scene nextScene;
    Game game;
    Panel panel;
    int lastPlayerIndex;
    int questionIndex;


    public AnswerButtonHandler(Stage primaryStage, Scene scene, Game game, int playerIndex, int questionIndex, Panel lastPanel) {
        this.stage = primaryStage;
        this.nextScene = scene;
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
            stage.setScene(nextScene);
        }
        else {
            //Mostrar la misma pregunta para el jugador de indice +1
            Panel gameScreen = new Panel(stage, new Scene(new MainContainer()), game, lastPlayerIndex, questionIndex);
            Scene gameScene = new Scene(gameScreen);
            stage.setScene(gameScene);
        }

        stage.setFullScreenExitHint("");

        stage.setFullScreen(true);

    }
}
