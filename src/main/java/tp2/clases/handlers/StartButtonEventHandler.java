package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    //private final ActionHandler startHandler;

    Stage stage;
    Scene nextScene;

    /*public StartButtonEventHandler(ActionHandler startHandler) {
        this.startHandler = startHandler;
    }

    @Override
    public void handle(ActionEvent event) {
        startHandler.execute();
    }*/

    public StartButtonEventHandler(Stage primaryStage, Scene playerInputScene) {

        this.stage = primaryStage;
        this.nextScene = playerInputScene;
    }

    @Override
    public void handle(ActionEvent event) {
        stage.setScene(nextScene);

        stage.setFullScreenExitHint("");

        stage.setFullScreen(true);
    }
}
