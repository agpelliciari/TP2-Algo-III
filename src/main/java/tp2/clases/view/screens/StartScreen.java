package tp2.clases.view.screens;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import tp2.clases.controllers.handlers.ActionHandler;

import static tp2.clases.ConstantsPaths.BACKGROUND_IMAGE_PATH;
import static tp2.clases.ConstantsPaths.LOGO_IMAGE_PATH;

public class StartScreen extends VBox {

    private StackPane stackPane;
    private PlayersInputScreen inputsScreen;
    private MediaPlayer mediaPlayer;

    public StartScreen(StackPane stackPane, PlayersInputScreen inputsScreen, MediaPlayer mediaPlayer) {
        super();
        this.stackPane = stackPane;
        this.inputsScreen = inputsScreen;
        this.mediaPlayer = mediaPlayer;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setPrefSize(800, 600);

        Image image = new Image(BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        ImageView logo = new ImageView(new Image(LOGO_IMAGE_PATH));
        logo.setFitWidth(300);
        logo.setFitHeight(350);

        Button startButton = new Button();
        startButton.setText("Comenzar");
        startButton.getStyleClass().setAll("btn", "btn-primary");
        startButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");

        Label subtitle = new Label("¡Testea tu conocimiento con nuestra emocionante trivia de preguntas!");
        subtitle.setTextFill(Color.GRAY);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1500), startButton);
        translateTransition.setFromY(50);
        translateTransition.setToY(0);
        translateTransition.play();

        startButton.setOnAction(event -> {
            ActionHandler.actionSound();
            this.setVisible(false);
            inputsScreen.setVisible(true);
        });

        this.getChildren().addAll(logo, subtitle, startButton);
    }
}