package tp2.clases.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.kordamp.bootstrapfx.BootstrapFX;
import tp2.clases.model.Game;
import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.types.Question;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tp2.clases.view.screens.PlayersInputScreen;
import tp2.clases.view.screens.StartScreen;

import java.io.File;
import java.util.ArrayList;

import static tp2.clases.ConstantsPaths.INTRO_SOUND_PATH;
import static tp2.clases.ConstantsPaths.QUESTIONS_FILE_PATH;

public class GameApp extends Application {

    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Juego de preguntas y respuestas");
        StackPane root = new StackPane();
        Game game = buildModel();

        String introSound = new File(INTRO_SOUND_PATH).toURI().toString();
        Media media = new Media(introSound);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        PlayersInputScreen inputsScreen = new PlayersInputScreen(root, game, mediaPlayer);
        StartScreen startScreen = new StartScreen(root, inputsScreen, mediaPlayer);

        root.getChildren().addAll(startScreen, inputsScreen);
        inputsScreen.setVisible(false);

        Scene mainScene = new Scene(root, 800, 700);
        mainScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setScene(mainScene);
        primaryStage.setFullScreen(false);

        primaryStage.show();
    }

    private Game buildModel() {
        ArrayList<Question> questions = JsonParser.questionsParser(QUESTIONS_FILE_PATH);
        //ArrayList<Question> questions = JsonParser.questionsParser("src/main/resources/groupChoice.json");

        return new Game(questions);
    }
}