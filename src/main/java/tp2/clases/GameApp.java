package tp2.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.*;

import java.util.ArrayList;

public class GameApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Juego de preguntas y respuestas");

        Game game = buildModel();

        PlayersInputScreen inputsScreen = new PlayersInputScreen(primaryStage, new Scene(new MainContainer()), game);
        Scene playerInputScene = new Scene(inputsScreen, 800, 600);

        StartScreen startScreen = new StartScreen(primaryStage, playerInputScene);
        Scene startScene = new Scene(startScreen, 800, 600);
        startScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setScene(startScene);

        primaryStage.setFullScreen(false);

        primaryStage.show();
    }

    private Game buildModel() {

        JsonParser parser = new JsonParser();

        ArrayList<Question> questions = parser.questionsParser("src/main/resources/preguntas.json");

        return new Game(questions);
    }
}
