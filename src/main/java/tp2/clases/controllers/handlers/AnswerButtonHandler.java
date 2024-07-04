package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import tp2.clases.model.Game;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.view.screens.AnswerScreen;
import tp2.clases.view.screens.GroupChoiceScreen;
import tp2.clases.view.screens.QuestionScreen;

public class AnswerButtonHandler implements EventHandler<ActionEvent> {

    private StackPane stackPane;
    private Game game;
    private QuestionScreen questionScreen;
    private GroupChoiceScreen groupChoiceScreen;
    private int playerIndex, questionIndex;

    public AnswerButtonHandler(StackPane stackPane, Game game, int playerIndex, int questionIndex, QuestionScreen lastQuestionScreen) {
        this.stackPane = stackPane;
        this.game = game;
        this.questionScreen = lastQuestionScreen;
        this.playerIndex = playerIndex;
        this.questionIndex = questionIndex;
    }

    public AnswerButtonHandler(StackPane stackPane, Game game, int playerIndex, int questionIndex, GroupChoiceScreen lastGroupChoiceScreen) {
        this.stackPane = stackPane;
        this.game = game;
        this.groupChoiceScreen = lastGroupChoiceScreen;
        this.playerIndex = playerIndex;
        this.questionIndex = questionIndex;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ActionHandler.actionSound();
        if (game.getQuestion(questionIndex) instanceof GroupChoice groupChoice) {

            NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
            nullifierHandler.selectNullifier(game.getPlayer(playerIndex), game.getPlayers(), groupChoiceScreen.isNullifierSelected());

            game.setPlayersScoreWithoutExclusivity(playerIndex, groupChoice.calculateTotalScore(groupChoiceScreen.getSelectedAnswers()));
            game.registerUsedExclusivity(groupChoiceScreen.isExclusivitySelected());
            game.assignExclusivity(playerIndex, groupChoiceScreen.isExclusivitySelected());

            playerIndex++;
            if (playerIndex >= game.getNumberOfPlayers()) {
                game.updatePlayersScoreWithExclusivity();
                game.resetExclusivityCount();

                AnswerScreen answerScreen = new AnswerScreen(stackPane, game);
                stackPane.getChildren().forEach(node -> node.setVisible(false));
                answerScreen.setVisible(true);
                stackPane.getChildren().add(answerScreen);
            } else {
                GroupChoiceScreen gameScreen = new GroupChoiceScreen(stackPane, game, playerIndex, questionIndex);
                stackPane.getChildren().forEach(node -> node.setVisible(false));
                gameScreen.setVisible(true);
                stackPane.getChildren().add(gameScreen);
            }
        } else {
            for (int i = 0; i < questionScreen.getMultipliersFactor(game.getPlayer(playerIndex)).size(); i++) {
                MultiplierButtonHandler multiplierButtonHandler = new MultiplierButtonHandler(questionScreen.getMultipliersFactor(game.getPlayer(playerIndex)).get(i));
                multiplierButtonHandler.selectMultiplier(game.getPlayer(playerIndex), questionScreen.isMultiplierSelected(i));
            }

            NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
            nullifierHandler.selectNullifier(game.getPlayer(playerIndex), game.getPlayers(), questionScreen.isNullifierSelected());

            game.setPlayersScoreWithoutExclusivity(playerIndex, questionIndex, questionScreen.getSelectedAnswers());
            game.registerUsedExclusivity(questionScreen.isExclusivitySelected());
            game.assignExclusivity(playerIndex, questionScreen.isExclusivitySelected());

            playerIndex++;
            if (playerIndex >= game.getNumberOfPlayers()) {
                if (game.getQuestion(questionIndex).getMode().isPenaltyMode()) {
                    game.updatePlayersScoreWithoutExclusivity();
                } else {
                    game.updatePlayersScoreWithExclusivity();
                }

                game.resetExclusivityCount();
                AnswerScreen answerScreen = new AnswerScreen(stackPane, game);
                stackPane.getChildren().forEach(node -> node.setVisible(false));
                answerScreen.setVisible(true);
                stackPane.getChildren().add(answerScreen);
            } else {
                QuestionScreen gameScreen = new QuestionScreen(stackPane, game, playerIndex, questionIndex);
                stackPane.getChildren().forEach(node -> node.setVisible(false));
                gameScreen.setVisible(true);
                stackPane.getChildren().add(gameScreen);
            }
        }
    }
}