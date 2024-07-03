package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import tp2.clases.Game;
import tp2.clases.screens.Panel;
import tp2.clases.screens.PlayersNamesInputScreen;

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

        Panel gameScreen = new Panel(stackPane, game, 0, questionIndex);

        stackPane.getChildren().clear();
        stackPane.getChildren().add(gameScreen);
    }
}
