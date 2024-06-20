package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import tp2.clases.handlers.ConfirmButtonHandler;
//import tp2.clases.handlers.ConfirmButtonHandler;

import java.util.function.Consumer;

public class PlayersInputScreen extends VBox {
    private TextField numberOfPlayersTextField;

    public PlayersInputScreen(Consumer<Integer> numberOfPlayersConsumer) {
        super(20); // Espacio entre elementos

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        Label label = new Label("Ingrese la cantidad de jugadores:");
        numberOfPlayersTextField = new TextField();
        numberOfPlayersTextField.setPromptText("Cantidad de jugadores");

        Button confirmButton = new Button("Confirmar");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(this, numberOfPlayersConsumer);
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
            if (numberOfPlayers > 0) {
                numberOfPlayersConsumer.accept(numberOfPlayers);
            } else {
                showError("Ingrese un número válido (> 0).");
            }
        } catch (NumberFormatException e) {
            showError("Ingrese un número válido.");
        }
    }

    private void showError(String errorMessage) {
        System.err.println(errorMessage);
    }
}

