package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import tp2.clases.model.Game;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.view.screens.EndGameScreen;
import tp2.clases.view.screens.GroupChoiceScreen;
import tp2.clases.view.screens.QuestionScreen;

public class ContinueButtonEventHandler implements EventHandler<ActionEvent> {

    private Game game;
    private StackPane stackPane;

    public ContinueButtonEventHandler(Game game, StackPane stackPane) {
        this.game = game;
        this.stackPane = stackPane;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();

        if (game.isFinished()) {
            VBox endScreen = new EndGameScreen(stackPane, game);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(endScreen);
        } else {
            game.deactivatePowers();
            int questionIndex = game.getRandomQuestionIndex();

            if (game.getQuestion(questionIndex) instanceof GroupChoice) {
                GroupChoiceScreen gameScreen = new GroupChoiceScreen(stackPane, game, 0, questionIndex);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(gameScreen);
            }
            else {
                QuestionScreen gameScreen = new QuestionScreen(stackPane, game, 0, questionIndex);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(gameScreen);
            }
        }
    }
}