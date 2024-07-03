package tp2.clases.screens;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import tp2.clases.handlers.ActionHandler;
import tp2.clases.handlers.StartButtonEventHandler;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

public class StartScreen extends VBox {

    Stage stage;

    /*public StartScreen(ActionHandler startHandler) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));

        Label title = new Label("Juego de Preguntas y Respuestas");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.getChildren().add(title);

        Button startButton = new Button("Comenzar");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        startButton.setStyle("-fx-background-color: #266d99; -fx-text-fill: white; -fx-padding: 10px 20px;");

        FadeTransition ft = new FadeTransition(Duration.millis(1500), startButton);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        startButton.setOnAction(new StartButtonEventHandler(startHandler));
        this.getChildren().add(startButton);

        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#b3e0ff"), CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(backgroundFill));
    }*/

    public StartScreen(Stage primaryStage, Scene playerInputScene) {
        super();

        this.stage = primaryStage;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setPrefSize(800, 600);

        Image image = new Image("file:white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        ImageView logo = new ImageView(new Image("file:algohoot_logo.png"));
        logo.setFitWidth(300);
        logo.setFitHeight(350);

        Button startButton = new Button();
        startButton.setText("Comenzar");
        startButton.getStyleClass().setAll("btn", "btn-primary");

        startButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");

        Label subtitle = new Label("Testea tu conocimiento con nuestra emocionante trivia de preguntas!");
        subtitle.setTextFill(Color.GRAY);


        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1500), startButton);
        translateTransition.setFromY(50);
        translateTransition.setToY(0);
        translateTransition.play();

        String introSound = new File("src/main/resources/sounds/intro-sound.mp3").toURI().toString();
        Media media = new Media(introSound);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(primaryStage, playerInputScene, mediaPlayer);
        startButton.setOnAction(startButtonEventHandler);

        this.getChildren().addAll(logo, subtitle, startButton);
    }
}