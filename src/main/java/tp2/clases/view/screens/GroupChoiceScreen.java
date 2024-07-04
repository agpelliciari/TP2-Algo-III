package tp2.clases.view.screens;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.util.Duration;
import tp2.clases.controllers.handlers.AnswerButtonHandler;
import tp2.clases.model.Game;
import tp2.clases.model.player.Player;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

public class GroupChoiceScreen extends VBox {

    private StackPane stackPane;
    private ScoreContainer scores;
    private Button answerButton;
    private VBox choicesBox;
    private HBox groupsBox;
    private ArrayList<VBox> groupBoxes = new ArrayList<>();
    private ArrayList<Button> draggedButtons = new ArrayList<>();
    private static CheckBox exclusivityCheckBox;
    private static CheckBox nullifierCheckBox;
    private ArrayList<Button> choiceButtons = new ArrayList<>();
    private ArrayList<Integer> buttonAnswerIds = new ArrayList<>();
    private static ArrayList<Integer> selectedAnswersA;
    private static ArrayList<Integer> selectedAnswersB;

    public GroupChoiceScreen(StackPane stackPane, Game game, int playerIndex, int questionIndex) {
        this.stackPane = stackPane;
        setUI(game, playerIndex, questionIndex);
    }

    private void setUI(Game game, int playerIndex, int questionIndex) {
        GroupChoice groupChoice = (GroupChoice) game.getQuestion(questionIndex);
        Player currentPlayer = game.getPlayer(playerIndex);

        setVBox();
        setBackground();
        setPlayerScores(game, currentPlayer);
        setQuestionAndPlayerInfo(groupChoice, game);
        setChoiceButtons(groupChoice.getChoices());
        setGroupBoxes(groupChoice.getGroups());

        for (Button button : choiceButtons)
            configureDragSource(button);

        for (VBox groupBox : groupBoxes)
            configureDragTarget(groupBox);

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(choicesBox);

        setPowers(currentPlayer);
        setAnswerButton(game, playerIndex, questionIndex);
    }

