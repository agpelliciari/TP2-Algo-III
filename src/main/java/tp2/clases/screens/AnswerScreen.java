package tp2.clases.screens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.player.Player;
import tp2.clases.handlers.ContinueButtonEventHandler;
import tp2.clases.questions.types.Question;

import java.util.ArrayList;
import java.util.List;


public class AnswerScreen extends VBox {
    Stage stage;
    Button continueButton;
    Question currentQuestion;
    FlowPane scores;


    public AnswerScreen(Stage primaryStage, Scene gameScene, Game game) {
        this.currentQuestion = game.getCurrentQuestion();
        stage = primaryStage;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Image image = new Image("file:white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        Label answerTextLabel = createAnswerTextLabel();
        this.getChildren().add(answerTextLabel);

        VBox scoreContainer = new VBox(10);
        scoreContainer.setAlignment(Pos.CENTER);
        scoreContainer.setPadding(new Insets(20));
        scoreContainer.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Label playersTextLabel = createScoresLabel();
        scoreContainer.getChildren().add(playersTextLabel);

        scores = new FlowPane();
        scores.setHgap(20);
        scores.setVgap(20);
        scores.setAlignment(Pos.CENTER);

        showPlayersScore(game);
        scoreContainer.getChildren().add(scores);
        this.getChildren().add(scoreContainer);

        ArrayList<Player> players = game.getPlayers();
        PowersUsedScreen powersUsedScreen = new PowersUsedScreen(players);
        this.getChildren().add(powersUsedScreen);

        continueButton = new Button("Continue");
        continueButton.setStyle("-fx-font-size: 14px; -fx-background-color: #090971; -fx-text-fill: white;");
        ContinueButtonEventHandler continueButtonEventHandler = new ContinueButtonEventHandler(game, gameScene, primaryStage);
        continueButton.setOnAction(continueButtonEventHandler);
        this.getChildren().add(continueButton);
    }

    private Label createAnswerTextLabel() {
        String answerText = currentQuestion.getContent().getAnswerText();
        Label label = new Label(answerText);
        label.setPadding(new Insets(20));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setWrapText(true);
        return label;
    }

    private Label createScoresLabel() {
        Label label = new Label("Puntajes");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        label.setPadding(new Insets(20));
        label.setStyle("-fx-text-fill: #333;");
        return label;
    }


    public void showPlayersScore(Game game) {
        scores.getChildren().clear();

        List<String> colors = new ArrayList<>();
        colors.add("#FFA07A"); // LightSalmon
        colors.add("#F08080"); // LightCoral
        colors.add("#FFB6C1"); // LightPink
        colors.add("#FAFAD2"); // LightGoldenrodYellow
        colors.add("#FFFFE0"); // LightYellow
        colors.add("#90EE90"); // LightGreen
        colors.add("#87CEFA"); // LightSkyBlue
        colors.add("#ADD8E6"); // LightBlue
        colors.add("#B0C4DE"); // LightSteelBlue
        colors.add("#E0FFFF"); // LightCyan
        colors.add("#778899"); // LightSlateGray
        colors.add("#D3D3D3"); // LightGray

        int colorIndex = 0;

        for (Player player : game.getPlayers()) {
            VBox scoreBox = new VBox(10);
            scoreBox.setPadding(new Insets(20));

            String color = colors.get(colorIndex % colors.size());
            scoreBox.setStyle("-fx-background-color: " + color + "; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 3px; -fx-background-radius: 3px;");

            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            scoreLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 10px;");
            scoreBox.getChildren().add(scoreLabel);

            scores.getChildren().add(scoreBox);
            colorIndex++;
        }
    }
}
