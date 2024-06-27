package tp2.clases;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tp2.clases.exceptions.InvalidAnswerFormatException;
import tp2.clases.handlers.MultiplicatorButtonHandler;
import tp2.clases.handlers.NullifierCheckBoxEventHandler;
import tp2.clases.screens.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class App extends Application {
    private MainContainer mainContainer;
    private ScoreContainer scoreContainer;
    private int numberOfPlayers = 0;
    private int questionLimit = 0;
    private int questionCount = 0;
    private int limitScore = 0;
    private int currentPlayerIndex = 0;
    private int currentQuestionIndex = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private JsonParser jsonParser = new JsonParser();
    private ArrayList<Question> questions;
    private Set<Integer> selectedQuestionIndices = new HashSet<>();
    int numberOfPlayersWhoUsedExclusivity = 0;
    private int[] playersScore;
    private Game game;

    public void initialize(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainContainer = new MainContainer();
        StartScreen startScreen = new StartScreen(this::showNumberOfPlayersField);
        mainContainer.addChild(startScreen);

        primaryStage.setTitle("Juego de preguntas y respuestas");
        primaryStage.setScene(new Scene(mainContainer, 1000, 800));
        primaryStage.show();
    }

    public void showNumberOfPlayersField() {
        PlayersInputScreen playersInputScreen = new PlayersInputScreen(this::setNumberOfPlayers, this::setQuestionLimit, this::setLimitScore);
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

    private void setLimitScore(int limit) {
        this.limitScore = limit;
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

    public void showQuestionForPlayer() {
        if (currentQuestionIndex >= questions.size()) {
            showEndGame();
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        Question currentQuestion = questions.get(currentQuestionIndex);

        mainContainer.cleanContainer();
        scoreContainer = new ScoreContainer();
        mainContainer.addChild(scoreContainer);
        showPlayersScore();


        if (currentQuestion instanceof GroupChoice) {
            PanelGroupChoice panel = new PanelGroupChoice(currentPlayer, currentQuestion, this);
            mainContainer.addChild(panel);
        }
        else {
            Panel panel = new Panel(currentPlayer, currentQuestion, this);
            mainContainer.addChild(panel);
        }
    }

    public void saveAnswerAndProceed(Question question, Player player, boolean useExclusivity, boolean selectedNullifier, String answer, String factor, boolean selectedMultiplicator) throws InvalidAnswerFormatException {
        validateAnswerFormat(answer);

        MultiplicatorButtonHandler multiplicatorButtonHandler = new MultiplicatorButtonHandler(factor);
        multiplicatorButtonHandler.selectMultiplier(player, selectedMultiplicator);

        playersScore[currentPlayerIndex] = question.calculateScore(player, player.setAnswers(question, answer));

        if (useExclusivity) {
            numberOfPlayersWhoUsedExclusivity++;
            player.getExclusivity().decreaseNumber();
        }

        NullifierCheckBoxEventHandler nullifierHandler = new NullifierCheckBoxEventHandler();
        nullifierHandler.selectNullifier(player, players, selectedNullifier);

        currentPlayerIndex++;
        if (currentPlayerIndex >= numberOfPlayers) {
            if (question.getMode().isPenaltyMode()) {
                for (int i = 0; i < numberOfPlayers; i++) {
                    players.get(i).addToScore(playersScore[i]);
                }
            } else {
                updatePlayersScoreWithExclusivity(playersScore, numberOfPlayersWhoUsedExclusivity);
            }

            showPlayersScore();
            showCorrectAnswer();

            questionCount++;
            if (limitReached()) {
                showEndGame();
                return;
            }
            currentPlayerIndex = 0;
            currentQuestionIndex = getRandomQuestionIndex();
            deactivatePowers(players);
            numberOfPlayersWhoUsedExclusivity = 0;
        } else {
            showQuestionForPlayer();
        }
    }

    public void updatePlayersScoreWithExclusivity(int[] playersScore, int numberOfPlayersWhoUsedExclusivity) {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfPlayersWhoUsedExclusivity > 0) {
                if (playersScore[i] > 0) {
                    if (game.checkIfOnlyOneCorrectAnswer(playersScore)) {
                        players.get(i).addToScore(playersScore[i] * players.get(i).getExclusivity().getMultiplier() * numberOfPlayersWhoUsedExclusivity);
                    } else if (game.checkIfAllAreCorrectAnswers(playersScore)) {
                        // Nadie suma nada
                    } else {
                        players.get(i).addToScore(playersScore[i]);
                    }
                } else {
                    players.get(i).addToScore(playersScore[i]);
                }
            } else {
                players.get(i).addToScore(playersScore[i]);
            }
        }
    }

    private void deactivatePowers(ArrayList<Player> players) {
        for (Player player : players) {
            player.disablePowers();
        }
    }

    private void showCorrectAnswer() {
        mainContainer.cleanContainer();
        AnswerScreen answerScreen = new AnswerScreen(this::showQuestionForPlayer, questions.get(currentQuestionIndex).getContent().getAnswerText(), players);
        answerScreen.getChildren().add(scoreContainer);
        mainContainer.addChild(answerScreen);
    }

    private void showPlayersScore() {
        scoreContainer.cleanContainer();
        for (Player player : players) {
            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            scoreContainer.addChild(scoreLabel);
        }
    }

    private void showEndGame() {
        mainContainer.cleanContainer();
        VBox vbox = createVBoxWithPaddingAndAlignment(Pos.CENTER, 20, 20);

        Label endGameLabel = new Label("¡Fin del juego!");
        endGameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
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
        game = new Game(players, questions);
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
        while (selectedQuestionIndices.contains(randomIndex) || checkIfRepeatedTheme(randomIndex)) {
            randomIndex = random.nextInt(numQuestions);
        }
        selectedQuestionIndices.add(randomIndex);

        return randomIndex;
    }

    public boolean checkIfRepeatedTheme(int index) {
        if ((selectedQuestionIndices.size()) == questions.size() - 1) {
            return false;
        }
        String newTheme = questions.get(index).getContent().getTheme();
        String currentTheme = questions.get(currentQuestionIndex).getContent().getTheme();
        return newTheme.equals(currentTheme);
    }

    private boolean limitReached() {
        return questionLimitReached() || scoreLimitReached();
    }

    private boolean scoreLimitReached() {
        boolean limitReached = false;
        for (Player player : players) {
            if (player.getScore() >= limitScore) {
                limitReached = true;
            }
        }
        return limitReached;
    }

    private boolean questionLimitReached() {
        return questionCount >= questionLimit;
    }
}