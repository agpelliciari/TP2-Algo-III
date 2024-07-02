package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.PanelBuilder;
import tp2.clases.screens.PlayersInputScreen;

public class ContinueButtonEventHandler implements EventHandler<ActionEvent> {
    Game game;
    Scene nextScene;
    Stage stage;

    public ContinueButtonEventHandler(Game game, Scene gameScene, Stage primaryStage) {
        this.stage = primaryStage;
        this.game = game;
        this.nextScene = new Scene(new MainContainer(), 800, 600);

    }

    @Override
    public void handle(ActionEvent event) {
        stage.setScene(nextScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(false);
    }
//    private final ActionHandler continueHandler;
//
//    public ContinueButtonEventHandler(ActionHandler continueHandler) {
//        this.continueHandler = continueHandler;
//    }
//
//    @Override
//    public void handle(ActionEvent event) {
//        continueHandler.execute();
//    }


}