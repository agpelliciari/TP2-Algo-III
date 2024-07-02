package tp2.clases.screens;

import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import tp2.clases.Game;
import tp2.clases.handlers.AnswerButtonHandler;
import tp2.clases.player.Player;
import tp2.clases.questions.types.Question;
import tp2.clases.questions.choice.Choice;
import tp2.clases.App;
import tp2.clases.exceptions.InvalidAnswerFormatException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Panel extends ScrollPane {
    ScoreContainer scores;
    Stage stage;

    private CheckBox exclusivityCheckBox;
    private CheckBox nullifierCheckBox;
    private CheckBox multiplicatorCheckBox;
    private TextField factorTextField;
    private VBox box;
    private Map<Button, String> buttonAnswerMap = new HashMap<>();
    private Set<String> selectedAnswers = new HashSet<>();

    public Panel(Player currentPlayer, Question currentQuestion, App app) {
        box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 20, 20, 20));

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), box);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();

        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#f0f0f0"), CornerRadii.EMPTY, Insets.EMPTY);
        box.setBackground(new Background(backgroundFill));

        box.getChildren().addAll(
                PanelBuilder.createLabel("Pregunta " + currentQuestion.getId(), 20, true),
                PanelBuilder.createLabel(currentPlayer.getName(), 30, true, "#ff9900"),
                PanelBuilder.createLabel(currentQuestion.getContent().getTheme(), 16, false),
                PanelBuilder.createLabel(currentQuestion.getContent().getPrompt(), 16, false)
        );

        for (Choice choice : currentQuestion.getChoices()) {
            Button choiceButton = new Button(choice.getId() + ") " + choice.getContent());
            choiceButton.setStyle("-fx-font-size: 14px;");
            buttonAnswerMap.put(choiceButton, String.valueOf(choice.getId()));
            choiceButton.setOnAction(event -> {
                String choiceId = buttonAnswerMap.get(choiceButton);
                selectedAnswers.add(choiceId);
                choiceButton.setDisable(true);
                updateSelectedAnswers();
            });
            box.getChildren().add(choiceButton);
        }

        exclusivityCheckBox = new CheckBox("Usar exclusividad");
        multiplicatorCheckBox = new CheckBox("Usar multiplicador");
        factorTextField = (TextField) PanelBuilder.createTextField("Factor", 12);
        nullifierCheckBox = new CheckBox("Usar anulador");

        if (currentQuestion.getMode().isPenaltyMode()) {
            box.getChildren().add(PanelBuilder.createMultiplicatorContainer(multiplicatorCheckBox, factorTextField));
        } else if (currentPlayer.getExclusivity().getNumber() > 0) {
            box.getChildren().add(exclusivityCheckBox);
        }

        if (!currentPlayer.getNullifier().isUsed())
            box.getChildren().add(nullifierCheckBox);

        Button answerButton = createAnswerButton(currentQuestion, currentPlayer, app);
        box.getChildren().add(answerButton);

        this.setContent(box);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    public Panel(Stage primaryStage, Scene scene, Game game, int playerIndex, int questionIndex) {

        this.stage = primaryStage;

        Question currentQuestion = game.getQuestion(questionIndex);
        Player currentPlayer = game.getPlayer(playerIndex);

        box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 20, 20, 20));

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), box);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();

        Image image = new Image("file:textura.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        box.setBackground(new Background(backgroundImage));

        box.getChildren().addAll(
                PanelBuilder.createLabel("Pregunta " + currentQuestion.getId(), 20, true),
                PanelBuilder.createLabel(currentPlayer.getName(), 30, true, "#ff9900"),
                PanelBuilder.createLabel(currentQuestion.getContent().getTheme(), 16, false),
                PanelBuilder.createLabel(currentQuestion.getContent().getPrompt(), 16, false)
        );

        for (Choice choice : currentQuestion.getChoices()) {
            Button choiceButton = new Button(choice.getId() + ") " + choice.getContent());
            choiceButton.setStyle("-fx-font-size: 14px;");
            buttonAnswerMap.put(choiceButton, String.valueOf(choice.getId()));
            choiceButton.setOnAction(event -> {
                String choiceId = buttonAnswerMap.get(choiceButton);
                selectedAnswers.add(choiceId);
                choiceButton.setDisable(true);
                updateSelectedAnswers();
            });
            box.getChildren().add(choiceButton);
        }

        exclusivityCheckBox = new CheckBox("Usar exclusividad");
        multiplicatorCheckBox = new CheckBox("Usar multiplicador");
        factorTextField = (TextField) PanelBuilder.createTextField("Factor", 12);
        nullifierCheckBox = new CheckBox("Usar anulador");

        if (currentQuestion.getMode().isPenaltyMode()) {
            box.getChildren().add(PanelBuilder.createMultiplicatorContainer(multiplicatorCheckBox, factorTextField));
        } else if (currentPlayer.getExclusivity().getNumber() > 0) {
            box.getChildren().add(exclusivityCheckBox);
        }

        this.scores = new ScoreContainer();
        showPlayersScore(game);
        box.getChildren().add(scores);

        if (!currentPlayer.getNullifier().isUsed())
            box.getChildren().add(nullifierCheckBox);

        Button answerButton = new Button("Responder");
        AnswerButtonHandler answerButtonHandler = new AnswerButtonHandler(primaryStage, scene, game, playerIndex, questionIndex, this);
        answerButton.setOnAction(answerButtonHandler);

        box.getChildren().add(answerButton);

        this.setContent(box);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    private void showPlayersScore(Game game) {
        scores.cleanContainer();
        for (Player player : game.getPlayers()) {
            Label scoreLabel = new Label(player.getName() + ": " + player.getScore() + " puntos");
            scoreLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            scores.addChild(scoreLabel);
        }
    }

    private Button createAnswerButton(Question currentQuestion, Player currentPlayer, App app) {
        Button answerButton = new Button("Responder");
        answerButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ff6666; -fx-text-fill: white;");
        answerButton.setOnAction(e -> {
            try {
                app.saveAnswerAndProceed(currentQuestion, currentPlayer, isExclusivitySelected(), isNullifierSelected(), getSelectedAnswers(), getFactor(), isMultiplicatorSelected());
            } catch (InvalidAnswerFormatException ex) {
                app.showErrorDialog(ex.getMessage());
            }
        });
        return answerButton;
    }

    private void updateSelectedAnswers() {

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

    public boolean isMultiplicatorSelected() {
        return multiplicatorCheckBox.isSelected();
    }

    public String getFactor() {
        return factorTextField.getText();
    }

}