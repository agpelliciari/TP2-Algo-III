package tp2.clases.screens;
import tp2.clases.Game;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import tp2.clases.player.Player;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EndGameScreen extends VBox {
    Stage stage;
    Button continueButton;
    Game game;

    public EndGameScreen(Stage primaryStage, Game game) {
        this.game = game;
        stage = primaryStage;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Image image = new Image("file:white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        Label titleLabel = new Label("Fin del Juego");
        titleLabel.setFont(Font.font("Monserrat", FontWeight.BOLD, 40));
        titleLabel.setStyle("-fx-letter-spacing: 2px;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.LIGHTBLUE);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        titleLabel.setEffect(dropShadow);
        this.getChildren().add(titleLabel);

        Player winner = getWinner();
        Label winnerLabel = new Label("GANADOR : " + winner.getName() + " con " + winner.getScore() + " puntos 🏆");
        winnerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        this.getChildren().add(winnerLabel);


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

    }

    private Player getWinner() {
        return game.getPlayers().stream()
                .max(Comparator.comparingInt(Player::getScore))
                .orElseThrow(() -> new IllegalArgumentException("No players in the game"));
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