    private void setVBox() {
        choicesBox = new VBox(20);
        choicesBox.setAlignment(Pos.CENTER);
        choicesBox.setPadding(new Insets(20, 20, 20, 20));

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), choicesBox);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }

    private void setBackground() {
        Image image = new Image("file:src/main/resources/images/white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        choicesBox.setBackground(new Background(backgroundImage));
    }

    private void setPlayerScores(Game game, Player currentPlayer) {
        this.scores = new ScoreContainer();
        showPlayersScore(game, currentPlayer);
        choicesBox.getChildren().add(scores);
    }

    private void showPlayersScore(Game game, Player currentPlayer) {
        scores.cleanContainer();
        for (Player player : game.getPlayers()) {
            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            if (player.getName().equals(currentPlayer.getName())) {
                scoreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: green;");
            } else {
                scoreLabel.setStyle("-fx-font-size: 14px;");
            }
            scores.addChild(scoreLabel);
        }
    }

    private void setQuestionAndPlayerInfo(Question currentQuestion, Game game) {
        choicesBox.getChildren().addAll(
                QuestionScreenBuilder.createLabel("Pregunta " + game.getQuestionCount(), 20, true),
                QuestionScreenBuilder.createLabel(currentQuestion.getContent().getTheme(), 16, false),
                QuestionScreenBuilder.createText(currentQuestion.getContent().getPrompt(), 16)
        );
    }

    private void setChoiceButtons(ArrayList<Choice> choices) {
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            String[] unicodeNumbers = {"①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩"};
            String unicodeNumber = (i < unicodeNumbers.length) ? unicodeNumbers[i] : String.valueOf(i + 1);
            Button choiceButton = new Button(unicodeNumber + " " + choice.getContent());
            choiceButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ebf3fb; -fx-border-color: #000000; -fx-border-radius: 5; -fx-background-radius: 5;");
            choiceButton.setUserData(choice.getId());
            choiceButtons.add(choiceButton);
            buttonAnswerIds.add(choice.getId());
            choicesBox.getChildren().add(choiceButton);
        }
    }

    private void setGroupBoxes(ArrayList<GroupChoice.Group> groups) {
        for (GroupChoice.Group group : groups) {
            VBox groupBox = new VBox();
            groupBox.setSpacing(10);
            groupBox.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: lightblue; -fx-border-radius: 10; -fx-background-radius: 10;");
            groupBox.setAlignment(Pos.CENTER);
            Label groupBoxLabel = new Label("Grupo " + group.getLetter() + ": " + group.getText());
            groupBoxLabel.setStyle("-fx-font-weight: bold;");
            groupBox.getChildren().add(groupBoxLabel);

            groupBoxes.add(groupBox);
        }

        groupsBox = new HBox();
        groupsBox.setSpacing(20);
        groupsBox.setAlignment(Pos.CENTER);

        for (VBox groupBox : groupBoxes)
            groupsBox.getChildren().add(groupBox);

        choicesBox.getChildren().add(groupsBox);
    }

    private void setPowers(Player currentPlayer) {
        exclusivityCheckBox = new CheckBox("Usar exclusividad (" + currentPlayer.getExclusivity().getNumber() + (currentPlayer.getExclusivity().getNumber() == 1 ? " restante)" : " restantes)"));
        nullifierCheckBox = new CheckBox("Usar anulador");

        if (currentPlayer.getExclusivity().getNumber() > 0) {
            choicesBox.getChildren().add(exclusivityCheckBox);
        }
        if (!currentPlayer.getNullifier().isUsed()) {
            choicesBox.getChildren().add(nullifierCheckBox);
        }
    }

    private void setAnswerButton(Game game, int playerIndex, int questionIndex) {
        answerButton = new Button("Responder");
        answerButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px; -fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        answerButton.setDisable(true);
        AnswerButtonHandler answerButtonHandler = new AnswerButtonHandler(stackPane, game, playerIndex, questionIndex, this);
        answerButton.setOnAction(answerButtonHandler);

        double width = 150;
        double height = 45;
        answerButton.setMinSize(width, height);
        answerButton.setPrefSize(width, height);
        answerButton.setMaxSize(width, height);

        choicesBox.getChildren().add(answerButton);
    }

    public ArrayList<ArrayList<Choice>> getSelectedAnswers() {
        ArrayList<ArrayList<Choice>> groupsChosenAnswers = new ArrayList<>();
        ArrayList<Choice> groupA = new ArrayList<>();

        for (Integer id : selectedAnswersA) {
            Choice choice = new Choice(id);
            groupA.add(choice);
        }
        groupsChosenAnswers.add(groupA);

        ArrayList<Choice> groupB = new ArrayList<>();
        for (Integer id : selectedAnswersB) {
            Choice choice = new Choice(id);
            groupB.add(choice);
        }
        groupsChosenAnswers.add(groupB);

        return groupsChosenAnswers;
    }

    public boolean isExclusivitySelected() {
        return exclusivityCheckBox.isSelected();
    }

    public static boolean isNullifierSelected() {
        return nullifierCheckBox.isSelected();
    }

    private void configureDragSource(Button button) {
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(button.getText());
            db.setDragView(button.snapshot(null, null));
            db.setContent(content);
            event.consume();
        });
    }

    private void configureDragTarget(VBox targetBox) {
        targetBox.setOnDragOver(event -> {
            if (event.getGestureSource() != targetBox && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        selectedAnswersA = new ArrayList<>();
        selectedAnswersB = new ArrayList<>();
        targetBox.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String buttonText = db.getString();
                Integer buttonId = getButtonId(buttonText);

                if (buttonId != null) {
                    Button originalButton = findButtonById(buttonId);
                    if (originalButton != null) {
                        targetBox.getChildren().add(originalButton);  // Mueve el botón original al targetBox

                        choicesBox.getChildren().remove(originalButton);
                        choiceButtons.remove(originalButton);

                        if (groupsBox.getChildren().indexOf(targetBox) == 0) {
                            selectedAnswersA.add(buttonId);
                        } else {
                            selectedAnswersB.add(buttonId);
                        }

                        success = true;
                        answerButton.setDisable(false);
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private Integer getButtonId(String buttonText) {
        for (Button button : choiceButtons) {
            if (button.getText().equals(buttonText)) {
                return (Integer) button.getUserData();
            }
        }
        return null;
    }

    private Button findButtonById(Integer buttonId) {
        for (Button button : choiceButtons) {
            if (button.getUserData().equals(buttonId)) {
                return button;
            }
        }
        return null;
    }
}