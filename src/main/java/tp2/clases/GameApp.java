package tp2.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.kordamp.bootstrapfx.BootstrapFX;
import tp2.clases.questions.types.Question;
import tp2.clases.screens.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

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

        String introSound = new File("src/main/resources/sounds/intro-sound.mp3").toURI().toString();
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
        ArrayList<Question> questions = JsonParser.questionsParser("src/main/resources/preguntas.json");
        //ArrayList<Question> questions = JsonParser.questionsParser("src/main/resources/groupChoice.json");

        return new Game(questions);
    }
}