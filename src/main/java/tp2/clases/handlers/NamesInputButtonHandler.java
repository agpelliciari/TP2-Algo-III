package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.Panel;
import tp2.clases.screens.PlayersNamesInputScreen;

import java.util.ArrayList;

public class NamesInputButtonHandler implements EventHandler<ActionEvent> {

    PlayersNamesInputScreen namesInputScreen;
    Game game;
    Scene nextScene;
    Stage stage;

    public NamesInputButtonHandler(Game game, Scene scene, Stage stage, PlayersNamesInputScreen playersNamesInputScreen) {
        this.game = game;
        this.nextScene = scene;
        this.stage = stage;
        this.namesInputScreen = playersNamesInputScreen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        game.registerUsers(namesInputScreen.getNames());

        int questionIndex = game.getRandomQuestionIndex();

        Panel gameScreen = new Panel(stage, new Scene(new MainContainer()), game, 0, questionIndex);
        Scene gameScene = new Scene(gameScreen, 800, 600);

        stage.setScene(gameScene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);
    }
}
