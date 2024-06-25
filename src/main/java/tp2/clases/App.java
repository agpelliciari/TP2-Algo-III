package tp2.clases;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tp2.clases.exceptions.InvalidAnswerFormatException;
import tp2.clases.handlers.NullifierCheckBoxEventHandler;
import tp2.clases.screens.MainContainer;
import tp2.clases.screens.PlayersInputScreen;
import tp2.clases.screens.PlayersNamesInputScreen;
import tp2.clases.screens.StartScreen;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    private MainContainer mainContainer;
    private HBox scoreContainer;
    private int numberOfPlayers = 0;
    private int currentPlayerIndex = 0;
    private int currentQuestionIndex = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private JsonParser jsonParser = new JsonParser();
    private ArrayList<Question> questions;
//    private ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
    private Label questionLabel, choiceLabel;
    private TextField answerTextField;
    private Game game;

    public void initialize(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainContainer = new MainContainer();

        StartScreen startScreen = new StartScreen(() -> showNumberOfPlayersField());

        mainContainer.addChild(startScreen);

        primaryStage.setTitle("Juego de preguntas y respuestas");
        primaryStage.setScene(new Scene(mainContainer, 800, 700));
        primaryStage.show();

    }

    public void showNumberOfPlayersField() {
        PlayersInputScreen playersInputScreen = new PlayersInputScreen(this::setNumberOfPlayers);
        updateMainContainer(playersInputScreen);
    }

    private void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        showPlayerNameInputFields();
    }

    public void showPlayerNameInputFields() {
        PlayersNamesInputScreen playersNamesInputScreen = new PlayersNamesInputScreen(numberOfPlayers, this::setPlayersNames);
        updateMainContainer(playersNamesInputScreen);
    }


    private void setPlayersNames(ArrayList<String> playersNames) {
        for (String playerName : playersNames) {
            players.add(new Player(playerName, 0));
        }
        showQuestionForPlayer();
    }


    private void showQuestionForPlayer() {
        if (currentQuestionIndex >= questions.size()) {
            showEndGame();
            return;
        }

        game.checkIfThereIsAScoreNullifierActivated();

        Player currentPlayer = players.get(currentPlayerIndex);
        Question currentQuestion = questions.get(currentQuestionIndex);

        mainContainer.cleanContainer();

        scoreContainer = new HBox(10);
        scoreContainer.setAlignment(Pos.TOP_RIGHT);
        scoreContainer.setPadding(new Insets(10));
        updateScores();

        mainContainer.addChild(scoreContainer);

        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label questionNumberLabel = new Label("Pregunta " + currentQuestion.getId());
        questionNumberLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vbox.getChildren().add(questionNumberLabel);

        Label playerLabel = new Label(currentPlayer.getName());
        playerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ff9900;");
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
        answerTextField.setStyle("-fx-font-size: 14px;");
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

        CheckBox nullifierCheckBox = new CheckBox("Usar anulador");
        vbox.getChildren().add(nullifierCheckBox);

        Button answerButton = new Button("Responder");
        answerButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ff6666; -fx-text-fill: white;");
        answerButton.setOnAction(e -> {
            try {
                saveAnswerAndProceed(currentQuestion, currentPlayer, exclusivityCheckBox.isSelected(), nullifierCheckBox.isSelected());
            } catch (InvalidAnswerFormatException ex) {
                showErrorDialog(ex.getMessage());
            }
        });
        vbox.getChildren().add(answerButton);

        answerTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    saveAnswerAndProceed(currentQuestion, currentPlayer, exclusivityCheckBox.isSelected(), nullifierCheckBox.isSelected());
                } catch (InvalidAnswerFormatException ex) {
                    showErrorDialog(ex.getMessage());
                }
            }
        });

        mainContainer.addChild(vbox);
    }

    private void saveAnswerAndProceed(Question question, Player player, boolean useExclusivity, boolean selectedNullifier) throws InvalidAnswerFormatException {
        String answer = answerTextField.getText();
        validateAnswerFormat(answer);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, answer);
        question.assignScore(player, chosenAnswers);

        NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler(game);
        nullifierHandler.selectNullifier(player, selectedNullifier);

//        chosenExclusivities.get(currentPlayerIndex)[currentQuestionIndex] = useExclusivity;

        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
            currentQuestionIndex++;
            game.deactivateNullifier();
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
        mainContainer.cleanContainer();

        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label endGameLabel = new Label("¡Fin del juego!");
        endGameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        vbox.getChildren().add(endGameLabel);

        updateScores();
        vbox.getChildren().add(scoreContainer);

        mainContainer.addChild(vbox);
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
        game = new Game(players, questions, 100);
    }

    private VBox createVBoxWithPaddingAndAlignment(Pos alignment, double spacing, double padding) {
        VBox vbox = new VBox(spacing);
        vbox.setAlignment(alignment);
        vbox.setPadding(new Insets(padding));
        return vbox;
    }

    private void updateMainContainer(VBox newContent) {
        mainContainer.cleanContainer();
        mainContainer.addChild(newContent);
    }
}