package tp2.clases.screens;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tp2.clases.*;
import tp2.clases.player.*;
import tp2.clases.questions.types.*;
import tp2.clases.questions.choice.*;
import tp2.clases.exceptions.InvalidAnswerFormatException;

import java.util.*;

public class PanelGroupChoice extends VBox {
    private int currentGroup = 0;
    private CheckBox exclusivityCheckBox;
    private CheckBox nullifierCheckBox;
    private CheckBox multiplicatorCheckBox;
    private TextField factorTextField;
    private VBox box;
    private Map<Button, String> buttonAnswerMap = new HashMap<>();
    private List<String> selectedChoicesGroup1 = new ArrayList<>();
    private List<String> selectedChoicesGroup2 = new ArrayList<>();
    private List<String> currentChoices;
    private boolean firstGroupSelected = false;
    private Group selectedGroup;
    private Button confirmButton;
    private App app;
    private Player currentPlayer;
    private Question currentQuestion;

    public PanelGroupChoice(Player currentPlayer, Question currentQuestion, App app) {
        this.app = app;
        this.currentPlayer = currentPlayer;
        this.currentQuestion = currentQuestion;

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

        GroupChoice groupChoice = (GroupChoice) currentQuestion;
        ArrayList<Group> groups = groupChoice.getGroups();
        for (Group group : groups) {
            Button groupButton = new Button(group.getText());
            groupButton.setStyle("-fx-font-size: 14px;");
            box.getChildren().add(groupButton);

            groupButton.setOnAction(event -> {
                if (firstGroupSelected) {
                    selectedGroup = group;
                    enableChoices(currentQuestion.getChoices(), selectedChoicesGroup1);
                    currentChoices = selectedChoicesGroup2;
                } else {
                    selectedGroup = group;
                    enableChoices(currentQuestion.getChoices(), new ArrayList<>());
                    currentChoices = selectedChoicesGroup1;
                    firstGroupSelected = true;
                }
                box.getChildren().remove(groupButton);
                addConfirmButton();
            });
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

        this.getChildren().add(box);
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

    private void enableChoices(List<Choice> choices, List<String> alreadySelected) {
        box.getChildren().removeIf(node -> node instanceof Button && !buttonAnswerMap.containsKey((Button) node));
        for (Choice choice : choices) {
            if (!alreadySelected.contains(String.valueOf(choice.getId()))) {
                Button choiceButton = new Button(choice.getId() + ") " + choice.getContent());
                choiceButton.setStyle("-fx-font-size: 14px;");
                buttonAnswerMap.put(choiceButton, String.valueOf(choice.getId()));
                choiceButton.setOnAction(event -> {
                    String choiceId = buttonAnswerMap.get(choiceButton);
                    currentChoices.add(choiceId);
                    choiceButton.setDisable(true);
                    updateSelectedAnswers();
                    confirmButton.setDisable(false); // Habilitar el botón de confirmación al seleccionar una opción
                });
                box.getChildren().add(choiceButton);
            }
        }
    }

    private void updateSelectedAnswers() {
        // Opcional: Realizar acciones necesarias cuando se actualizan las respuestas seleccionadas.
    }

    private void addConfirmButton() {
        confirmButton = new Button("Confirmar elección");
        confirmButton.setStyle("-fx-font-size: 14px;");
        confirmButton.setDisable(true); // Deshabilitado inicialmente hasta que se haga una elección
        confirmButton.setOnAction(event -> {
            handleConfirmation();
        });
        box.getChildren().add(confirmButton);
    }

    private void handleConfirmation() {
        System.out.println("Elección confirmada: " + getSelectedAnswers());
        confirmButton.setDisable(true);

        currentGroup++;
        if ((currentGroup >= 2) || (firstGroupSelected && !selectedChoicesGroup2.isEmpty())) {
            currentGroup = 0;
            app.showQuestionForPlayer();
        }
    }

    public String getSelectedAnswers() {
        StringBuilder answerBuilder = new StringBuilder();
        for (String answer : currentChoices) {
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