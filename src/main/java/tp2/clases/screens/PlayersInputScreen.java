package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import tp2.clases.handlers.ConfirmButtonHandler;

import java.util.function.Consumer;

public class PlayersInputScreen extends VBox {
    private TextField numberOfPlayersTextField;
    private TextField numberOfQuestionsTextField;
    private TextField numberOfPointsTextField;

    public PlayersInputScreen(Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        super(20);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        setStyle("-fx-background-color: #f0f0f0;");

        Label label = labelCreator("Ingrese la cantidad de jugadores:");
        numberOfPlayersTextField = textFieldCreator("Cantidad de jugadores");

        Label questionsLabel = labelCreator("Ingrese el limite de preguntas:");
        numberOfQuestionsTextField = textFieldCreator("Limite de preguntas");

        Label pointsLabel = labelCreator("Ingrese el limite de puntos:");
        numberOfPointsTextField = textFieldCreator("Limite de puntos");

        Button confirmButton = getButton(numberOfPlayersConsumer, numberOfQuestionsConsumer, numberOfPointsConsumer);

        numberOfPlayersTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfPlayersTextField, numberOfPlayersConsumer, 1);
            }
        });

        numberOfQuestionsTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfQuestionsTextField, numberOfQuestionsConsumer, 1);
            }
        });

        numberOfPointsTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfPointsTextField, numberOfPointsConsumer, 1);
            }
        });

        getChildren().addAll(label, numberOfPlayersTextField, questionsLabel, numberOfQuestionsTextField, pointsLabel, numberOfPointsTextField, confirmButton);
    }

    private Label labelCreator(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        return label;
    }

    private TextField textFieldCreator(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        return textField;
    }

    private Button getButton(Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        Button confirmButton = new Button("Confirmar");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(this, numberOfPlayersConsumer, numberOfQuestionsConsumer, numberOfPointsConsumer);
        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnAction(confirmButtonHandler);
        return confirmButton;
    }

    public TextField getNumberOfPlayersTextField() {
        return numberOfPlayersTextField;
    }

    public TextField getNumberOfQuestionsTextField() {
        return numberOfQuestionsTextField;
    }

    public TextField getNumberOfPointsTextField() {
        return numberOfPointsTextField;
    }

    public void confirmNumber(TextField textField, Consumer<Integer> consumer, int minValue) {
        try {
            int value = Integer.parseInt(textField.getText());
            if (value > minValue) {
                consumer.accept(value);
            } else {
                showErrorDialog("Ingrese un número válido (> 1).");
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Ingrese un número válido.");
        }
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
