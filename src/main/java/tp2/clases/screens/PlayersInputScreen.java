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

    public PlayersInputScreen(Consumer<Integer> numberOfPlayersConsumer) {
        super(20);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        setStyle("-fx-background-color: #f0f0f0;");


        Label label = new Label("Ingrese la cantidad de jugadores:");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

        numberOfPlayersTextField = new TextField();
        numberOfPlayersTextField.setPromptText("Cantidad de jugadores");
        numberOfPlayersTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Button confirmButton = new Button("Confirmar");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(this,numberOfPlayersConsumer);
        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnAction(confirmButtonHandler);

        numberOfPlayersTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumberOfPlayers(numberOfPlayersConsumer);
            }
        });

        getChildren().addAll(label, numberOfPlayersTextField, confirmButton);
    }

    public void confirmNumberOfPlayers(Consumer<Integer> numberOfPlayersConsumer) {
        try {
            int numberOfPlayers = Integer.parseInt(numberOfPlayersTextField.getText());
            if (numberOfPlayers > 1) {
                numberOfPlayersConsumer.accept(numberOfPlayers);
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
