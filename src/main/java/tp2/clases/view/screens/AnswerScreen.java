package tp2.clases.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tp2.clases.model.Game;
import tp2.clases.controllers.handlers.ContinueButtonEventHandler;
import tp2.clases.model.player.Player;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static tp2.clases.ConstantsPaths.BACKGROUND_IMAGE_PATH;

public class AnswerScreen extends VBox {

    private StackPane stackPane;
    private Button continueButton;
    private Question currentQuestion;
    private Game game;

    public AnswerScreen(StackPane stackPane, Game game) {
        this.stackPane = stackPane;
        this.game = game;
        this.currentQuestion = game.getCurrentQuestion();

        setupLayout();
        setupBackground();
        addAnswerText();
        addLeaderboard();
        if (game.powerWereUsed())
            addPowersUsedScreen();
        addContinueButton();
    }

    private void setupLayout() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);
    }

    private void setupBackground() {
        Image image = new Image(BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
    }

    private void addAnswerText() {
        TextFlow answerText = QuestionScreenBuilder.createText(currentQuestion.getContent().getAnswerText(), 20);
        this.getChildren().add(answerText);
    }

    private void addLeaderboard() {
        VBox leaderboard = createLeaderboard();
        leaderboard.setStyle("-fx-background-color: #7b96a3; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 1px;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(leaderboard);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        this.getChildren().add(scrollPane);

        VBox leaderboardContainer = new VBox();
        leaderboardContainer.setStyle("-fx-background-color: #3a4d60; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 1px;");
        leaderboardContainer.setAlignment(Pos.CENTER);
        leaderboardContainer.getChildren().add(leaderboard);
    }

    private void addPowersUsedScreen() {
        PowersUsedScreen powersUsedScreen = new PowersUsedScreen(game.getPlayers());
        this.getChildren().add(powersUsedScreen);
    }

    private void addContinueButton() {
        continueButton = new Button("Continuar");
        continueButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");

        ContinueButtonEventHandler continueButtonEventHandler = new ContinueButtonEventHandler(game, stackPane);
        continueButton.setOnAction(continueButtonEventHandler);

        this.getChildren().add(continueButton);
    }

    private VBox createLeaderboard() {
        VBox leaderboard = new VBox();
        leaderboard.setAlignment(Pos.CENTER);
        leaderboard.setSpacing(10);
        leaderboard.setPadding(new Insets(20));

        ArrayList<Player> sortedPlayers = game.getPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        GridPane headerGrid = createHeaderGrid();
        leaderboard.getChildren().add(headerGrid);

        addPlayerRows(leaderboard, sortedPlayers);

        return leaderboard;
    }

    private GridPane createHeaderGrid() {
        GridPane headerGrid = new GridPane();
        headerGrid.setHgap(50);
        headerGrid.setPadding(new Insets(5));
        headerGrid.setStyle("-fx-background-color: #373737; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #000000;");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(15);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(45);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        headerGrid.getColumnConstraints().addAll(col1, col2, col3, col4);

        Label posHeader = new Label("Posición");
        posHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(posHeader, 0, 0);

        Label nameHeader = new Label("Nombre");
        nameHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(nameHeader, 1, 0);

        Label scoreHeader = new Label("Puntaje");
        scoreHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(scoreHeader, 2, 0);

        Label scoreChangeHeader = new Label("Cambio");
        scoreChangeHeader.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        GridPane.setConstraints(scoreChangeHeader, 3, 0);

        headerGrid.getChildren().addAll(posHeader, nameHeader, scoreHeader, scoreChangeHeader);
        return headerGrid;
    }

    private void addPlayerRows(VBox leaderboard, ArrayList<Player> sortedPlayers) {
        int rank = 1;

        for (Player player : sortedPlayers) {
            GridPane playerGrid = createPlayerGrid();

            Label rankLabel = new Label(String.valueOf(rank));
            rankLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(rankLabel, 0, 0);

            Label nameLabel = new Label(player.getName());
            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(nameLabel, 1, 0);

            Label scoreLabel = new Label(String.valueOf(player.getScore()));
            scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(scoreLabel, 2, 0);

            String scoreChangeText = player.getPreviousScore() + (player.getScoreChange() >= 0 ? "+" : "") + player.getScoreChange();
            Label scoreChangeLabel = new Label(scoreChangeText);
            scoreChangeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            GridPane.setConstraints(scoreChangeLabel, 3, 0);

            if (player.getScoreChange() > 0) {
                playerGrid.setStyle("-fx-background-color: #348348; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #000000;");
            } else if (player.getScoreChange() < 0) {
                playerGrid.setStyle("-fx-background-color: #d33a3a; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #000000;");
            } else {
                playerGrid.setStyle("-fx-background-color: #288bdd; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #000000;");
            }

            playerGrid.getChildren().addAll(rankLabel, nameLabel, scoreLabel, scoreChangeLabel);
            leaderboard.getChildren().add(playerGrid);
            rank++;
        }
    }

    private GridPane createPlayerGrid() {
        GridPane playerGrid = new GridPane();
        playerGrid.setHgap(50);
        playerGrid.setPadding(new Insets(5));
        playerGrid.setStyle("-fx-background-color: #45607c; -fx-padding: 10px; -fx-border-width: 0 0 1px 0; -fx-border-color: #000000;");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(15);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(45);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        playerGrid.getColumnConstraints().addAll(col1, col2, col3, col4);

        return playerGrid;
    }
}