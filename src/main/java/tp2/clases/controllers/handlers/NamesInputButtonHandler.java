package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import tp2.clases.model.Game;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.view.screens.GroupChoiceScreen;
import tp2.clases.view.screens.QuestionScreen;
import tp2.clases.view.screens.PlayersNamesInputScreen;

public class NamesInputButtonHandler implements EventHandler<ActionEvent> {

    private PlayersNamesInputScreen namesInputScreen;
    private Game game;
    private StackPane stackPane;

    public NamesInputButtonHandler(Game game, StackPane stackPane, PlayersNamesInputScreen playersNamesInputScreen) {
        this.game = game;
        this.stackPane = stackPane;
        this.namesInputScreen = playersNamesInputScreen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ActionHandler.actionSound();

        game.registerUsers(namesInputScreen.getNames());
        int questionIndex = game.getRandomQuestionIndex();

        if (game.getQuestion(questionIndex) instanceof GroupChoice) {
            GroupChoiceScreen gameScreen = new GroupChoiceScreen(stackPane, game, 0, questionIndex);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(gameScreen);
        } else {
            QuestionScreen gameScreen = new QuestionScreen(stackPane, game, 0, questionIndex);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(gameScreen);
        }
    }
}
