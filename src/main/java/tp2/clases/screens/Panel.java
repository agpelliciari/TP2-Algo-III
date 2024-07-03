package tp2.clases.screens;

import javafx.animation.ScaleTransition;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import tp2.clases.Game;
import tp2.clases.handlers.AnswerButtonHandler;
import tp2.clases.player.Player;
import tp2.clases.questions.types.Question;
import tp2.clases.questions.choice.Choice;

import java.util.ArrayList;

public class Panel extends ScrollPane {

    ScoreContainer scores;
    StackPane root;
    Button answerButton;

    private CheckBox exclusivityCheckBox;
    private CheckBox nullifierCheckBox;
    private ArrayList<CheckBox> multiplicatorCheckBoxes = new ArrayList<>();
    private VBox box;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> buttonAnswerList = new ArrayList<>();
    private ArrayList<String> selectedAnswers = new ArrayList<>();

    public Panel(StackPane root, Game game, int playerIndex, int questionIndex) {
        this.root = root;
        setUI(game, playerIndex, questionIndex);
    }

    private void setUI(Game game, int playerIndex, int questionIndex) {
        Question currentQuestion = game.getQuestion(questionIndex);
        Player currentPlayer = game.getPlayer(playerIndex);

        setVBox();
        setBackground();
        setPlayerScores(game, currentPlayer);
        setQuestionAndPlayerInfo(currentQuestion, game);
        setChoiceButtons(currentQuestion);
        setPowers(currentQuestion, currentPlayer);
        setAnswerButton(game, playerIndex, questionIndex);

        this.setContent(box);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    private void setVBox() {
        box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 20, 20, 20));

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), box);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }

    private void setBackground() {
        Image image = new Image("file:white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        box.setBackground(new Background(backgroundImage));
    }

    private void setPlayerScores(Game game, Player currentPlayer) {
        this.scores = new ScoreContainer();
        showPlayersScore(game, currentPlayer);
        box.getChildren().add(scores);
    }

    private void showPlayersScore(Game game, Player currentPlayer) {
        scores.cleanContainer();
        for (Player player : game.getPlayers()) {
            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            if (player.getName().equals(currentPlayer.getName())) {
                scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            } else {
                scoreLabel.setStyle("-fx-font-size: 14px;");
            }
            scores.addChild(scoreLabel);
        }
    }

    private void setQuestionAndPlayerInfo(Question currentQuestion, Game game) {
        box.getChildren().addAll(
                PanelBuilder.createLabel("Pregunta " + game.getQuestionCount(), 20, true),
                PanelBuilder.createLabel(currentQuestion.getContent().getTheme(), 16, false),
                PanelBuilder.createText(currentQuestion.getContent().getPrompt(), 16)
        );
    }

    private void setChoiceButtons(Question currentQuestion) {
        for (Choice choice : currentQuestion.getChoices()) {
            Button choiceButton = new Button(choice.getContent());
            choiceButton.setStyle("-fx-font-size: 14px;");
            buttons.add(choiceButton);
            buttonAnswerList.add(String.valueOf(choice.getId()));
            choiceButton.setOnAction(event -> handleChoiceButton(choiceButton));
            box.getChildren().add(choiceButton);
        }
    }

    private void handleChoiceButton(Button choiceButton) {
        int index = buttons.indexOf(choiceButton);
        String choiceId = buttonAnswerList.get(index);

        if (selectedAnswers.contains(choiceId)) {
            selectedAnswers.remove(choiceId);
            choiceButton.setStyle("-fx-font-size: 14px;");
        } else {
            selectedAnswers.add(choiceId);
            choiceButton.setStyle("-fx-font-size: 14px; -fx-background-color: lightblue;");
        }
        checkSelectedAnswers();
    }

    private void checkSelectedAnswers() {
        answerButton.setDisable(selectedAnswers.isEmpty());
    }

    private void setPowers(Question currentQuestion, Player currentPlayer) {
        exclusivityCheckBox = new CheckBox("Usar exclusividad (" + currentPlayer.getExclusivity().getNumber() + (currentPlayer.getExclusivity().getNumber() == 1 ? " restante)" : " restantes)"));
        for (int i = 0; i < currentPlayer.getMultipliers().size(); i++)
            multiplicatorCheckBoxes.add(new CheckBox("Usar multiplicador x" + currentPlayer.getMultipliers().get(i).getFactor()));
        nullifierCheckBox = new CheckBox("Usar anulador");

        if (currentQuestion.getMode().isPenaltyMode()) {
            for (int i = 0; i < multiplicatorCheckBoxes.size(); i++)
                    if (!currentPlayer.getMultipliers().get(i).isUsed())
                        box.getChildren().add(PanelBuilder.createMultiplierContainer(multiplicatorCheckBoxes.get(i)));
        } else if (currentPlayer.getExclusivity().getNumber() > 0) {
            box.getChildren().add(exclusivityCheckBox);
        }
        if (!currentPlayer.getNullifier().isUsed())
            box.getChildren().add(nullifierCheckBox);
    }

    private void setAnswerButton(Game game, int playerIndex, int questionIndex) {
        answerButton = new Button("Responder");
        answerButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px; -fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        answerButton.setDisable(true);
        AnswerButtonHandler answerButtonHandler = new AnswerButtonHandler(root, game, playerIndex, questionIndex, this);
        answerButton.setOnAction(answerButtonHandler);

        double width = 150;
        double height = 45;
        answerButton.setMinSize(width, height);
        answerButton.setPrefSize(width, height);
        answerButton.setMaxSize(width, height);

        box.getChildren().add(answerButton);
    }

    public String getSelectedAnswers() {
        StringBuilder answerBuilder = new StringBuilder();
        for (String answer : selectedAnswers) {
            if (answerBuilder.length() > 0) {
                answerBuilder.append(",");
            }
            answerBuilder.append(answer);
        }
        return answerBuilder.toString();
    }

    public boolean isExclusivitySelected() {
        return exclusivityCheckBox.isSelected();
    }

    public boolean isNullifierSelected() {
        return nullifierCheckBox.isSelected();
    }

    public boolean isMultiplierSelected(int multiplicatorIndex) {
        return multiplicatorCheckBoxes.get(multiplicatorIndex).isSelected();
    }

    public ArrayList<String> getMultipliersFactor(Player player) {
        ArrayList<String> multiplicatorsFactor = new ArrayList<>();
        for (int i = 0; i < player.getMultipliers().size(); i++)
            multiplicatorsFactor.add(String.valueOf(player.getMultipliers().get(i).getFactor()));
        return multiplicatorsFactor;
    }
}