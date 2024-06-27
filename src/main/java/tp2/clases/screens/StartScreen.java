package tp2.clases.screens;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import tp2.clases.handlers.ActionHandler;
import tp2.clases.handlers.StartButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartScreen extends VBox {

    public StartScreen(ActionHandler startHandler) {
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
    }
}