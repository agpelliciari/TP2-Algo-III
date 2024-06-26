package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tp2.clases.Player;
import tp2.clases.Question;
import javafx.scene.control.ScrollPane;

public class Panel extends ScrollPane {
    private TextField answerTextField;
    private CheckBox exclusivityCheckBox;
    private CheckBox nullifierCheckBox;
    private CheckBox multiplicatorCheckBox;
    private TextField factorTextField;
    private VBox box;

    public Panel(Player currentPlayer, Question currentQuestion){
        box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20,20,20,20));

        box.getChildren().addAll(
                PanelBuilder.createLabel("Pregunta " + currentQuestion.getId(), 20, true),
                PanelBuilder.createLabel(currentPlayer.getName(), 30, true, "#ff9900"),
                PanelBuilder.createLabel(currentQuestion.getContent().getTheme(), 16, false),
                PanelBuilder.createLabel(currentQuestion.getContent().getPrompt(), 16, false)
        );

        currentQuestion.getChoices().forEach(choice ->
                box.getChildren().add(PanelBuilder.createLabel(choice.getId() + ") " + choice.getContent(), 14, false)));

        box.getChildren().add(PanelBuilder.createLabel("Escriba la respuesta", 16, false));

        answerTextField = (TextField) PanelBuilder.createTextField("Respuesta", 14);
        box.getChildren().add(answerTextField);

        exclusivityCheckBox = new CheckBox("Usar exclusividad");
        box.getChildren().add(exclusivityCheckBox);

        multiplicatorCheckBox = new CheckBox("Usar multiplicador");
        factorTextField = (TextField) PanelBuilder.createTextField("Factor", 14);
        box.getChildren().add(PanelBuilder.createMultiplicatorContainer(multiplicatorCheckBox, factorTextField));

        nullifierCheckBox = new CheckBox("Usar anulador");
        box.getChildren().add(nullifierCheckBox);

        this.setContent(box);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    public String getAnswer() {
        return answerTextField.getText();
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

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public void addChild(Button answerButton) {
        box.getChildren().add(answerButton);
    }
}
