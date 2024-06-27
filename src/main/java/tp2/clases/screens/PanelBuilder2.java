package tp2.clases.screens;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import tp2.clases.App2;
import tp2.clases.player.Player;
import tp2.clases.questions.types.Question;
import tp2.clases.exceptions.InvalidAnswerFormatException;

public class PanelBuilder2 {

    public static Node createLabel(String text, int fontSize, boolean bold) {
        return createLabel(text, fontSize, bold, null);
    }

    public static Node createLabel(String text, int fontSize, boolean bold, String textColor) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize + "px;" + (bold ? " -fx-font-weight: bold;" : "") + (textColor != null ? " -fx-text-fill: " + textColor + ";" : ""));
        label.setWrapText(true);
        return label;
    }

    public static Node createTextField(String promptText, int fontSize) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: " + fontSize + "px;");
        return textField;
    }

    public static Node createMultiplicatorContainer(CheckBox multiplicatorCheckBox, TextField factorTextField) {
        HBox multiplicatorContainer = new HBox(10);
        multiplicatorContainer.setAlignment(Pos.CENTER);
        factorTextField.setPrefWidth(50);
        multiplicatorContainer.getChildren().addAll(multiplicatorCheckBox, factorTextField);
        return multiplicatorContainer;
    }

    public static Button createAnswerButton(Question currentQuestion, Player currentPlayer, Panel panel, App2 app) {
        Button answerButton = new Button("Responder");
        answerButton.setStyle("-fx-font-size: 14px; -fx-background-color: #ff6666; -fx-text-fill: white;");
        answerButton.setOnAction(e -> {
            try {
                app.saveAnswerAndProceed(currentQuestion, currentPlayer, panel.isExclusivitySelected(), panel.isNullifierSelected(), panel.getAnswer(), panel.getFactor(), panel.isMultiplicatorSelected());
            } catch (InvalidAnswerFormatException ex) {
                app.showErrorDialog(ex.getMessage());
            }
        });
        return answerButton;
    }
}
