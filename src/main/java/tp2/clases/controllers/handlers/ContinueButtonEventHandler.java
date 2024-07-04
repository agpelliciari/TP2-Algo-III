package tp2.clases.controllers.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import tp2.clases.model.Game;
import tp2.clases.view.screens.EndGameScreen;
import tp2.clases.view.screens.Panel;

public class ContinueButtonEventHandler implements EventHandler<ActionEvent> {

    private Game game;
    private StackPane root;

    public ContinueButtonEventHandler(Game game, StackPane root) {
        this.game = game;
        this.root = root;
    }

    @Override
    public void handle(ActionEvent event) {
        ActionHandler.actionSound();

        if (game.isFinished()) {
            VBox endScreen = new EndGameScreen(root, game);
            root.getChildren().clear();
            root.getChildren().add(endScreen);
        } else {
            game.deactivatePowers();
            int questionIndex = game.getRandomQuestionIndex();
            ScrollPane gameScreen = new Panel(root, game, 0, questionIndex);
            root.getChildren().clear();
            root.getChildren().add(gameScreen);
        }
    }
}