package tp2.clases.screens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import tp2.clases.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tp2.clases.player.Player;
import tp2.clases.handlers.ContinueButtonEventHandler;
import tp2.clases.questions.types.Question;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AnswerScreen extends VBox {
    Stage stage;
    Button continueButton;
    Question currentQuestion;
    FlowPane scores;
    Game game;


    public AnswerScreen(Stage primaryStage, Game game) {
        this.currentQuestion = game.getCurrentQuestion();
        stage = primaryStage;
        this.game = game;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Image image = new Image("file:white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        Label answerTextLabel = createAnswerTextLabel();
        this.getChildren().add(answerTextLabel);

        VBox leaderboard = createLeaderboard();
        leaderboard.setStyle("-fx-background-color: #0d3e5a; -fx-padding: 10px; -fx-border-color: #ffffff; -fx-border-width: 1px;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(leaderboard);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        this.getChildren().add(scrollPane);

        VBox leaderboardContainer = new VBox();
        leaderboardContainer.setStyle("-fx-background-color: #266d99; -fx-padding: 10px; -fx-border-color: #ffffff; -fx-border-width: 1px;");
        leaderboardContainer.setAlignment(Pos.CENTER);
        leaderboardContainer.getChildren().add(leaderboard);


        ArrayList<Player> players = game.getPlayers();
        PowersUsedScreen powersUsedScreen = new PowersUsedScreen(players);
        this.getChildren().add(powersUsedScreen);

        continueButton = new Button("Continuar");
        continueButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        ContinueButtonEventHandler continueButtonEventHandler = new ContinueButtonEventHandler(game, primaryStage);
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


    private VBox createLeaderboard() {
        VBox leaderboard = new VBox();
        leaderboard.setAlignment(Pos.CENTER);
        leaderboard.setSpacing(10);
        leaderboard.setPadding(new Insets(20));

        List<Player> sortedPlayers = game.getPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toList());


        GridPane headerGrid = new GridPane();
        headerGrid.setHgap(50);
        headerGrid.setPadding(new Insets(5));
        headerGrid.setStyle("-fx-background-color: #0b4163; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #0b4163;");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        headerGrid.getColumnConstraints().addAll(col1, col2, col3);

        Label posHeader = new Label("Posición");
        posHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(posHeader, 0, 0);

        Label nameHeader = new Label("Nombre");
        nameHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(nameHeader, 1, 0);

        Label scoreHeader = new Label("Puntaje");
        scoreHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(scoreHeader, 2, 0);

        headerGrid.getChildren().addAll(posHeader, nameHeader, scoreHeader);
        leaderboard.getChildren().add(headerGrid);

        int rank = 1;
        for (Player player : sortedPlayers) {


            GridPane playerGrid = new GridPane();
            playerGrid.setHgap(50);
            playerGrid.setPadding(new Insets(5));
            playerGrid.setStyle("-fx-background-color: #266d99; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #266d99;");
            playerGrid.getColumnConstraints().addAll(col1, col2, col3);


            Label rankLabel = new Label(String.valueOf(rank));
            rankLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

            GridPane.setConstraints(rankLabel, 0, 0);

            Label nameLabel = new Label(player.getName());
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(nameLabel, 1, 0);

            Label scoreLabel = new Label(String.valueOf(player.getScore()));
            scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(scoreLabel, 2, 0);

            playerGrid.getChildren().addAll(rankLabel, nameLabel, scoreLabel);
            leaderboard.getChildren().add(playerGrid);
            rank++;
        }




        return leaderboard;
    }
}

