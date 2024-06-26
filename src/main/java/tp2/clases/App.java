package tp2.clases;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import tp2.clases.exceptions.InvalidAnswerFormatException;
import tp2.clases.handlers.MultiplicatorButtonHandler;
import tp2.clases.handlers.NullifierCheckBoxEventHandler;
import tp2.clases.screens.*;
import javafx.scene.layout.VBox;

import java.util.*;

public class App extends Application {

    private MainContainer mainContainer;
    private ScoreContainer scoreContainer;
    private int numberOfPlayers = 0;
    private int questionLimit = 0;
    private int questionCount = 0;
    private int pointsLimit = 0;
    private int currentPlayerIndex = 0;
    private int currentQuestionIndex = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private JsonParser jsonParser = new JsonParser();
    private ArrayList<Question> questions;
    private Set<Integer> selectedQuestionIndices = new HashSet<>();
    private ArrayList<Boolean> chosenExclusivities =  new ArrayList<>();
    private int[] playersScore;
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
        primaryStage.setScene(new Scene(mainContainer, 900, 800));
        primaryStage.show();
    }

    public void showNumberOfPlayersField() {
        PlayersInputScreen playersInputScreen = new PlayersInputScreen(this::setNumberOfPlayers, this::setQuestionLimit, this::setPointsLimit);
        updateMainContainer(playersInputScreen);
    }

    private void setNumberOfPlayers(int numberOfPlayers) {
        playersScore = new int[numberOfPlayers];
        this.numberOfPlayers = numberOfPlayers;
        showPlayerNameInputFields();
    }

    private void setQuestionLimit(int limit) {
        this.questionLimit = limit;
    }

    private void setPointsLimit(int limit) {
        this.pointsLimit = limit;
    }

    public void showPlayerNameInputFields() {
        PlayersNamesInputScreen playersNamesInputScreen = new PlayersNamesInputScreen(numberOfPlayers, this::setPlayersNames);
        updateMainContainer(playersNamesInputScreen);
    }

    private void setPlayersNames(ArrayList<String> playersNames) {
        for (String playerName : playersNames) {
            players.add(new Player(playerName, 0));
        }
        this.currentQuestionIndex = getRandomQuestionIndex();
        showQuestionForPlayer();
    }

    private void showQuestionForPlayer() {
        game.checkIfThereIsAScoreNullifierActivated();
        Player currentPlayer = players.get(currentPlayerIndex);
        Question currentQuestion = questions.get(currentQuestionIndex);

        mainContainer.cleanContainer();
        scoreContainer = new ScoreContainer();
        mainContainer.addChild(scoreContainer);

        Panel panel = new Panel(currentPlayer, currentQuestion);
        Button answerButton = PanelBuilder.createAnswerButton(currentQuestion, currentPlayer, panel, this);
        panel.addChild(answerButton);
        pressedKeyEvent(currentQuestion, currentPlayer, panel);

        mainContainer.addChild(panel);
    }

    public void pressedKeyEvent(Question currentQuestion, Player currentPlayer, Panel panel) {
        panel.getAnswerTextField().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    saveAnswerAndProceed(currentQuestion, currentPlayer, panel.isExclusivitySelected(), panel.isNullifierSelected(), panel.getAnswer(), panel.getFactor(), panel.isMultiplicatorSelected());
                } catch (InvalidAnswerFormatException ex) {
                    showErrorDialog(ex.getMessage());
                }
            }
        });
    }

    public void saveAnswerAndProceed(Question question, Player player, boolean useExclusivity, boolean selectedNullifier, String answer, String factor, boolean selectedMultiplicator) {
        validateAnswerFormat(answer);

        MultiplicatorButtonHandler multiplicatorButtonHandler = new MultiplicatorButtonHandler(factor);
        multiplicatorButtonHandler.selectMultiplier(player, selectedMultiplicator);

        boolean isCorrect = false;

        int calculatedScore = question.calculateScore(player, player.setAnswers(question, answer));

        if (calculatedScore > 0) {
            isCorrect = true;
            player.setAnsweredCorrectly();
        }

        playersScore[currentPlayerIndex] = calculatedScore;

        if (useExclusivity) {
            chosenExclusivities.add(true);
            player.getExclusivity().decreaseNumber();
        } else {
            chosenExclusivities.add(false);
        }

        NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
        nullifierHandler.selectNullifier(player, players, selectedNullifier);

        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            calculateExclusivityScore();
            updateScores(playersScore, chosenExclusivities);

            game.deactivateNullifier();
            playersScore = new int[numberOfPlayers];
            chosenExclusivities = new ArrayList<>();
            showCorrectAnswer();

            questionCount++;

            if (limitReached()) {
                showEndGame();
                return;
            }

            currentPlayerIndex = 0;
            currentQuestionIndex = getRandomQuestionIndex();
        } else {
            showQuestionForPlayer();
        }
    }

    private void calculateExclusivityScore() {
        int exclusivityCount = 0;
        for (Boolean chosenExclusivity : chosenExclusivities) {
            if (chosenExclusivity) exclusivityCount++;
        }

        boolean anyCorrectAnswer = false;
        boolean[] correctAnswers = new boolean[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = players.get(i);
            correctAnswers[i] = player.answeredCorrectly();
            if (correctAnswers[i]) anyCorrectAnswer = true;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            if (chosenExclusivities.get(i)) {
                if (exclusivityCount == 1) {
                    if (correctAnswers[i] && !anyCorrectAnswer) {
                        playersScore[i] *= 2;
                    } else {
                        playersScore[i] = 0;
                    }
                } else {
                    if (correctAnswers[i] && !anyCorrectAnswer) {
                        playersScore[i] *= exclusivityCount;
                    } else {
                        playersScore[i] = 0;
                    }
                }
            }
        }
    }

    private void showCorrectAnswer() {
        mainContainer.cleanContainer();
        AnswerScreen answerScreen = new AnswerScreen(() -> showQuestionForPlayer(), questions.get(currentQuestionIndex).getContent().getAnswerText());
        answerScreen.getChildren().add(scoreContainer);
        mainContainer.addChild(answerScreen);
    }

    private void updateScores(int[] playersScore, ArrayList<Boolean> chosenExclusivities) {
        scoreContainer.cleanContainer();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.get(i).assignScore(playersScore[i]);

            Label scoreLabel = new Label(players.get(i).getName() + ": " + players.get(i).getScore() + " puntos");
            scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            scoreContainer.addChild(scoreLabel);
        }
    }

    private void showEndGame() {
        mainContainer.cleanContainer();
        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label endGameLabel = new Label("¡Fin del juego!");
        endGameLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        vbox.getChildren().add(endGameLabel);

        vbox.getChildren().add(scoreContainer);
        mainContainer.addChild(vbox);
    }

    public void showErrorDialog(String errorMessage) {
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

    public Integer getRandomQuestionIndex() {
        Random random = new Random();
        int numQuestions = questions.size();
        int randomIndex = random.nextInt(numQuestions);
        while (selectedQuestionIndices.contains(randomIndex)) {
            randomIndex = random.nextInt(numQuestions);
        }
        return randomIndex;
    }

    private boolean limitReached() {
        return questionLimitReached() | pointsLimitReached();
    }

    private boolean pointsLimitReached() {
        boolean limitReached = false;
        for (Player player: players) {
            if (player.getScore() > pointsLimit) {
                limitReached = true;
            }
        }
        return limitReached;
    }

    private boolean questionLimitReached() {
        return questionCount >= questionLimit;
    }
}