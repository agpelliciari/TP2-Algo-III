package tp2.clases.screens;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import tp2.clases.handlers.ActionHandler;
import tp2.clases.handlers.StartButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        Image image = new Image("file:textura.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        Button startButton = new Button();
        startButton.setText("Comenzar");
        startButton.getStyleClass().setAll("btn", "btn-primary");

        Label title = new Label();
        title.setText("Juego de Preguntas y Respuestas");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(primaryStage, playerInputScene);
        startButton.setOnAction(startButtonEventHandler);

        this.getChildren().addAll(title, startButton);
    }
}