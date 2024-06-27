package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tp2.clases.Player;
import tp2.clases.handlers.ActionHandler;
import tp2.clases.handlers.ContinueButtonEventHandler;

import java.util.ArrayList;

public class AnswerScreen extends VBox {

    public AnswerScreen(ActionHandler continueHandler, String answerText, ArrayList<Player> players) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));

        Label title = new Label("Respuesta");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.getChildren().add(title);

        Label answer = new Label(answerText);
        answer.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.getChildren().add(answer);

        PowersUsedScreen powersUsedScreen = new PowersUsedScreen(players);
        this.getChildren().add(powersUsedScreen);

        Button continueButton = new Button("Continuar");
        continueButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        continueButton.setStyle("-fx-background-color: #266d99; -fx-text-fill: white; -fx-padding: 10px 20px;");
        continueButton.setOnAction(new ContinueButtonEventHandler(continueHandler));

        this.getChildren().add(continueButton);
    }
}
