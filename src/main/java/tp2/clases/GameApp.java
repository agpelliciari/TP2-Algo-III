package tp2.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.StartScreen;

import java.util.ArrayList;

public class GameApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Juego de preguntas y respuestas");

        MainContainer mainContainer = new MainContainer();

        Game game = buildModel();

        // TODO implement logic of mvc
        //StartScreen startScreen = new StartScreen(this::showNumberOfPlayersField);
        //mainContainer.addChild(startScreen);

        primaryStage.setScene(new Scene(mainContainer, 1000, 800));
        primaryStage.show();
    }

    private Game buildModel() {

        JsonParser parser = new JsonParser();

        ArrayList<Question> questions = parser.questionsParser("src/main/resources/preguntas.json");

        return new Game(questions);
    }
}
