package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import tp2.clases.handlers.ConfirmButtonHandler;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PlayersNamesInputScreen extends VBox {
    private int numberOfPlayers;
    private ArrayList<TextField> playerNameTextFields;
    private Consumer<ArrayList<String>> playersNamesConsumer;

    public PlayersNamesInputScreen(int numberOfPlayers, Consumer<ArrayList<String>> playersNamesConsumer) {
        super(20); // Espacio entre elementos
        this.numberOfPlayers = numberOfPlayers;
        this.playersNamesConsumer = playersNamesConsumer;
        this.playerNameTextFields = new ArrayList<>();
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        // Estilos para el VBox
        setStyle("-fx-background-color: #f0f0f0;");

        createPlayerNameInputFields();
    }

    private void createPlayerNameInputFields() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Label label = new Label("Ingrese el nombre del jugador " + (i + 1) + ":");
            label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
            getChildren().add(label);

            TextField playerNameTextField = new TextField();
            playerNameTextField.setPromptText("Nombre del jugador");
            playerNameTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            playerNameTextFields.add(playerNameTextField);
            getChildren().add(playerNameTextField);

            Button confirmButton = new Button("Confirmar");
            confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
            confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
            confirmButton.setOnAction(e -> handlePlayerNameEntry(playerNameTextField));
            getChildren().add(confirmButton);

            playerNameTextField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    handlePlayerNameEntry(playerNameTextField);
                }
            });
        }
    }

    private void handlePlayerNameEntry(TextField playerNameTextField) {
        confirmPlayersName(playerNameTextField);
        if (allPlayersNamesEntered()) {
            ArrayList<String> playersNames = new ArrayList<>();
            for (TextField textField : playerNameTextFields) {
                playersNames.add(textField.getText());
            }
            playersNamesConsumer.accept(playersNames);
        }
    }

    private void confirmPlayersName(TextField playerNameTextField) {
        String playerName = playerNameTextField.getText();
        if (!playerName.isEmpty() && isValidName(playerName)) {
            playerNameTextField.setDisable(true);
        } else {
            showErrorDialog("Por favor ingrese un nombre de jugador válido.");
        }
    }

    private boolean allPlayersNamesEntered() {
        for (TextField textField : playerNameTextFields) {
            if (!textField.isDisabled()) {
                return false;
            }
        }
        return true;
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
