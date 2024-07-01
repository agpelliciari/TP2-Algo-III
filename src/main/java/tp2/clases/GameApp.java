package tp2.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.PlayersInputScreen;
import tp2.clases.screens.PlayersNamesInputScreen;
import tp2.clases.screens.StartScreen;

import java.util.ArrayList;

public class GameApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Juego de preguntas y respuestas");

        Game game = buildModel();

        // TODO implement logic of mvc

        //EndGameScreen endGameScreen = new EndGameScreen();
        //Scene endGameScene = new Scene(endGameScreen);

        //GameScreen gameScreen = new GameScreen(primaryStage, endGameScene, game);
        //Scene gameScene = new Scene(gameScreen);

        PlayersInputScreen inputsScreen = new PlayersInputScreen(primaryStage, new Scene(new MainContainer()), game);
        Scene playerInputScene = new Scene(inputsScreen);

        StartScreen startScreen = new StartScreen(primaryStage, playerInputScene);
        Scene startScene = new Scene(startScreen);
        startScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setScene(startScene);

        primaryStage.setFullScreen(true);

        primaryStage.show();
    }

    private Game buildModel() {

        JsonParser parser = new JsonParser();

        ArrayList<Question> questions = parser.questionsParser("src/main/resources/preguntas.json");

        return new Game(questions);
    }
}
