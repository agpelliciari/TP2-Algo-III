package tp2.clases.screens;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tp2.clases.questions.modes.PenaltyMode;
import tp2.clases.player.Player;
import tp2.clases.questions.types.Question;
import tp2.clases.questions.choice.Choice;
import javafx.scene.control.ScrollPane;
import
        tp2.clases.App;
import tp2.clases.exceptions.InvalidAnswerFormatException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Panel extends ScrollPane {
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