package tp2.clases;

import tp2.clases.exceptions.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private VBox mainContainer;
    private HBox scoreContainer;
    private int numberOfPlayers = 0;
    private int currentPlayerIndex = 0;
    private int currentQuestionIndex = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private JsonParser jsonParser = new JsonParser();
    private ArrayList<Question> questions;
    private ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
    private Label questionLabel, choiceLabel;
    private TextField answerTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);

        Button startButton = new Button("Comenzar");
        startButton.setOnAction(e -> showNumberOfPlayersField());
        mainContainer.getChildren().add(startButton);

        primaryStage.setTitle("Juego de preguntas y respuestas");
        primaryStage.setScene(new Scene(mainContainer, 800, 600));
        primaryStage.show();
    }

    private void showNumberOfPlayersField() {
        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label label = new Label("Ingrese la cantidad de jugadores: ");
        vbox.getChildren().add(label);

        TextField numberOfPlayersTextField = new TextField();
        numberOfPlayersTextField.setPromptText("Cantidad de jugadores");
        vbox.getChildren().add(numberOfPlayersTextField);

        Button confirmButton = new Button("Confirmar");
        confirmButton.setOnAction(e -> {
            if (confirmNumberOfPlayers(numberOfPlayersTextField)) {
                showPlayerNameInputFields();
            }
        });
        vbox.getChildren().add(confirmButton);

        numberOfPlayersTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (confirmNumberOfPlayers(numberOfPlayersTextField)) {
                    showPlayerNameInputFields();
                }
            }
        });

        updateMainContainer(vbox);
        numberOfPlayersTextField.requestFocus();
    }

    private boolean confirmNumberOfPlayers(TextField numberOfPlayersTextField) {
        try {
            numberOfPlayers = Integer.parseInt(numberOfPlayersTextField.getText());
            for (int i = 0; i < numberOfPlayers; i++) {
                chosenExclusivities.add(new boolean[questions.size()]);
            }
            numberOfPlayersTextField.clear();
            return true;
        } catch (NumberFormatException e) {
            showErrorDialog("Por favor ingrese un número válido.");
            return false;
        }
    }

    private void showPlayerNameInputFields() {
        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        for (int i = 0; i < numberOfPlayers; i++) {
            Label label = new Label("Ingrese el nombre del jugador " + (i + 1) + ": ");
            vbox.getChildren().add(label);

            TextField playerNameTextField = new TextField();
            playerNameTextField.setPromptText("Nombre del jugador");
            vbox.getChildren().add(playerNameTextField);

            Button confirmButton = new Button("Confirmar");
            confirmButton.setOnAction(e -> handlePlayerNameEntry(playerNameTextField));
            vbox.getChildren().add(confirmButton);

            playerNameTextField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    handlePlayerNameEntry(playerNameTextField);
                }
            });
        }

        updateMainContainer(vbox);
    }

    private void handlePlayerNameEntry(TextField playerNameTextField) {
        confirmPlayersName(playerNameTextField);
        if (players.size() == numberOfPlayers) {
            showQuestionForPlayer();
        }
    }

    private void confirmPlayersName(TextField playerNameTextField) {
        String playerName = playerNameTextField.getText();
        if (!playerName.isEmpty()) {
            players.add(new Player(playerName, 0));
            playerNameTextField.clear();
            playerNameTextField.setDisable(true);
        } else {
            showErrorDialog("Por favor ingrese un nombre de jugador válido.");
        }
    }

    private void showQuestionForPlayer() {
        if (currentQuestionIndex >= questions.size()) {
            showEndGame();
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        Question currentQuestion = questions.get(currentQuestionIndex);

        mainContainer.getChildren().clear();

        scoreContainer = new HBox(10);
        scoreContainer.setAlignment(Pos.TOP_RIGHT);
        scoreContainer.setPadding(new Insets(10));
        updateScores();
        mainContainer.getChildren().add(scoreContainer);

        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label questionNumberLabel = new Label("Pregunta " + currentQuestion.getId());
        questionNumberLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vbox.getChildren().add(questionNumberLabel);

        Label playerLabel = new Label(currentPlayer.getName());
        playerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        vbox.getChildren().add(playerLabel);

        questionLabel = new Label(currentQuestion.getContent().getTheme());
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 16px;");
        vbox.getChildren().add(questionLabel);

        questionLabel = new Label(currentQuestion.getContent().getPrompt());
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 16px;");
        vbox.getChildren().add(questionLabel);

        for (int i = 0; i < currentQuestion.getChoices().size(); i++) {
            choiceLabel = new Label(currentQuestion.getChoices().get(i).getId() + ") " + currentQuestion.getChoices().get(i).getContent());
            choiceLabel.setWrapText(true);
            choiceLabel.setStyle("-fx-font-size: 14px;");
            vbox.getChildren().add(choiceLabel);
        }

        Label helpAnswerFormatLabel = new Label("Escriba la respuesta");
        helpAnswerFormatLabel.setStyle("-fx-font-size: 16px");
        vbox.getChildren().add(helpAnswerFormatLabel);

        answerTextField = new TextField();
        answerTextField.setPromptText("Respuesta");
        vbox.getChildren().add(answerTextField);

        CheckBox exclusivityCheckBox = new CheckBox("Usar exclusividad");
        vbox.getChildren().add(exclusivityCheckBox);

        HBox multiplicatorContainer = new HBox(10);
        multiplicatorContainer.setAlignment(Pos.CENTER);
        CheckBox multiplicatorCheckBox = new CheckBox("Usar multiplicador");
        TextField factorTextField = new TextField();
        factorTextField.setPromptText("Factor");
        factorTextField.setPrefWidth(50);
        multiplicatorContainer.getChildren().addAll(multiplicatorCheckBox, factorTextField);
        vbox.getChildren().add(multiplicatorContainer);


        Button answerButton = new Button("Responder");
        answerButton.setOnAction(e -> {
            try {
                saveAnswerAndProceed(currentQuestion, currentPlayer, exclusivityCheckBox.isSelected());
            } catch (InvalidAnswerFormatException ex) {
                showErrorDialog(ex.getMessage());
            }
        });
        vbox.getChildren().add(answerButton);

        answerTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    saveAnswerAndProceed(currentQuestion, currentPlayer, exclusivityCheckBox.isSelected());
                } catch (InvalidAnswerFormatException ex) {
                    showErrorDialog(ex.getMessage());
                }
            }
        });

        mainContainer.getChildren().add(vbox);
    }

    private void saveAnswerAndProceed(Question question, Player player, boolean useExclusivity) throws InvalidAnswerFormatException {
        String answer = answerTextField.getText();
        validateAnswerFormat(answer);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, answer);
        question.assignScore(player, chosenAnswers);

        chosenExclusivities.get(currentPlayerIndex)[currentQuestionIndex] = useExclusivity;

        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
            currentQuestionIndex++;
        }

        updateScores();
        showQuestionForPlayer();
    }

    private void updateScores() {
        scoreContainer.getChildren().clear();
        for (Player player : players) {
            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            scoreContainer.getChildren().add(scoreLabel);
        }
    }

    private void showEndGame() {
        mainContainer.getChildren().clear();
        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label endGameLabel = new Label("¡Fin del juego!");
        endGameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        vbox.getChildren().add(endGameLabel);

        updateScores();
        vbox.getChildren().add(scoreContainer);

        mainContainer.getChildren().add(vbox);
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private void validateAnswerFormat(String answer) throws InvalidAnswerFormatException {
        if (!answer.matches("^[0-9]+(,[0-9]+)*$")) {
            throw new InvalidAnswerFormatException("El formato de la respuesta es inválido. Use un formato como '1', '1,2' o '1,3,2'.");
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        questions = jsonParser.questionsParser("src/main/resources/preguntas.json");
    }

    private VBox createVBoxWithPaddingAndAlignment(Pos alignment, double spacing, double padding) {
        VBox vbox = new VBox(spacing);
        vbox.setAlignment(alignment);
        vbox.setPadding(new Insets(padding));
        return vbox;
    }

    private void updateMainContainer(VBox newContent) {
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(newContent);
    }
}