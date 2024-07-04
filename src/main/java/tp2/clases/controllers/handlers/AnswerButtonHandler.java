package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import tp2.clases.model.Game;
import tp2.clases.view.screens.AnswerScreen;
import tp2.clases.view.screens.Panel;

public class AnswerButtonHandler implements EventHandler<ActionEvent> {

    private StackPane root;
    private Game game;
    private Panel panel;
    private int playerIndex;
    private int questionIndex;

    public AnswerButtonHandler(StackPane root, Game game, int playerIndex, int questionIndex, Panel lastPanel) {
        this.root = root;
        this.game = game;
        this.panel = lastPanel;
        this.playerIndex = playerIndex;
        this.questionIndex = questionIndex;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ActionHandler.actionSound();

        for (int i = 0; i < panel.getMultipliersFactor(game.getPlayer(playerIndex)).size(); i++) {
            MultiplierButtonHandler multiplicatorButtonHandler = new MultiplierButtonHandler(panel.getMultipliersFactor(game.getPlayer(playerIndex)).get(i));
            multiplicatorButtonHandler.selectMultiplier(game.getPlayer(playerIndex), panel.isMultiplierSelected(i));
        }

        NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
        nullifierHandler.selectNullifier(game.getPlayer(playerIndex), game.getPlayers(), panel.isNullifierSelected());

        game.setPlayersScoreWithoutExclusivity(playerIndex, questionIndex, panel.getSelectedAnswers());
        game.registerUsedExclusivity(panel.isExclusivitySelected());
        game.assignExclusivity(playerIndex, panel.isExclusivitySelected());

        playerIndex++;
        if (playerIndex >= game.getNumberOfPlayers()) {
            if (game.getQuestion(questionIndex).getMode().isPenaltyMode()) {
                game.updatePlayersScoreWithoutExclusivity();
            } else {
                game.updatePlayersScoreWithExclusivity();
            }

            game.resetExclusivityCount();
            AnswerScreen answerScreen = new AnswerScreen(root, game);
            root.getChildren().forEach(node -> node.setVisible(false));
            answerScreen.setVisible(true);
            root.getChildren().add(answerScreen);
        } else {
            Panel gameScreen = new Panel(root, game, playerIndex, questionIndex);
            root.getChildren().forEach(node -> node.setVisible(false));
            gameScreen.setVisible(true);
            root.getChildren().add(gameScreen);
        }
    }
}